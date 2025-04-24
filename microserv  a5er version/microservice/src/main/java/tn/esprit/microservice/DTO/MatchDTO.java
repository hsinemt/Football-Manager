package tn.esprit.microservice.DTO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MatchDTO {
    private int id;
    private LocalDateTime date;
    private int equipeDomicileId;
    private int equipeExterieurId;
    private int scoreDomicile;
    private int scoreExterieur;
    private String city;
}
