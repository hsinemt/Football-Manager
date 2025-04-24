package tn.esprit.examen.nomPrenomClasseExamen.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.examen.nomPrenomClasseExamen.DTO.EquipeDTO;
import tn.esprit.examen.nomPrenomClasseExamen.entities.Equipe;

import java.util.List;
import java.util.UUID;

public interface IEquipeRepository extends JpaRepository<Equipe, Long> {
    List<Equipe> findByCompetitionId(UUID competitionId); // Add this method

}