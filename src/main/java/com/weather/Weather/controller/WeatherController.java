package com.weather.Weather.controller;
import com.weather.Weather.service.WeatherService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController

@RequestMapping("/weather")
public class WeatherController {
    private final WeatherService weatherService;
    public WeatherController(WeatherService weatherService){
        this.weatherService = weatherService;
    }


    @GetMapping
    public Object getWeather(@RequestParam List<String> cities){
        if(cities.size()>5){
            return Map.of("error","Requested data for up to 5 cities only");
        }
        return weatherService.getWeatherData(cities);
    }
}
