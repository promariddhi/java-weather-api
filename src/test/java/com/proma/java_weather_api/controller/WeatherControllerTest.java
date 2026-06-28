package com.proma.java_weather_api.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;;

@WebMvcTest(WeatherController.class)
public class WeatherControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getApiInfoReturnsInfo() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Weather API"))
                .andExpect(jsonPath("$.version").value("1.0.0"));
    }

    @Test
    void getWeatherWithoutCityReturns400() throws Exception {
        mockMvc.perform(get("/weather"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getWeatherWithoutDatesReturns400() throws Exception {
        mockMvc.perform(get("/weather")
                .param("city", "London"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getWeatherWithSingleDateReturns200() throws Exception {
        mockMvc.perform(get("/weather")
                .param("city", "London")
                .param("date", "2026-02-26"))
                .andExpect(status().isOk());
    }

    @Test
    void getWeatherWithDateRangeReturns200() throws Exception {
        mockMvc.perform(get("/weather")
                .param("city", "London")
                .param("from", "2026-02-26")
                .param("to", "2026-04-26"))
                .andExpect(status().isOk());
    }

    @Test
    void getWeatherWithFromOnlyReturns400() throws Exception {
        mockMvc.perform(get("/weather")
                .param("city", "London")
                .param("from", "2026-02-26"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getWeatherWithToOnlyReturns400() throws Exception {
        mockMvc.perform(get("/weather")
                .param("city", "London")
                .param("to", "2026-04-26"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getWeatherWithDateAndRangeReturns400() throws Exception {
        mockMvc.perform(get("/weather")
                .param("city", "London")
                .param("date", "2026-02-26")
                .param("from", "2026-02-26")
                .param("to", "2026-04-26"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getWeatherWithInvalidDateRangeReturns400() throws Exception {
        mockMvc.perform(get("/weather")
                .param("city", "London")
                .param("from", "2026-04-26")
                .param("to", "2026-02-26"))
                .andExpect(status().isBadRequest());
    }
}