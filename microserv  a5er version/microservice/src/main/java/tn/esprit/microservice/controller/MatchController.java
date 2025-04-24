package tn.esprit.microservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import tn.esprit.microservice.DTO.MatchDTO;
import tn.esprit.microservice.entity.Match;
import tn.esprit.microservice.entity.WeatherResponse;
import tn.esprit.microservice.repository.MatchRepository;
import tn.esprit.microservice.service.MatchService;
import tn.esprit.microservice.service.WeatherService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.http.MediaType;
@Tag(name = "Gestion match", description = "Contrôleur pour la gestion des match")
@RestController
@RequestMapping("/matches")
public class MatchController {
    private final MatchService matchService;
    private MatchRepository ma;
    private final WeatherService weatherService;
    public MatchController(MatchService matchService,WeatherService weatherService) {
        this.matchService = matchService;
        this.weatherService = weatherService;
    }

    // Get all matches
    @Operation(description = "Get all matches")
    @GetMapping("/all")
    public List<Match> getAllMatches() {
        return matchService.getAllMatches();
    }

    // Get match by ID
    @Operation(description = "Get all matches")
    @GetMapping("/{id}")
    public Optional<Match> getMatchById(@PathVariable int id) {
        return matchService.getMatchById(id);
    }

    // Add a new match
    @Operation(description = "Get all matches")
    @PostMapping
    public Match addMatch(@RequestBody Match match) {
        return matchService.addMatch(match);
    }

    // Update match details
    @Operation(description = "Update match details")
    @PutMapping("/{id}")
    public Match updateMatch(@PathVariable int id, @RequestBody Match match) {
        return matchService.updateMatch(id, match);
    }

    // Delete match by ID
    @Operation(description = "Update match details")
    @DeleteMapping("/{id}")
    public void deleteMatch(@PathVariable int id) {
        matchService.deleteMatch(id);
    }
    @Operation(description = "searsh match")
    @GetMapping("/search")
    public List<Match> searchMatches(
            @RequestParam(required = false) LocalDateTime date,
            @RequestParam(required = false) Integer equipeDomicileId,
            @RequestParam(required = false) Integer equipeExterieurId
    ) {
        return matchService.searchMatches(date, equipeDomicileId, equipeExterieurId);
    }
    @GetMapping("/result/{id}")
    public String getMatchResult(@PathVariable int id) {
        return matchService.getMatchResult(id);
    }

    @GetMapping(value = "/{id}/weather", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody WeatherResponse getWeatherForMatch(@PathVariable int id) {
        Match match = matchService.getMatchById(id)
                .orElseThrow(() -> new RuntimeException("Match not found"));

        return weatherService.getWeather(match.getCity());
    }

        @Value("${welcome.message}")
        private String welcomeMessage;

        @GetMapping("/welcome")
        public String welcome() {
            return welcomeMessage;
        }

    @PutMapping("/{id}/finalize")
    public MatchDTO finalizeMatch(@PathVariable int id) {
        return matchService.finalizeMatch(id);
    }

}
