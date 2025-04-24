package tn.esprit.examen.nomPrenomClasseExamen.services;

import tn.esprit.examen.nomPrenomClasseExamen.DTO.EquipeDTO;
import tn.esprit.examen.nomPrenomClasseExamen.DTO.EquipeInfo;
import tn.esprit.examen.nomPrenomClasseExamen.entities.Equipe;

import java.util.List;
import java.util.UUID;

public interface IServices {
    Equipe addEquipe(Equipe equipe);

    Equipe updateEquipe(Equipe equipe);

    void deleteEquipe(Long id);

    Equipe getEquipeById(Long id);

    List<Equipe> getAllEquipes();

    List<Equipe> getEquipesSortedByVictoires();
    Equipe updateStats (Long id,
                        int victoires,
                        int nuls,
                        int defaites,
                        int butsMarques,
                        int butsEncaissees);

    EquipeDTO createEquipe(EquipeDTO dto);
    List<EquipeInfo> getEquipesByCompetition(UUID competitionId);
    EquipeDTO createEquipeWithManager(EquipeDTO equipeDTO);
}
