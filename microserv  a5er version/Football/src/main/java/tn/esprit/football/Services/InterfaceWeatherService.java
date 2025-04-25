package tn.esprit.football.Services;

import tn.esprit.football.Entity.Weather;
import tn.esprit.football.Entity.Zone;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface InterfaceWeatherService {
    // Opérations sur les zones
    List<Zone> getAllZones();
    Optional<Zone> getZoneById(Long id);
    Optional<Zone> getZoneByName(String name);
    Optional<Zone> getZoneByCountryAndCity(String country, String city);
    Zone saveZone(Zone zone);
    void deleteZone(Long id);

    // Opérations sur la météo
    Weather getCurrentWeather(String zoneName);
    Weather getWeatherByLocation(String country, String city);
    List<Weather> getWeatherHistory(String zoneName);
    List<Weather> getWeatherForPeriod(String zoneName, LocalDateTime start, LocalDateTime end);
    Weather updateWeatherData(String zoneName, Weather weatherData);

    // Mise à jour périodique des données météo
    void refreshAllWeatherData();
}