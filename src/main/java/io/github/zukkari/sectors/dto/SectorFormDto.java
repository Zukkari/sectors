package io.github.zukkari.sectors.dto;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class SectorFormDto {

  @NotNull(message = "Name cannot be empty")
  @NotEmpty(message = "Name cannot be empty")
  private String name;

  @NotNull(message = "Selected sectors cannot be empty")
  @NotEmpty(message = "Selected sectors cannot be empty")
  @Size(min = 1, message = "Selected sectors must contain at least one value")
  private List<Long> selectedSectors;

  @AssertTrue(message = "You must agree to terms!")
  private boolean agreedToTerms;

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
