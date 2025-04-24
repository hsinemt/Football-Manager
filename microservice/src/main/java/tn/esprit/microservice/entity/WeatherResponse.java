package tn.esprit.microservice.entity;

import lombok.Data;
import java.util.List;

@Data
public class WeatherResponse {
    private List<Weather> weather;  // This maps the "weather" array correctly
    private MainInfo main;

    @Data
    public static class Weather {
        private String main;
        private String description;
    }

    @Data
    public static class MainInfo {
        private double temp;
        private double humidity;
        public double getTempInCelsius() {
            return temp - 273.15;
        }
    }
}
