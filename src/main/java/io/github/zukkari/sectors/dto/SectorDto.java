package io.github.zukkari.sectors.dto;

import java.util.List;

public class SectorDto {
  private final long value;
  private final String name;
  private final List<SectorDto> subSectors;

  public SectorDto(long value, String name, List<SectorDto> subSectors) {
    this.value = value;
    this.name = name;
    this.subSectors = subSectors;
  }

  public long getValue() {
    return value;
  }

  public String getName() {
    return name;
  }

  public List<SectorDto> getSubSectors() {
    return subSectors;
  }
}
