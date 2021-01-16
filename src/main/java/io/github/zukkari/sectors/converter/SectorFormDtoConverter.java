package io.github.zukkari.sectors.converter;

import io.github.zukkari.sectors.dto.SectorFormDto;
import io.github.zukkari.sectors.model.SectorForm;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class SectorFormDtoConverter implements Converter<SectorFormDto, SectorForm> {

  @Override
  @NonNull
  public SectorForm convert(SectorFormDto source) {
    final var sectorForm = new SectorForm();

    sectorForm.setName(source.getName());
    sectorForm.setAgreedToTerms(source.isAgreedToTerms());
    sectorForm.setSelectedSectors(source.getSelectedSectors());

    return sectorForm;
  }
}
