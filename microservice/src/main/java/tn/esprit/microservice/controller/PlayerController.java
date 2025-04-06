package tn.esprit.microservice.controller;

import org.springframework.web.bind.annotation.*;
import tn.esprit.microservice.entity.Player;
import tn.esprit.microservice.service.PlayerService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/players")
public class PlayerController {
    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping
    public List<Player> getAllPlayers() {
        return playerService.getAllPlayers();
    }

    @GetMapping("/serch/{id}")
    public Optional<Player> getPlayerById(@PathVariable String id) {
        return playerService.getPlayerById(id);
    }

    @PostMapping("/addPlayer")
    public Player addPlayer(@RequestBody Player player) {
        return playerService.addPlayer(player);

    }

    @PutMapping("/{id}")
    public Player updatePlayer(@PathVariable String id, @RequestBody Player player) {
        return playerService.updatePlayer(id, player);
    }

    @DeleteMapping("/{id}")
    public void deletePlayer(@PathVariable String id) {
        playerService.deletePlayer(id);
    }
}
