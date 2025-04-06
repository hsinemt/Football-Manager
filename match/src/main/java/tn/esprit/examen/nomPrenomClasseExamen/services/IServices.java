package tn.esprit.examen.nomPrenomClasseExamen.services;

import tn.esprit.examen.nomPrenomClasseExamen.entities.Equipe;

import java.util.List;

public interface IServices {
    Equipe addEquipe(Equipe equipe);
    Equipe updateEquipe(Equipe equipe);
    void deleteEquipe(Long id);
    Equipe getEquipeById(Long id);
    List<Equipe> getAllEquipes();

}
