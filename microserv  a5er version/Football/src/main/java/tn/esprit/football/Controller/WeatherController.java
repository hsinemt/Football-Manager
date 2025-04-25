package tn.esprit.football.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    private final Random random = new Random();

    @GetMapping("/realtime")
    public ResponseEntity<Map<String, Object>> getRealtimeWeather(
            @RequestParam String city,
            @RequestParam(required = false) String country) {

        // Générer des données météo simulées
        Map<String, Object> result = new HashMap<>();

        // Informations de base
        result.put("city", city);
        result.put("country", country != null ? country : "Unknown");
        result.put("timestamp", LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));

        // Données météo simulées
        double temperature = 15 + (random.nextDouble() * 20); // Entre 15°C et 35°C
        result.put("temperature", Math.round(temperature * 10.0) / 10.0);
        result.put("feels_like", Math.round((temperature - 2 + random.nextDouble() * 4) * 10.0) / 10.0);
        result.put("humidity", 40 + random.nextInt(50)); // Entre 40% et 90%
        result.put("pressure", 1000 + random.nextInt(30)); // Entre 1000 et 1030 hPa
        result.put("wind_speed", Math.round((2 + random.nextDouble() * 8) * 10.0) / 10.0); // Entre 2 et 10 m/s

        // Condition météo simulée
        String[] conditions = {"Clear", "Clouds", "Rain", "Drizzle", "Thunderstorm", "Snow", "Mist"};
        String[] descriptions = {
                "ciel dégagé", "nuages épars", "pluie légère", "bruine", "orage", "neige légère", "brouillard"
        };
        int conditionIndex = random.nextInt(conditions.length);
        result.put("weather_condition", conditions[conditionIndex]);
        result.put("weather_description", descriptions[conditionIndex]);

        return ResponseEntity.ok(result);
    }

    @GetMapping("/forecast")
    public ResponseEntity<Map<String, Object>> getWeatherForecast(
            @RequestParam String city,
            @RequestParam(required = false) String country) {

        Map<String, Object> result = new HashMap<>();
        result.put("city", city);
        result.put("country", country != null ? country : "Unknown");
        result.put("forecast_period", "5 days");

        // Générer des prévisions pour 5 jours
        Map<String, Object>[] dailyForecasts = new Map[5];

        LocalDateTime today = LocalDateTime.now();
        String[] conditions = {"Clear", "Clouds", "Rain", "Drizzle", "Thunderstorm", "Snow", "Mist"};

        for (int i = 0; i < 5; i++) {
            Map<String, Object> day = new HashMap<>();
            LocalDateTime forecastDate = today.plusDays(i);

            day.put("date", forecastDate.format(DateTimeFormatter.ISO_DATE));

            // Température qui varie légèrement chaque jour
            double baseTemp = 15 + (random.nextDouble() * 20);
            day.put("temp_max", Math.round((baseTemp + random.nextDouble() * 5) * 10.0) / 10.0);
            day.put("temp_min", Math.round((baseTemp - random.nextDouble() * 5) * 10.0) / 10.0);
            day.put("humidity", 40 + random.nextInt(50));
            day.put("weather_condition", conditions[random.nextInt(conditions.length)]);

            dailyForecasts[i] = day;
        }

        result.put("daily", dailyForecasts);

        return ResponseEntity.ok(result);
    }
}