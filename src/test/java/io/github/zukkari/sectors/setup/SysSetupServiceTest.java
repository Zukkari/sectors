package io.github.zukkari.sectors.setup;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.zukkari.sectors.model.Sector;
import io.github.zukkari.sectors.repository.SectorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.atMostOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;

class SysSetupServiceTest {

  Migration migration;
  SectorRepository sectorRepository;
  ObjectMapper objectMapper;
  StreamProvider streamProvider;

  SysSetupService sysSetupService;

  @BeforeEach
  void setUp() {
    migration = mock(Migration.class);
    sectorRepository = mock(SectorRepository.class);
    objectMapper = spy(new ObjectMapper());
    streamProvider = mock(StreamProvider.class);

    sysSetupService =
        new SysSetupService(migration, objectMapper, sectorRepository, streamProvider);
  }

  @Test
  void test_migration_no_run() throws Exception {
    given(migration.isShouldRun()).willReturn(false);

    sysSetupService.run(null);

    then(migration).should(never()).getFiles();
    then(objectMapper).shouldHaveNoInteractions();
    then(sectorRepository).shouldHaveNoInteractions();
  }

  @Test
  @SuppressWarnings("unchecked")
  void test_migration_run() throws Exception {
    given(migration.isShouldRun()).willReturn(true);
    given(migration.getFiles()).willReturn(List.of("single.json"));

    var json =
        "[{\"value\":1,\"name\":\"TestSector 1\",\"subSectors\":[{\"value\":19,\"name\":\"SubSector 2\",\"subSectors\":[]}]}]";
    var stream = new ByteArrayInputStream(json.getBytes(StandardCharsets.UTF_8));

    given(streamProvider.getStream(any())).willReturn(stream);

    given(sectorRepository.saveAll(any(List.class)))
        .willAnswer(invocationOnMock -> invocationOnMock.getArguments()[0]);

    sysSetupService.run(null);

    then(migration).should(atMostOnce()).isShouldRun();

    then(migration).should(atMostOnce()).getFiles();

    then(objectMapper).should().readValue(any(InputStream.class), eq(Sector[].class));

    then(sectorRepository).should().saveAll(any());
  }
}
