package tn.esprit.examen.nomPrenomClasseExamen.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.esprit.examen.nomPrenomClasseExamen.Client.ManagerClient;
import tn.esprit.examen.nomPrenomClasseExamen.DTO.EquipeDTO;
import tn.esprit.examen.nomPrenomClasseExamen.DTO.EquipeInfo;
import tn.esprit.examen.nomPrenomClasseExamen.DTO.EquipeMapper;
import tn.esprit.examen.nomPrenomClasseExamen.entities.Equipe;
import tn.esprit.examen.nomPrenomClasseExamen.repositories.IEquipeRepository;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class ServicesImpl implements IServices {
    private final ManagerClient managerClient;

    @Autowired
    private IEquipeRepository equipeRepository;


    @Override
    public Equipe addEquipe(Equipe equipe) {
        return equipeRepository.save(equipe);
    }

    @Override
    public Equipe updateEquipe(Equipe equipe) {return equipeRepository.save(equipe);}

    @Override
    public void deleteEquipe(Long id) {
        equipeRepository.deleteById(id);
    }

    @Override
    public Equipe getEquipeById(Long id) {
        return equipeRepository.findById(id).orElse(null);
    }

    @Override
    public List<Equipe> getAllEquipes() {
        return equipeRepository.findAll();
    }

    @Override
    public List<Equipe> getEquipesSortedByVictoires() {
        return equipeRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(Equipe::getVictoires).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public Equipe updateStats(Long id, int victoires, int nuls, int defaites, int butsMarques, int butsEncaissees) {

        Equipe equipe = equipeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Equipe not found"));

        equipe.setVictoires(equipe.getVictoires() + victoires);
        equipe.setNuls(equipe.getNuls() + nuls);
        equipe.setDefaites(equipe.getDefaites() + defaites);
        equipe.setButs_marques(equipe.getButs_marques() + butsMarques);
        equipe.setButs_encaissees(equipe.getButs_encaissees() + butsEncaissees);

        return equipeRepository.save(equipe);
    }

    @Override
    public EquipeDTO createEquipe(EquipeDTO dto) {
        Equipe equipe = EquipeMapper.toEntity(dto);
        Equipe saved = equipeRepository.save(equipe);
        return EquipeMapper.toDTO(saved);
    }

    @Override
    public List<EquipeInfo> getEquipesByCompetition(UUID competitionId) {
        return equipeRepository.findByCompetitionId(competitionId).stream()
                .sorted(Comparator.comparing(Equipe::getPoints).reversed())
                .map(equipe -> {
                    EquipeInfo info = new EquipeInfo();
                    info.setId(equipe.getId());
                    info.setNom(equipe.getNom());
                    info.setPoints(equipe.getVictoires() * 3 + equipe.getNuls());
                    info.setVictoires(equipe.getVictoires());
                    info.setNuls(equipe.getNuls());
                    info.setDefaites(equipe.getDefaites());
                    return info;
                })
                .collect(Collectors.toList());
    }

    @Override
    public EquipeDTO createEquipeWithManager(EquipeDTO equipeDTO) {


        Equipe equipe = new Equipe();
        equipe.setNom(equipeDTO.getNom());
        equipe.setManagerId(equipeDTO.getManagerId());
        equipe.setStade(equipeDTO.getStade());
        equipe.setVille(equipeDTO.getVille());
        equipe.setPays(equipeDTO.getPays());
        equipe.setCompetitionId(equipeDTO.getCompetitionId());
        equipe.setVictoires(equipeDTO.getVictoires());
        equipe.setNuls(equipeDTO.getNuls());
        equipe.setDefaites(equipeDTO.getDefaites());
        equipe.setButs_marques(equipeDTO.getButs_marques());
        equipe.setButs_encaissees(equipeDTO.getButs_encaissees());
        Equipe savedEquipe = equipeRepository.save(equipe);

        // Assigner l'équipe au manager ET le manager à l'équipe
        managerClient.assignTeamToManager(
                equipeDTO.getManagerId(),
                savedEquipe.getId().toString());


        return convertToDTO(savedEquipe);
    }

    private EquipeDTO convertToDTO(Equipe equipe) {
        EquipeDTO dto = new EquipeDTO();
        dto.setId(equipe.getId());
        dto.setNom(equipe.getNom());
        dto.setManagerId(equipe.getManagerId());
        dto.setStade(equipe.getStade());
        dto.setVille(equipe.getVille());
        dto.setPays(equipe.getPays());
        dto.setVictoires(equipe.getVictoires());
        dto.setNuls(equipe.getNuls());
        dto.setDefaites(equipe.getDefaites());
        dto.setButs_marques(equipe.getButs_marques());
        dto.setButs_encaissees(equipe.getButs_encaissees());
        dto.setCompetitionId(equipe.getCompetitionId());
        return dto;
    }
}



