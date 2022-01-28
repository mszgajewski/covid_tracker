package com.example.covid_tracker;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Slf4j
@Controller
public class MainController {

    CoronaService coronaService;

    public MainController(CoronaService coronaService){
        this.coronaService = coronaService;
    }

    @GetMapping("/test")
    public String testMethod(Model model) throws IOException {
        model.addAttribute("test1", "Witaj użytkowniku!");
        coronaService.populateDatabase2();
        coronaService.populateDatabase();
        return "mainTemplate";
    }

    @GetMapping("/")
    public String root(Model model) {
      //model.addAttribute("coronaData",coronaService.findByLastUpdate(LocalDate.now().minusDays(1)));
        model.addAttribute("coronaData",coronaService.findAll());
        return "mainTemplate";
    }
}
