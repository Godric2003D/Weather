package com.weather.Weather.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WeatherService {
    private final RestTemplate restTemplate = new RestTemplate();
    public Map<String, Map<String, Double>> getWeatherData(List<String> cities){
        Map<String, Map<String,Double>> result = new HashMap<>();
        for(String city:cities) {
            String url = String.format("https://openweathermap.org/data/2.5/weather?q="+city+"&appid=439d4b804bc8187953eb36d2a8c26a02",city);

            Map<String, Object> response = restTemplate.getForObject(url, Map.class);
            Map<String,Object> main = (Map<String,Object>) response.get("main");
            double minTemp = ((Number)main.get("temp_min")).doubleValue();
            double maxTemp = ((Number)main.get("temp_max")).doubleValue();
            result.put(city, Map.of("minTemp",minTemp,"maxTemp",maxTemp));

        }
        return result;
    }
}
