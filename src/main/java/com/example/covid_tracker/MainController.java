package com.example.covid_tracker;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;

@Slf4j
@Controller
public class MainController {

    CoronaService coronaService;

    public MainController(CoronaService coronaService){
        this.coronaService = coronaService;
    }

    @GetMapping("/test")
    public String testMethod (Model model) throws IOException {
        model.addAttribute("test1", "Witaj użytkowniku!");
        coronaService.populateDatabase2();
        coronaService.populateDatabase();
        return "mainTemplate";
    }

    @GetMapping("/")
    public String root (Model model) throws IOException {
        //model.addAttribute("coronaData",coronaService.findByLastUpdate(LocalDate.now().minusDays(1)));
        model.addAttribute("coronaData",coronaService.findAll());
        return "mainTemplate";
    }

    @RequestMapping(value = "/test-path", method = RequestMethod.GET)
    public String roots (Model model) throws IOException {
        //model.addAttribute("coronaData",coronaService.findByLastUpdate(LocalDate.now().minusDays(1)));
        model.addAttribute("coronaData",coronaService.findAll());
        return "mainTemplate";
    }
}