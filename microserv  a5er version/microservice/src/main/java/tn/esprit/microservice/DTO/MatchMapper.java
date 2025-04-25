package tn.esprit.microservice.DTO;

import tn.esprit.microservice.entity.Match;

public class MatchMapper {
    public static Match toEntity(MatchDTO dto) {
        Match match = new Match();
        match.setId(dto.getId());
        match.setDate(dto.getDate());
        match.setEquipeDomicileId((long) dto.getEquipeDomicileId());
        match.setEquipeExterieurId((long) dto.getEquipeExterieurId());
        match.setScoreDomicile(dto.getScoreDomicile());
        match.setScoreExterieur(dto.getScoreExterieur());
        match.setCity(dto.getCity());
        return match;
    }

    public static MatchDTO toDTO(Match entity) {
        MatchDTO dto = new MatchDTO();
        dto.setId(entity.getId());
        dto.setDate(entity.getDate());
        dto.setEquipeDomicileId(Math.toIntExact(entity.getEquipeDomicileId()));
        dto.setEquipeExterieurId(Math.toIntExact(entity.getEquipeExterieurId()));
        dto.setScoreDomicile(entity.getScoreDomicile());
        dto.setScoreExterieur(entity.getScoreExterieur());
        dto.setCity(entity.getCity());
        return dto;
    }
}
