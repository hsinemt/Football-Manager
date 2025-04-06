package tn.esprit.microservice.controller;

import org.springframework.web.bind.annotation.*;
import tn.esprit.microservice.entity.Match;
import tn.esprit.microservice.service.MatchService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/matches")
public class MatchController {
    private final MatchService matchService;

    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    // Get all matches
    @GetMapping("/all")
    public List<Match> getAllMatches() {
        return matchService.getAllMatches();
    }

    // Get match by ID
    @GetMapping("/{id}")
    public Optional<Match> getMatchById(@PathVariable int id) {
        return matchService.getMatchById(id);
    }

    // Add a new match
    @PostMapping
    public Match addMatch(@RequestBody Match match) {
        return matchService.addMatch(match);
    }

    // Update match details
    @PutMapping("/{id}")
    public Match updateMatch(@PathVariable int id, @RequestBody Match match) {
        return matchService.updateMatch(id, match);
    }

    // Delete match by ID
    @DeleteMapping("/{id}")
    public void deleteMatch(@PathVariable int id) {
        matchService.deleteMatch(id);
    }
}
