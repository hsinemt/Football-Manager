package tn.esprit.microservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.microservice.entity.Match;


public interface MatchRepository extends JpaRepository<Match, Integer> {
}
