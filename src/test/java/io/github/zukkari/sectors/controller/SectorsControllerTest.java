package io.github.zukkari.sectors.controller;

import io.github.zukkari.sectors.dto.SectorDto;
import io.github.zukkari.sectors.dto.SectorFormDto;
import io.github.zukkari.sectors.service.SectorFormService;
import io.github.zukkari.sectors.service.SectorService;
import io.github.zukkari.sectors.setup.SysSetupService;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.hamcrest.core.IsNull.notNullValue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@Profile("test")
@SpringBootTest
@AutoConfigureMockMvc
class SectorsControllerTest {

  @MockBean SectorFormService sectorFormService;

  @MockBean SectorService sectorService;

  @MockBean SysSetupService sysSetupService;

  @Autowired WebApplicationContext webApplicationContext;

  MockMvc mockMvc;

  @BeforeEach
  void setUp() {
    mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
  }

  @Test
  void test_index_initial() throws Exception {
    var sectors =
        List.of(
            new SectorDto(1L, "Sector Name", List.of()),
            new SectorDto(2L, "Second sector", List.of()));

    given(sectorService.getSectors()).willReturn(sectors);

    mockMvc
        .perform(get("/"))
        .andExpect(status().isOk())
        .andExpect(view().name("index"))
        .andExpect(model().attribute("sectors", sectors))
        .andExpect(model().attribute("sectorFormDto", notNullValue()));
  }

  @Test
  void test_submit_invalid() throws Exception {
    mockMvc
        .perform(
            post("/submit")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
        .andExpect(status().isOk())
        .andExpect(view().name("index"))
        .andExpect(
            model()
                .attribute(
                    "errors",
                    new BaseMatcher<>() {
                      @Override
                      public void describeTo(Description description) {}

                      @Override
                      public boolean matches(Object o) {
                        final var bindingResult = (BindingResult) o;
                        return bindingResult.hasErrors()
                            && bindingResult.hasFieldErrors("name")
                            && bindingResult.hasFieldErrors("selectedSectors")
                            && bindingResult.hasFieldErrors("agreedToTerms");
                      }
                    }));
  }

  @Test
  void test_refill_form() throws Exception {
    final var formDto = new SectorFormDto();
    formDto.setName("My name");
    formDto.setAgreedToTerms(true);
    formDto.setSelectedSectors(List.of(1L, 2L, 3L));

    given(sectorFormService.getForm(eq("my_form_id"))).willReturn(Optional.of(formDto));

    mockMvc
        .perform(get("/").sessionAttr("_form_id", "my_form_id"))
        .andExpect(status().isOk())
        .andExpect(view().name("index"))
        .andExpect(
            model()
                .attribute(
                    "sectorFormDto",
                    new BaseMatcher<SectorFormDto>() {
                      @Override
                      public boolean matches(Object o) {
                        return Objects.equals(o, formDto);
                      }

                      @Override
                      public void describeTo(Description description) {}
                    }));
  }
}
