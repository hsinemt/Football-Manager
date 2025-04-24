package tn.esprit.microservice.DTO;

import lombok.Data;

@Data
public class EquipeInfo {
    private Long id;
    private String nom;
    private Integer points;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
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

    private Integer victoires;
    private Integer nuls;
    private Integer defaites;
}
