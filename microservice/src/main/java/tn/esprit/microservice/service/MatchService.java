package tn.esprit.microservice.service;

import org.springframework.stereotype.Service;
import tn.esprit.microservice.entity.Match;
import tn.esprit.microservice.repository.MatchRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MatchService {
    private final MatchRepository matchRepository;

    public MatchService(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    public List<Match> getAllMatches() {
        return matchRepository.findAll();
    }

    public Optional<Match> getMatchById(int id) {
        return matchRepository.findById(id);
    }

    public Match addMatch(Match match) {
        return matchRepository.save(match);
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

}
