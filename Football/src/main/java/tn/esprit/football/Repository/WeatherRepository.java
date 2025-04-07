package tn.esprit.football.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tn.esprit.football.Entity.Weather;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface WeatherRepository extends JpaRepository<Weather, Long> {
    List<Weather> findByZoneNameOrderByTimestampDesc(String zoneName);

    @Query("SELECT w FROM Weather w WHERE w.zoneName = ?1 AND w.timestamp = (SELECT MAX(w2.timestamp) FROM Weather w2 WHERE w2.zoneName = ?1)")
    Optional<Weather> findLatestByZoneName(String zoneName);

    List<Weather> findByZoneNameAndTimestampBetween(String zoneName, LocalDateTime start, LocalDateTime end);
}