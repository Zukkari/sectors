package io.github.zukkari.sectors.setup;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.zukkari.sectors.model.Sector;
import io.github.zukkari.sectors.repository.SectorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class SysSetupService implements ApplicationRunner {
  private static final Logger log = LoggerFactory.getLogger(SysSetupService.class);

  private final Migration migration;

  private final ObjectMapper objectMapper;
  private final SectorRepository sectorRepository;
  private final StreamProvider streamProvider;

  @Autowired
  public SysSetupService(
      Migration migration,
      ObjectMapper objectMapper,
      SectorRepository sectorRepository,
      StreamProvider streamProvider) {
    this.migration = migration;
    this.objectMapper = objectMapper;
    this.sectorRepository = sectorRepository;
    this.streamProvider = streamProvider;
  }

  @Override
  public void run(ApplicationArguments args) throws Exception {
    log.info("Running system setup service");

    if (!migration.isShouldRun()) {
      log.info("Migration run disabled, skipping...");
      return;
    }

    for (String file : migration.getFiles()) {
      try (var resource = streamProvider.getStream(file)) {
        final var sectors = objectMapper.readValue(resource, Sector[].class);
        log.info("Found '{}' records from file: {}", sectors.length, file);

        sectorRepository.saveAll(Arrays.asList(sectors));
      }

      log.info("Migration finished for file: {}", file);
    }
  }
}
