package io.github.zukkari.sectors.service;

import io.github.zukkari.sectors.converter.SectorConverter;
import io.github.zukkari.sectors.dto.SectorDto;
import io.github.zukkari.sectors.repository.SectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SectorService {

  private final SectorRepository sectorRepository;
  private final SectorConverter sectorConverter;

  @Autowired
  public SectorService(SectorRepository sectorRepository, SectorConverter sectorConverter) {
    this.sectorRepository = sectorRepository;
    this.sectorConverter = sectorConverter;
  }

  /**
   * Get all sectors available in the database
   *
   * @return {@link List} containing instances of {@link SectorDto}-s
   */
  public List<SectorDto> getSectors() {
    return sectorRepository.findAll().stream()
        .map(sectorConverter::convert)
        .collect(Collectors.toList());
  }
}
