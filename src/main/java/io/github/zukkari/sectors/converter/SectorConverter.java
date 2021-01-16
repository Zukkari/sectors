package io.github.zukkari.sectors.converter;

import io.github.zukkari.sectors.dto.SectorDto;
import io.github.zukkari.sectors.model.Sector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class SectorConverter implements Converter<Sector, SectorDto> {
  private static final Logger log = LoggerFactory.getLogger(SectorConverter.class);

  @Override
  public SectorDto convert(Sector source) {
    log.debug("Converting sector with id: {}", source.getId());

    final var sectorValue = source.getValue();
    final var sectorName = source.getName();
    final var subSectors =
        source.getSubSectors().stream().map(this::convert).collect(Collectors.toList());

    return new SectorDto(sectorValue, sectorName, subSectors);
  }
}
