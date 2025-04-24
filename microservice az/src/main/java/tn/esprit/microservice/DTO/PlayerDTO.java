package tn.esprit.microservice.DTO;

public class PlayerDTO {
    private String id;
    private String nom;
    private String poste;
    private int age;
    private String nationalite;
    private int buts;
    private int passesDecisives;
    private int cartonsJaunes;
    private int cartonsRouges;
    private int matchsJoues;
    private Long equipeId;

    public PlayerDTO() {}

    public PlayerDTO(String id, String nom, String poste, int age, String nationalite,
                     int buts, int passesDecisives, int cartonsJaunes,
                     int cartonsRouges, int matchsJoues) {
        this.id = id;
        this.nom = nom;
        this.poste = poste;
        this.age = age;
        this.nationalite = nationalite;
        this.buts = buts;
        this.passesDecisives = passesDecisives;
        this.cartonsJaunes = cartonsJaunes;
        this.cartonsRouges = cartonsRouges;
        this.matchsJoues = matchsJoues;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPoste() {
        return poste;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getNationalite() {
        return nationalite;
    }

    public void setNationalite(String nationalite) {
        this.nationalite = nationalite;
    }

    public int getButs() {
        return buts;
    }

    public void setButs(int buts) {
        this.buts = buts;
    }

    public int getPassesDecisives() {
        return passesDecisives;
    }

    public void setPassesDecisives(int passesDecisives) {
        this.passesDecisives = passesDecisives;
    }

    public int getCartonsJaunes() {
        return cartonsJaunes;
    }

    public void setCartonsJaunes(int cartonsJaunes) {
        this.cartonsJaunes = cartonsJaunes;
    }

    public int getCartonsRouges() {
        return cartonsRouges;
    }

    public void setCartonsRouges(int cartonsRouges) {
        this.cartonsRouges = cartonsRouges;
    }

    public int getMatchsJoues() {
        return matchsJoues;
    }

    public Long getEquipeId() {
        return equipeId;
    }

    public void setEquipeId(Long equipeId) {
        this.equipeId = equipeId;
    }

    public void setMatchsJoues(int matchsJoues) {
        this.matchsJoues = matchsJoues;
    }
}
