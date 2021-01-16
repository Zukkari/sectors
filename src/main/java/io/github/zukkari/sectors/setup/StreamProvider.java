package io.github.zukkari.sectors.setup;

import java.io.InputStream;

public interface StreamProvider {

  /**
   * Get the input stream for provided file. Note that the user of the handle is responsible for
   * closing the stream.
   *
   * @param f file path to get the input stream for
   * @return {@link InputStream} to consume
   */
  InputStream getStream(String f);
}
