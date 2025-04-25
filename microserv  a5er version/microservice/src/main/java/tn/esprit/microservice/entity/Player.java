package tn.esprit.microservice.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "players")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
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
}
