package io.github.zukkari.sectors.service;

import io.github.zukkari.sectors.converter.SectorFormConverter;
import io.github.zukkari.sectors.converter.SectorFormDtoConverter;
import io.github.zukkari.sectors.dto.SectorFormDto;
import io.github.zukkari.sectors.repository.SectorFormRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SectorFormService {
  private static final Logger log = LoggerFactory.getLogger(SectorFormService.class);

  private final SectorFormConverter sectorFormConverter;
  private final SectorFormDtoConverter sectorFormDtoConverter;

  private final SectorFormRepository sectorFormRepository;

  @Autowired
  public SectorFormService(
      SectorFormConverter sectorFormConverter,
      SectorFormDtoConverter sectorFormDtoConverter,
      SectorFormRepository sectorFormRepository) {
    this.sectorFormConverter = sectorFormConverter;
    this.sectorFormDtoConverter = sectorFormDtoConverter;
    this.sectorFormRepository = sectorFormRepository;
  }

  /**
   * Fetch form with provided form id
   *
   * @param formId id of the form to fetch
   * @return maybe a form with provided id
   */
  public Optional<SectorFormDto> getForm(String formId) {
    log.info("Fetching form with form id: {}", formId);
    return sectorFormRepository.findById(formId).map(sectorFormConverter::convert);
  }

  /**
   * Persist this form to the database
   *
   * @param dto form to persist
   * @return id of persisted form
   */
  public String save(SectorFormDto dto) {
    var converted = sectorFormDtoConverter.convert(dto);
    return sectorFormRepository.save(converted).getId();
  }

  /**
   * Update the form with the provided ID
   *
   * @param id id of the form to update
   * @param dto dto to use when updating the form
   * @return id of the updated form
   */
  public String update(String id, SectorFormDto dto) {
    final var converted = sectorFormDtoConverter.convert(dto);
    converted.setId(id);
    return sectorFormRepository.save(converted).getId();
  }
}
