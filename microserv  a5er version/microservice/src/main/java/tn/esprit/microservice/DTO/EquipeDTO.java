package tn.esprit.microservice.DTO;

import lombok.Data;

@Data
public class EquipeDTO {
    private Long id;
    private String nom;
    private String entraineur;
    private String stade;
    private String ville;
    private String pays;
    private Integer victoires;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEntraineur() {
        return entraineur;
    }

    public void setEntraineur(String entraineur) {
        this.entraineur = entraineur;
    }

    public String getStade() {
        return stade;
    }

    public void setStade(String stade) {
        this.stade = stade;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
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

    public Integer getButsMarques() {
        return butsMarques;
    }

    public void setButsMarques(Integer butsMarques) {
        this.butsMarques = butsMarques;
    }

    public Integer getButsEncaissees() {
        return butsEncaissees;
    }

    public void setButsEncaissees(Integer butsEncaissees) {
        this.butsEncaissees = butsEncaissees;
    }

    private Integer nuls;
    private Integer defaites;
    private Integer butsMarques;
    private Integer butsEncaissees;
}
