package tn.esprit.examen.nomPrenomClasseExamen.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

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
    private String entraineur;
    private String stade;
    private String ville;
    private String pays;
    private Integer victoires;
    private Integer nuls;
    private Integer defaites;
    private Integer buts_marques;
    private Integer buts_encaissees;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
