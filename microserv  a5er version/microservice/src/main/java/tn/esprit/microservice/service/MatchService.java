package tn.esprit.microservice.service;

import org.springframework.stereotype.Service;
import tn.esprit.microservice.DTO.MatchDTO;
import tn.esprit.microservice.DTO.MatchMapper;
import tn.esprit.microservice.client.EquipeClient;
import tn.esprit.microservice.entity.Match;
import tn.esprit.microservice.repository.MatchRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MatchService {
    private final MatchRepository matchRepository;
    private final EquipeClient equipeClient;


    public MatchService(MatchRepository matchRepository , EquipeClient equipeClient) {
        this.matchRepository = matchRepository;
        this.equipeClient = equipeClient;
    }

    public List<Match> getAllMatches() {
        return matchRepository.findAll();
    }

    public Optional<Match> getMatchById(int id) {
        return matchRepository.findById(id);
    }

    public Match addMatch(Match match) {
        Match savedMatch = matchRepository.save(match);
        updateEquipeStats(savedMatch); // Mise à jour automatique des stats
        return savedMatch;
    }

    public Match updateMatch(int id, Match match) {
        return matchRepository.findById(id).map(m -> {
            m.setDate(match.getDate());
            m.setEquipeDomicileId(match.getEquipeDomicileId());
            m.setEquipeExterieurId(match.getEquipeExterieurId());
            m.setScoreDomicile(match.getScoreDomicile());
            m.setScoreExterieur(match.getScoreExterieur());
            return matchRepository.save(m);
        }).orElse(null);
    }

    public void deleteMatch(int id) {
        matchRepository.deleteById(id);
    }
    public List<Match> searchMatches(LocalDateTime date, Integer equipeDomicileId, Integer equipeExterieurId) {
        if (date != null && equipeDomicileId != null && equipeExterieurId != null) {
            return matchRepository.findByDateAndEquipeDomicileIdAndEquipeExterieurId(date, equipeDomicileId, equipeExterieurId);
        } else if (date != null) {
            return matchRepository.findByDate(date);
        } else if (equipeDomicileId != null) {
            return matchRepository.findByEquipeDomicileId(equipeDomicileId);
        } else if (equipeExterieurId != null) {
            return matchRepository.findByEquipeExterieurId(equipeExterieurId);
        } else {
            return matchRepository.findAll();
        }
    }
    public String getMatchResult(int matchId) {
        Optional<Match> matchOpt = matchRepository.findById(matchId);
        if (matchOpt.isPresent()) {
            Match match = matchOpt.get();
            if (match.getScoreDomicile() > match.getScoreExterieur()) {
                return "Team Domicile (Home) Wins";
            } else if (match.getScoreDomicile() < match.getScoreExterieur()) {
                return "Team Exterieur (Away) Wins";
            } else {
                return "It's a Draw";
            }
        } else {
            return "Match not found";
        }
    }
    public MatchDTO createMatch(MatchDTO matchDTO) {
        Match match = MatchMapper.toEntity(matchDTO);
        Match savedMatch = matchRepository.save(match);
        return MatchMapper.toDTO(savedMatch);
    }
    public MatchDTO finalizeMatch(int matchId) {
        Match match = matchRepository.findById(matchId)
                .orElseThrow(() -> new RuntimeException("Match not found"));

        updateEquipeStats(match);

        return MatchMapper.toDTO(match);
    }

    private void updateEquipeStats(Match match) {
        int butsDomicile = match.getScoreDomicile();
        int butsExterieur = match.getScoreExterieur();

        // Convertir les IDs en Long
        Long domicileId = (long) match.getEquipeDomicileId();
        Long exterieurId = (long) match.getEquipeExterieurId();

        if (butsDomicile > butsExterieur) {
            // Victoire domicile
            equipeClient.updateEquipeStats(domicileId, 1, 0, 0, butsDomicile, butsExterieur);
            equipeClient.updateEquipeStats(exterieurId, 0, 0, 1, butsExterieur, butsDomicile);
        } else if (butsDomicile < butsExterieur) {
            // Victoire extérieur
            equipeClient.updateEquipeStats(exterieurId, 1, 0, 0, butsExterieur, butsDomicile);
            equipeClient.updateEquipeStats(domicileId, 0, 0, 1, butsDomicile, butsExterieur);
        } else {
            // Match nul
            equipeClient.updateEquipeStats(domicileId, 0, 1, 0, butsDomicile, butsExterieur);
            equipeClient.updateEquipeStats(exterieurId, 0, 1, 0, butsExterieur, butsDomicile);
        }

    }
}
