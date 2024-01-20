package com.jcastellanos.demo.service;

import com.jcastellanos.demo.model.City;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.Arrays;

@Service
public class CityService {

    public Flux<City> getCities() {
        return Flux.fromIterable(Arrays.asList(new City[]{new City("Bogota", "Colombia"),
                new City("Cali", "Colombia"),
                new City("Medellin", "Colombia"),
                new City("Cartagena", "Colombia")}));
    }
}
