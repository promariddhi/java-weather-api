package com.proma.java_weather_api.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.proma.java_weather_api.dto.ApiInfo;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class WeatherController {

    @GetMapping("/")
    public ApiInfo getApiInfo() {
        return new ApiInfo("Weather API", "1.0.0", "Returns historical weather information");
    }

    @GetMapping("/weather")
    public String getWeather(@RequestParam String city, @RequestParam(required = false) LocalDate date,
            @RequestParam(required = false) LocalDate from, @RequestParam(required = false) LocalDate to) {

        // Must provide either date OR a complete range
        if (date == null && (from == null || to == null)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        // Cannot provide both
        if (date != null && (from != null || to != null)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        // Range must be valid
        if (from != null && to != null && from.isAfter(to)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return "TODO";
    }

}
