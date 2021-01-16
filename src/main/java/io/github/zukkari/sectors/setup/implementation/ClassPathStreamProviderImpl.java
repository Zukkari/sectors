package io.github.zukkari.sectors.setup.implementation;

import io.github.zukkari.sectors.setup.StreamProvider;
import org.springframework.stereotype.Component;

import java.io.InputStream;

/** Implementation that reads file from JAR classpath */
@Component
public class ClassPathStreamProviderImpl implements StreamProvider {

  @Override
  public InputStream getStream(String f) {
    return getClass().getClassLoader().getResourceAsStream(f);
  }
}
