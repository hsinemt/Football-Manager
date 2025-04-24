package tn.esprit.microservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.microservice.entity.Match;

import java.time.LocalDateTime;
import java.util.List;


public interface MatchRepository extends JpaRepository<Match, Integer> {
    List<Match> findByDate(LocalDateTime date);
    List<Match> findByEquipeDomicileId(Integer equipeDomicileId);
    List<Match> findByEquipeExterieurId(Integer equipeExterieurId);

    List<Match> findByDateAndEquipeDomicileIdAndEquipeExterieurId(LocalDateTime date, Integer equipeDomicileId, Integer equipeExterieurId);
}
