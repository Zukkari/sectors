package io.github.zukkari.sectors.converter;

import io.github.zukkari.sectors.model.SectorForm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class SectorFormConverterTest {

  SectorFormConverter sectorFormConverter;

  @BeforeEach
  void setUp() {
    sectorFormConverter = new SectorFormConverter();
  }

  @Test
  void test_convert() {
    final var sectorForm = new SectorForm();
    sectorForm.setName("Name");
    sectorForm.setAgreedToTerms(true);
    sectorForm.setSelectedSectors(List.of(1L));

    final var converted = sectorFormConverter.convert(sectorForm);

    assertThat(converted).isNotNull();
    assertThat(converted.getName()).isEqualTo("Name");
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
