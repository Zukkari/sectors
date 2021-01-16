package io.github.zukkari.sectors.service;

import io.github.zukkari.sectors.converter.SectorConverter;
import io.github.zukkari.sectors.model.Sector;
import io.github.zukkari.sectors.repository.SectorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.atMostOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

class SectorServiceTest {

  SectorConverter sectorConverter;
  SectorRepository sectorRepository;

  SectorService sectorService;

  @BeforeEach
  void setUp() {
    sectorConverter = spy(new SectorConverter());
    sectorRepository = mock(SectorRepository.class);

    sectorService = new SectorService(sectorRepository, sectorConverter);
  }

  @Test
  void test_getSectors() {
    var sector = Sector.fromJson(40L, "testSector", List.of());

    given(sectorRepository.findAll()).willReturn(List.of(sector));

    final var sectors = sectorService.getSectors();

    assertThat(sectors).isNotNull().isNotEmpty().size().isEqualTo(1);

    var sectorDto = sectors.get(0);

    assertThat(sectorDto).isNotNull();

    assertThat(sectorDto.getName()).isEqualTo("testSector");
    assertThat(sectorDto.getValue()).isEqualTo(40L);
    assertThat(sectorDto.getSubSectors()).isEqualTo(List.of());

    then(sectorRepository)
            .should(atMostOnce())
            .findAll();

    then(sectorConverter)
            .should()
            .convert(eq(sector));
  }
}
