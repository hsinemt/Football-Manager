package tn.esprit.microservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tn.esprit.microservice.DTO.CompetitionDTO;
import tn.esprit.microservice.DTO.EquipeInfo;

import java.util.List;
import java.util.UUID;

@FeignClient(name = "match")
public interface EquipeClient {
    @GetMapping("/equipe/by-competition/{competitionId}")
    List<EquipeInfo> getEquipesByCompetition(@PathVariable UUID competitionId);

    @PutMapping("/equipe/{id}/set-competition")
    void setCompetition(@PathVariable Long id, @RequestParam UUID competitionId);
}
