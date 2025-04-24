package tn.esprit.examen.nomPrenomClasseExamen.DTO;


import lombok.Data;
import tn.esprit.examen.nomPrenomClasseExamen.entities.Equipe;

import java.util.UUID;

@Data
public class EquipeDTO {
    private Long id;
    private UUID competitionId;
    private String nom;
    private Integer victoires;
    private Integer nuls;
    private Integer defaites;
    private Integer buts_marques;
    private Integer buts_encaissees;
    private String stade;
    private String ville;
    private String pays;
    private String managerId;
    private Integer points;


    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }




    public Integer getVictoires() {
        return victoires;
    }

    public void setVictoires(Integer victoires) {
        this.victoires = victoires;
    }

    public Integer getNuls() {
        return nuls;
    }

    public void setNuls(Integer nuls) {
        this.nuls = nuls;
    }

    public Integer getDefaites() {
        return defaites;
    }

    public void setDefaites(Integer defaites) {
        this.defaites = defaites;
    }

    public Integer getButs_marques() {
        return buts_marques;
    }

    public void setButs_marques(Integer buts_marques) {
        this.buts_marques = buts_marques;
    }

    public Integer getButs_encaissees() {
        return buts_encaissees;
    }

    public void setButs_encaissees(Integer buts_encaissees) {
        this.buts_encaissees = buts_encaissees;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getCompetitionId() {
        return competitionId;
    }

    public void setCompetitionId(UUID competitionId) {
        this.competitionId = competitionId;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

}
