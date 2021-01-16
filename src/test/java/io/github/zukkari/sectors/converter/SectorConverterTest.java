package io.github.zukkari.sectors.converter;

import io.github.zukkari.sectors.dto.SectorDto;
import io.github.zukkari.sectors.model.Sector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class SectorConverterTest {

  SectorConverter sectorConverter;

  @BeforeEach
  void setUp() {
    sectorConverter = new SectorConverter();
  }

  @Test
  void test_convert() {
    final var sector = new Sector();
    sector.setId("testId");
    sector.setName("testName");
    sector.setValue(40L);

    var subSector = new Sector();
    subSector.setId("testSubSector");
    subSector.setValue(666L);
    subSector.setName("Sub sector");

    sector.setSubSectors(List.of(subSector));

    var dto = sectorConverter.convert(sector);

    verifyDto(dto, "testName", 40L);

    assertThat(dto.getSubSectors()).size().isEqualTo(1);

    var subDto = dto.getSubSectors().get(0);
    verifyDto(subDto, "Sub sector", 666L);
  }

  private void verifyDto(SectorDto dto, String expectedName, long expectedValue) {
    assertThat(dto).isNotNull();
    assertThat(dto.getName()).isEqualTo(expectedName);
    assertThat(dto.getValue()).isEqualTo(expectedValue);
  }
}
