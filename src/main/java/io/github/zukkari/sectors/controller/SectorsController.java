package io.github.zukkari.sectors.controller;

import io.github.zukkari.sectors.dto.SectorFormDto;
import io.github.zukkari.sectors.service.SectorFormService;
import io.github.zukkari.sectors.service.SectorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Optional;

@Controller
public class SectorsController {
  private static final Logger log = LoggerFactory.getLogger(SectorsController.class);

  private static final String FORM_ATTRIBUTE = "sectorFormDto";
  private static final String FORM_ID = "_form_id";

  private final SectorFormService sectorFormService;
  private final SectorService sectorService;

  @Autowired
  public SectorsController(SectorFormService sectorFormService, SectorService sectorService) {
    this.sectorFormService = sectorFormService;
    this.sectorService = sectorService;
  }

  @GetMapping("/")
  public String index(
      Model model, @SessionAttribute(value = FORM_ID, required = false) String formId) {
    log.trace("/index called");

    var sectors = sectorService.getSectors();
    log.debug("Loaded {} sector values", sectors.size());
    model.addAttribute("sectors", sectors);

    Optional.ofNullable(formId)
        .flatMap(sectorFormService::getForm)
        .ifPresent(form -> model.addAttribute(FORM_ATTRIBUTE, form));

    if (!model.containsAttribute(FORM_ATTRIBUTE)) {
      model.addAttribute(FORM_ATTRIBUTE, new SectorFormDto());
    }

    return "index";
  }

  @PostMapping("/submit")
  public String submitForm(
      @Valid @ModelAttribute("sectorFormDto") SectorFormDto sectorFormDto,
      BindingResult bindingResult,
      Model model,
      HttpSession session,
      @SessionAttribute(value = FORM_ID, required = false) String formId) {
    if (bindingResult.hasErrors()) {
      log.info("Submitted form has errors: {}", bindingResult.getAllErrors());

      model.addAttribute(FORM_ATTRIBUTE, sectorFormDto);
      model.addAttribute("errors", bindingResult);
      return index(model, null);
    }

    log.info("Form has no binding errors, persisting form with id: {}", formId);
    var newFormId =
        formId == null
            ? sectorFormService.save(sectorFormDto)
            : sectorFormService.update(formId, sectorFormDto);

    session.setAttribute(FORM_ID, newFormId);

    return "redirect:/success.html";
  }
}
