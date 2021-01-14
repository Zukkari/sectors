package io.github.zukkari.sectors.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class Sector {

  @Id private String id;
  private long value;
  private String name;
  private List<Sector> subSectors;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public long getValue() {
    return value;
  }

  public void setValue(long value) {
    this.value = value;
  }

  public List<Sector> getSubSectors() {
    return subSectors;
  }

  public void setSubSectors(List<Sector> subSectors) {
    this.subSectors = subSectors;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
