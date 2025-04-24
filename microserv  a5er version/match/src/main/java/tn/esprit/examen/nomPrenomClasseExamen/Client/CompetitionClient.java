package tn.esprit.examen.nomPrenomClasseExamen.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import tn.esprit.examen.nomPrenomClasseExamen.DTO.CompetitionDTO;

import java.util.UUID;

@FeignClient(name = "Competition-service")
public interface CompetitionClient {
    @GetMapping("/api/competitions/{id}")
    CompetitionDTO getCompetitionById(@PathVariable UUID id);

//    @GetMapping("/competitions/exists/{id}")
//    boolean competitionExists(@PathVariable UUID id);
}
