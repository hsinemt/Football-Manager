package tn.esprit.microservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.microservice.entity.Player;

import java.util.List;

public interface PlayerRepository extends JpaRepository<Player, String> {
    List<Player> findByNomContaining(String nom);
    List<Player> findByPoste(String poste);
    List<Player> findByAgeBetween(int startAge, int endAge);
    List<Player> findByNationalite(String nationalite);
}
