package tn.esprit.football.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tn.esprit.football.Entity.Weather;
import tn.esprit.football.Entity.Zone;
import tn.esprit.football.Repository.WeatherRepository;
import tn.esprit.football.Repository.ZoneRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class WeatherServiceImpl implements InterfaceWeatherService {

    @Value("${openweathermap.api.key}")
    private String apiKey;

    @Autowired
    private ZoneRepository zoneRepository;

    @Autowired
    private WeatherRepository weatherRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<Zone> getAllZones() {
        return zoneRepository.findAll();
    }

    @Override
    public Optional<Zone> getZoneById(Long id) {
        return zoneRepository.findById(id);
    }

    @Override
    public Optional<Zone> getZoneByName(String name) {
        return zoneRepository.findByName(name);
    }

    @Override
    public Optional<Zone> getZoneByCountryAndCity(String country, String city) {
        return zoneRepository.findByCountryAndCity(country, city);
    }

    @Override
    public Zone saveZone(Zone zone) {
        return zoneRepository.save(zone);
    }

    @Override
    public void deleteZone(Long id) {
        zoneRepository.deleteById(id);
    }

    @Override
    public Weather getCurrentWeather(String zoneName) {
        Optional<Zone> zoneOpt = zoneRepository.findByName(zoneName);
        if (!zoneOpt.isPresent()) {
            throw new RuntimeException("Zone not found with name: " + zoneName);
        }

        return weatherRepository.findLatestByZoneName(zoneName)
                .orElseThrow(() -> new RuntimeException("No weather data available for zone: " + zoneName));
    }

    @Override
    public Weather getWeatherByLocation(String country, String city) {
        // Chercher d'abord si la zone existe déjà
        Optional<Zone> zoneOpt = zoneRepository.findByCountryAndCity(country, city);
        Zone zone;

        if (zoneOpt.isPresent()) {
            zone = zoneOpt.get();
        } else {
            // Créer une nouvelle zone si elle n'existe pas
            zone = new Zone();
            zone.setName(city + ", " + country);
            zone.setCountry(country);
            zone.setCity(city);
            // Nous ne définissons pas les coordonnées ici, car OpenWeatherMap peut fonctionner avec le nom de la ville
            zone = zoneRepository.save(zone);
        }

        // Récupérer les données météo en temps réel
        return fetchRealTimeWeatherData(zone);
    }

    @Override
    public List<Weather> getWeatherHistory(String zoneName) {
        Optional<Zone> zoneOpt = zoneRepository.findByName(zoneName);
        if (!zoneOpt.isPresent()) {
            throw new RuntimeException("Zone not found with name: " + zoneName);
        }

        return weatherRepository.findByZoneNameOrderByTimestampDesc(zoneName);
    }

    @Override
    public List<Weather> getWeatherForPeriod(String zoneName, LocalDateTime start, LocalDateTime end) {
        Optional<Zone> zoneOpt = zoneRepository.findByName(zoneName);
        if (!zoneOpt.isPresent()) {
            throw new RuntimeException("Zone not found with name: " + zoneName);
        }

        return weatherRepository.findByZoneNameAndTimestampBetween(zoneName, start, end);
    }

    @Override
    public Weather updateWeatherData(String zoneName, Weather weatherData) {
        Optional<Zone> zoneOpt = zoneRepository.findByName(zoneName);
        if (!zoneOpt.isPresent()) {
            throw new RuntimeException("Zone not found with name: " + zoneName);
        }

        weatherData.setZoneName(zoneName);
        weatherData.setTimestamp(LocalDateTime.now());

        return weatherRepository.save(weatherData);
    }

    @Override
    @Scheduled(fixedRate = 3600000) // 1 heure en millisecondes
    public void refreshAllWeatherData() {
        List<Zone> allZones = zoneRepository.findAll();

        for (Zone zone : allZones) {
            fetchRealTimeWeatherData(zone);
        }
    }

    private Weather fetchRealTimeWeatherData(Zone zone) {
        try {
            String url;
            if (zone.getLatitude() != null && zone.getLongitude() != null) {
                // Si on a les coordonnées, on les utilise pour plus de précision
                url = String.format(
                        "https://api.openweathermap.org/data/2.5/weather?lat=%f&lon=%f&units=metric&appid=%s",
                        zone.getLatitude(), zone.getLongitude(), apiKey
                );
            } else {
                // Sinon on utilise le nom de la ville et du pays
                url = String.format(
                        "https://api.openweathermap.org/data/2.5/weather?q=%s,%s&units=metric&appid=%s",
                        zone.getCity(), zone.getCountry(), apiKey
                );
            }

            // Appel à l'API OpenWeatherMap
            Map<String, Object> response = restTemplate.getForObject(url, Map.class);

            // Extraction des données
            Map<String, Object> main = (Map<String, Object>) response.get("main");
            Map<String, Object> wind = (Map<String, Object>) response.get("wind");
            List<Map<String, Object>> weatherList = (List<Map<String, Object>>) response.get("weather");

            Double temperature = ((Number) main.get("temp")).doubleValue();
            Double humidity = ((Number) main.get("humidity")).doubleValue();
            Double windSpeed = ((Number) wind.get("speed")).doubleValue();
            String weatherCondition = (String) weatherList.get(0).get("main");

            // Création et sauvegarde de l'objet Weather
            Weather weather = new Weather();
            weather.setZoneName(zone.getName());
            weather.setTemperature(temperature);
            weather.setHumidity(humidity);
            weather.setWindSpeed(windSpeed);
            weather.setWeatherCondition(weatherCondition);
            weather.setTimestamp(LocalDateTime.now());

            return weatherRepository.save(weather);

        } catch (Exception e) {
            System.err.println("Error fetching weather data: " + e.getMessage());
            // En cas d'erreur, on retourne null ou on pourrait générer des données aléatoires comme fallback
            return null;
        }
    }
}