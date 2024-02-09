package com.jcastellanos.demo.service;

import com.jcastellanos.demo.model.City;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.Arrays;

@Service
public class CityService {

    private WebClient client;
    @Value("${endpoint.service.cities.url}")
    private String endpointCities;


    @PostConstruct
    public void postConstructor() {
        this.client = WebClient.create(endpointCities);
    }

    public Flux<City> getCities() {
        return this.client.get().uri("/cities").accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(City.class);
    }
}
