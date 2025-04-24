package tn.esprit.examen.nomPrenomClasseExamen.DTO;

import lombok.Data;

import java.util.List;

@Data
public class ManagerDTO {
    private String id;
    private String nom;
    private String prenom;
    private String equipe; // ID de l'équipe
    private Integer nb_match_carriere;
    private Integer nb_victoire;
    private Integer nb_titre;
    private List<String> historique;
}
