package tn.esprit.microservice.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "game_match")

public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private LocalDateTime date;

    @Column(name = "equipe_domicile_id")
    private Long equipeDomicileId;

    @Column(name = "equipe_exterieur_id")
    private Long equipeExterieurId;

    private int scoreDomicile;
    private int scoreExterieur;
    private  String city;

}
