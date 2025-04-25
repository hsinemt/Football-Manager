package tn.esprit.microservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.microservice.entity.Competition;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.UUID;

public interface CompetitionRepository extends JpaRepository<Competition, UUID> {
    List<Competition> findByNomContainingIgnoreCase(String nom);
    List<Competition> findBySaisonContainingIgnoreCase(String saison);
    List<Competition> findByNomContainingIgnoreCaseAndSaisonContainingIgnoreCase(String nom, String saison, Sort sort);
}
