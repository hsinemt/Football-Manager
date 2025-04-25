package tn.esprit.examen.nomPrenomClasseExamen.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.examen.nomPrenomClasseExamen.DTO.EquipeDTO;
import tn.esprit.examen.nomPrenomClasseExamen.DTO.EquipeInfo;
import tn.esprit.examen.nomPrenomClasseExamen.entities.Equipe;
import tn.esprit.examen.nomPrenomClasseExamen.services.IServices;
import tn.esprit.examen.nomPrenomClasseExamen.services.ServicesImpl;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("equipe")
public class EquipeRestController {

    private final IServices equipeService;
    private final ServicesImpl servicesImpl;

    public EquipeRestController(IServices equipeService, ServicesImpl servicesImpl) {
        this.equipeService = equipeService;
        this.servicesImpl = servicesImpl;
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

    @GetMapping("/all")
    public List<Equipe> getAllEquipes() {
        return equipeService.getAllEquipes();
    }

    @GetMapping("/sorted")
    public ResponseEntity<List<Equipe>> getEquipesSortedByVictoires() {
        return ResponseEntity.ok(equipeService.getEquipesSortedByVictoires());
    }

        @Value("${welcome.message}")
        private String welcomeMessage;

        @GetMapping("/welcome")
        public String welcome() {
            return welcomeMessage;
        }


    @PutMapping("/{id}/update-stats")
    public Equipe updateEquipeStats(
            @PathVariable Long id,
            @RequestParam int victoires,
            @RequestParam int nuls,
            @RequestParam int defaites,
            @RequestParam int butsMarques,
            @RequestParam int butsEncaissees) {
        return equipeService.updateStats(
                id, victoires, nuls, defaites, butsMarques, butsEncaissees);
    }

    @PostMapping("/create")
    public EquipeDTO create(@RequestBody EquipeDTO dto) {
        return equipeService.createEquipe(dto);
    }

    @GetMapping("/by-competition/{competitionId}")
    public List<EquipeInfo> getByCompetition(@PathVariable UUID competitionId) {
        return servicesImpl.getEquipesByCompetition(competitionId);
    }
    @PostMapping("/with-manager")
    public EquipeDTO createEquipeWithManager(@RequestBody EquipeDTO equipeDTO) {
        return servicesImpl.createEquipeWithManager(equipeDTO);
    }

}