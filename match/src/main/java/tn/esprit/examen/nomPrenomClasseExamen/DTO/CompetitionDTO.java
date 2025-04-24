package tn.esprit.examen.nomPrenomClasseExamen.DTO;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
public class CompetitionDTO {
    private UUID id;
    private String nom;
    private String saison;
    private LocalDate dateDebut;
    private LocalDate dateFin;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getSaison() {
        return saison;
    }

    public void setSaison(String saison) {
        this.saison = saison;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public List<EquipeInfo> getEquipes() {
        return equipes;
    }

    public void setEquipes(List<EquipeInfo> equipes) {
        this.equipes = equipes;
    }

    private List<EquipeInfo> equipes;
}

