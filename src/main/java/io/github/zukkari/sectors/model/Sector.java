package io.github.zukkari.sectors.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Objects;

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

  @JsonCreator
  public static Sector factory(
          @JsonProperty("value") long value,
          @JsonProperty("name") String name,
          @JsonProperty("subSectors") List<Sector> subSectors) {
    final var sector = new Sector();
    sector.setValue(value);
    sector.setName(name);
    sector.setSubSectors(subSectors);

    return sector;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Sector sector = (Sector) o;

    if (value != sector.value) return false;
    if (!Objects.equals(id, sector.id)) return false;
    if (!Objects.equals(name, sector.name)) return false;
    return Objects.equals(subSectors, sector.subSectors);
  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (int) (value ^ (value >>> 32));
    result = 31 * result + (name != null ? name.hashCode() : 0);
    result = 31 * result + (subSectors != null ? subSectors.hashCode() : 0);
    return result;
  }
}
