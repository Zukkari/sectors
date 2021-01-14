package io.github.zukkari.sectors.setup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class Migration {
  private final boolean shouldRun;
  private final List<String> files;

  @Autowired
  public Migration(
      @Value("${sectors.insertion.run}") boolean shouldRun,
      @Value("${sectors.insertion.files}") List<String> files) {
    this.shouldRun = shouldRun;
    this.files = files;
  }

  public boolean isShouldRun() {
    return shouldRun;
  }

  public List<String> getFiles() {
    return files;
  }
}
