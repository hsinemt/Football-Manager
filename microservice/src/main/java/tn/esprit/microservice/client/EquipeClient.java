package tn.esprit.microservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tn.esprit.microservice.DTO.EquipeDTO;

@FeignClient(name = "match")
public interface EquipeClient {
    @GetMapping("/equipe/{id}")
    EquipeDTO getEquipeById(@PathVariable Long id);

    @PutMapping("/equipe/{id}/update-stats")
    void updateEquipeStats(
            @PathVariable Long id,
            @RequestParam int victoires,
            @RequestParam int nuls,
            @RequestParam int defaites,
            @RequestParam int butsMarques,
            @RequestParam int butsEncaissees);
}
