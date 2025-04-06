package tn.esprit.microservice.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tn.esprit.microservice.entity.WeatherResponse;

@Service
public class WeatherService {
    private final RestTemplate restTemplate;
    private final String apiUrl = "http://api.openweathermap.org/data/2.5/weather";
    private final String apiKey = "75ca7bcef4d9c917037eafbb98b9a04b";

    public WeatherService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public WeatherResponse getWeather(String city) {
        String url = String.format("%s?q=%s&appid=%s", apiUrl, city, apiKey);
        return restTemplate.getForObject(url, WeatherResponse.class);
    }
}

