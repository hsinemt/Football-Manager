package tn.esprit.microservice.controller;

import org.springframework.web.bind.annotation.*;
import tn.esprit.microservice.entity.Player;
import tn.esprit.microservice.service.PlayerService;
import tn.esprit.microservice.service.QuoteService;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/players")
public class PlayerController {
    private final PlayerService playerService;
    private final QuoteService quoteService;

    public PlayerController(PlayerService playerService, QuoteService quoteService) {
        this.playerService = playerService;
        this.quoteService = quoteService;
    }

    @GetMapping("/all")
    public List<Player> getAllPlayers() {
        return playerService.getAllPlayers();
    }

    @GetMapping("/search/{id}")
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

    @GetMapping("/stats/{id}")
    public double getPlayerStats(@PathVariable String id) {
        Optional<Player> playerOpt = playerService.getPlayerById(id);
        if (playerOpt.isPresent()) {
            return playerService.calculerMoyenneButsParMatch(playerOpt.get());
        }
        return 0.0;
    }

    @GetMapping("/search")
    public List<Player> searchPlayers(
            @RequestParam(required = false) String nom,
            @RequestParam(required = false) String poste,
            @RequestParam(required = false) Integer minAge,
            @RequestParam(required = false) Integer maxAge,
            @RequestParam(required = false) String nationalite
    ) {
        return playerService.searchPlayers(nom, poste, minAge, maxAge, nationalite);
    }

    @GetMapping("/sport")
    public String getSportsQuote() {
        return quoteService.getRandomSportsQuote();
    }

    @GetMapping("/motivation")
    public String getMotivationalQuote() {
        return quoteService.getMotivationalQuote();
    }
}
