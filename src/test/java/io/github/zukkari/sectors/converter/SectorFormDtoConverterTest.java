package io.github.zukkari.sectors.converter;

import io.github.zukkari.sectors.dto.SectorFormDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class SectorFormDtoConverterTest {

  SectorFormDtoConverter sectorFormDtoConverter;

  @BeforeEach
  void setUp() {
    sectorFormDtoConverter = new SectorFormDtoConverter();
  }

  @Test
  void test_convert() {
    var dto = new SectorFormDto();
    dto.setName("name");
    dto.setAgreedToTerms(true);
    dto.setSelectedSectors(List.of(1L));

    final var converted = sectorFormDtoConverter.convert(dto);

    assertThat(converted).isNotNull();
    assertThat(converted.getName()).isEqualTo("name");
    assertThat(converted.isAgreedToTerms()).isTrue();
    assertThat(converted.getSelectedSectors())
        .isNotNull()
        .isNotEmpty()
        .size()
        .isEqualTo(1)
        .returnToIterable()
        .anyMatch(s -> 1L == s);
  }
}
