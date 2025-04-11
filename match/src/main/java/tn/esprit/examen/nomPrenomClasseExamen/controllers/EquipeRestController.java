package tn.esprit.examen.nomPrenomClasseExamen.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.examen.nomPrenomClasseExamen.entities.Equipe;
import tn.esprit.examen.nomPrenomClasseExamen.services.IServices;

import java.util.List;

@RestController
@RequestMapping("match")
public class EquipeRestController {

    private final IServices equipeService;

    public EquipeRestController(IServices equipeService) {
        this.equipeService = equipeService;
    }


    @PostMapping
    public Equipe addEquipe(@RequestBody Equipe equipe) {
        return equipeService.addEquipe(equipe);
    }

    @PutMapping("/{id}")
    public Equipe updateEquipe(@PathVariable Long id, @RequestBody Equipe equipe) {
        equipe.setId(id);
        return equipeService.updateEquipe(equipe);
    }

    @DeleteMapping("/{id}")
    public void deleteEquipe(@PathVariable Long id) {
        equipeService.deleteEquipe(id);
    }

    @GetMapping("/{id}")
    public Equipe getEquipeById(@PathVariable Long id) {
        return equipeService.getEquipeById(id);
    }

    @GetMapping
    public List<Equipe> getAllEquipes() {
        return equipeService.getAllEquipes();
    }

    @GetMapping("/sorted")
    public ResponseEntity<List<Equipe>> getEquipesSortedByVictoires() {
        return ResponseEntity.ok(equipeService.getEquipesSortedByVictoires());
    }
}