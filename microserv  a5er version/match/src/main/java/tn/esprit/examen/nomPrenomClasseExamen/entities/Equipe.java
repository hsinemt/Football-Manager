package tn.esprit.examen.nomPrenomClasseExamen.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
@Entity
public class Equipe implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)

    private Long id;

    private String nom;
    private String managerId;
    private String stade;
    private String ville;
    private String pays;
    private Integer victoires;
    private Integer nuls;
    private Integer defaites;
    private Integer buts_marques;
    private Integer buts_encaissees;
    private UUID competitionId;
    @Transient // Ne sera pas persisté en base
    private Integer points;

    @PostLoad // Calcul automatique après chargement
    private void calculatePoints() {
        this.points = victoires * 3 + nuls * 1;
    }

}
