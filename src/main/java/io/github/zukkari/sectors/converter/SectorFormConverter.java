package io.github.zukkari.sectors.converter;

import io.github.zukkari.sectors.dto.SectorFormDto;
import io.github.zukkari.sectors.model.SectorForm;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class SectorFormConverter implements Converter<SectorForm, SectorFormDto> {

  @Override
  public SectorFormDto convert(SectorForm source) {
    final var sectorForm = new SectorFormDto();

    sectorForm.setName(source.getName());
    sectorForm.setAgreedToTerms(source.isAgreedToTerms());
    sectorForm.setSelectedSectors(source.getSelectedSectors());

    return sectorForm;
  }
}
