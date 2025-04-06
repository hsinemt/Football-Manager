package tn.esprit.microservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.microservice.entity.Competition;
import tn.esprit.microservice.service.CompetitionService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/competitions")
public class CompetitionController {
    private final CompetitionService competitionService;

    @Autowired
    public CompetitionController(CompetitionService competitionService) {
        this.competitionService = competitionService;
    }

    @GetMapping
    public ResponseEntity<List<Competition>> getAllCompetitions() {
        List<Competition> competitions = competitionService.getAllCompetitions();
        return ResponseEntity.ok(competitions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Competition> getCompetitionById(@PathVariable UUID id) {
        return competitionService.getCompetitionById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> addCompetition(
            @RequestBody Competition competition,
            @RequestParam("telephone") String telephone,
            @RequestParam("email") String email) {
        try {
            Competition savedCompetition = competitionService.addCompetition(competition, telephone, email);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedCompetition);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Competition> updateCompetition(@PathVariable UUID id, @RequestBody Competition competition) {
        Competition updatedCompetition = competitionService.updateCompetition(id, competition);
        if (updatedCompetition != null) {
            return ResponseEntity.ok(updatedCompetition);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompetition(@PathVariable UUID id) {
        competitionService.deleteCompetition(id);
        return ResponseEntity.noContent().build();
    }
}