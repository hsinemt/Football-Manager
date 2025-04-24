package tn.esprit.microservice.DTO;

import tn.esprit.microservice.entity.Competition;

import java.util.List;

public class CompetitionMapper {
    public static Competition toEntity(CompetitionDTO dto) {
        Competition competition = new Competition();
        competition.setId(dto.getId());
        competition.setNom(dto.getNom());
        competition.setSaison(dto.getSaison());
        competition.setDateDebut(dto.getDateDebut());
        competition.setDateFin(dto.getDateFin());
        return competition;
    }
    public static CompetitionDTO toDTO(Competition entity) {
        CompetitionDTO dto = new CompetitionDTO();
        dto.setId(entity.getId());
        dto.setNom(entity.getNom());
        dto.setSaison(entity.getSaison());
        dto.setDateDebut(entity.getDateDebut());
        dto.setDateFin(entity.getDateFin());
        return dto;
    }
    public static CompetitionDTO toDTOWithEquipes(Competition entity, List<EquipeInfo> equipes) {
        CompetitionDTO dto = toDTO(entity);
        dto.setEquipes(equipes);
        return dto;
    }
}
