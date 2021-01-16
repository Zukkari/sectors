package io.github.zukkari.sectors.controller;

import io.github.zukkari.sectors.service.SectorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SectorsController {
  private static final Logger log = LoggerFactory.getLogger(SectorsController.class);

  private final SectorService sectorService;

  @Autowired
  public SectorsController(SectorService sectorService) {
    this.sectorService = sectorService;
  }

  @GetMapping("/")
  public String index(Model model) {
    log.trace("/index called");

    var sectors = sectorService.getSectors();
    log.debug("Loaded {} sector values", sectors.size());
    model.addAttribute("sectors", sectors);

    return "index";
  }
}
