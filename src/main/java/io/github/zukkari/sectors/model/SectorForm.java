package io.github.zukkari.sectors.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class SectorForm {
  @Id private String id;

  private String name;
  private List<Long> selectedSectors;
  private boolean agreedToTerms;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Long> getSelectedSectors() {
    return selectedSectors;
  }

  public void setSelectedSectors(List<Long> selectedSectors) {
    this.selectedSectors = selectedSectors;
  }

  public boolean isAgreedToTerms() {
    return agreedToTerms;
  }

  public void setAgreedToTerms(boolean agreedToTerms) {
    this.agreedToTerms = agreedToTerms;
  }
}
