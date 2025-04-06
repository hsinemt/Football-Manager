package tn.esprit.microservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.microservice.entity.Competition;
import java.util.UUID;

public interface CompetitionRepository extends JpaRepository<Competition, UUID> {
}
