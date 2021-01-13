package io.github.zukkari.sectors.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SectorsController {

    @GetMapping("/")
    public String index() {
        return "index";
    }
}
