package com.jcastellanos.demo.controller;

import com.jcastellanos.demo.service.CityService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.result.view.Rendering;
import reactor.core.publisher.Mono;

@Controller
public class MainController {
    private CityService cityService;

    public MainController(CityService cityService) {
        this.cityService = cityService;
    }
    @GetMapping("/")
    public Mono<Rendering> home() {
        var cities = cityService.getCities();
        return Mono.just(Rendering.view("index")
                .modelAttribute("cities", cities).build());
    }
}
