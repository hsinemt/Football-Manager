package tn.esprit.microservice.service;

import org.springframework.stereotype.Service;
import tn.esprit.microservice.entity.Player;
import tn.esprit.microservice.repository.PlayerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {
        private final PlayerRepository playerRepository;

        public PlayerService(PlayerRepository playerRepository) {
            this.playerRepository = playerRepository;
        }

        public List<Player> getAllPlayers() {
            return playerRepository.findAll();
        }

        public Optional<Player> getPlayerById(String id) {
            return playerRepository.findById(id);
        }

        public Player addPlayer(Player player) {
            return playerRepository.save(player);
        }

        public Player updatePlayer(String id, Player player) {
            return playerRepository.findById(id).map(p -> {
                p.setNom(player.getNom());
                p.setPoste(player.getPoste());
                p.setAge(player.getAge());
                return playerRepository.save(p);
            }).orElse(null);
        }

        public void deletePlayer(String id) {
            playerRepository.deleteById(id);
        }
    public double calculerMoyenneButsParMatch(Player player) {
        return (double) player.getButs() / player.getMatchsJoues();
    }


    public List<Player> searchPlayers(String nom, String poste, Integer minAge, Integer maxAge, String nationalite) {
        if (nom != null) {
            return playerRepository.findByNomContaining(nom);
        } else if (poste != null) {
            return playerRepository.findByPoste(poste);
        } else if (minAge != null && maxAge != null) {
            return playerRepository.findByAgeBetween(minAge, maxAge);
        } else if (nationalite != null) {
            return playerRepository.findByNationalite(nationalite);
        }
        return playerRepository.findAll();
    }
    public List<Player> getTopPlayers() {
        List<Player> players = playerRepository.findAll();
        return players.stream()
                .sorted((p1, p2) -> Double.compare(
                        calculerScoreGlobal(p2),
                        calculerScoreGlobal(p1)))
                .toList();
    }

    public double calculerScoreGlobal(Player player) {
        return player.getButs() * 2
                + player.getPassesDecisives()
                - player.getCartonsJaunes() * 0.5
                - player.getCartonsRouges();
    }
    public List<Player> getPlayersWithMinMatchs(int minMatchs) {
        return playerRepository.findAll()
                .stream()
                .filter(p -> p.getMatchsJoues() >= minMatchs)
                .toList();
    }

}
