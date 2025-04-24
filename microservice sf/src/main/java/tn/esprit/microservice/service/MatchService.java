package tn.esprit.microservice.service;

import org.springframework.stereotype.Service;
import tn.esprit.microservice.entity.Match;
import tn.esprit.microservice.repository.MatchRepository;

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
}
