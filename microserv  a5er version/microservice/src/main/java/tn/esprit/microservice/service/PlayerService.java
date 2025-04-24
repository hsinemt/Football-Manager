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
}
