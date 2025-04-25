package tn.esprit.examen.nomPrenomClasseExamen.DTO;

import lombok.Builder;
import tn.esprit.examen.nomPrenomClasseExamen.entities.Equipe;
@Builder
public class EquipeMapper {
    public static Equipe toEntity(EquipeDTO dto) {
        Equipe equipe = new Equipe();
        equipe.setNom(dto.getNom());
        equipe.setStade(dto.getStade());
        equipe.setVille(dto.getVille());
        equipe.setPays(dto.getPays());
        equipe.setManagerId(dto.getManagerId());
        equipe.setVictoires(dto.getVictoires());
        equipe.setNuls(dto.getNuls());
        equipe.setDefaites(dto.getDefaites());
        equipe.setButs_marques(dto.getButs_marques());
        equipe.setButs_encaissees(dto.getButs_encaissees());
        return equipe;
    }

    public static EquipeDTO toDTO(Equipe entity) {
        EquipeDTO dto = new EquipeDTO();
        dto.setId(entity.getId());
        dto.setNom(entity.getNom());
        dto.setStade(entity.getStade());
        dto.setVille(entity.getVille());
        dto.setPays(entity.getPays());
        dto.setManagerId(entity.getManagerId());
        dto.setVictoires(entity.getVictoires());
        dto.setNuls(entity.getNuls());
        dto.setDefaites(entity.getDefaites());
        dto.setButs_marques(entity.getButs_marques());
        dto.setButs_encaissees(entity.getButs_encaissees());
        return dto;
    }

}
