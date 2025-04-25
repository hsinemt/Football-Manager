package tn.esprit.examen.nomPrenomClasseExamen.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import tn.esprit.examen.nomPrenomClasseExamen.DTO.ManagerDTO;

@FeignClient(name = "MICROSERVICE5")
public interface ManagerClient {
    @PutMapping("/managers/{managerId}/assign-team/{teamId}")
    ResponseEntity<Void> assignTeamToManager(
            @PathVariable String managerId,
            @PathVariable String teamId);

    @GetMapping("/managers/by-team/{teamId}")
    ResponseEntity<ManagerDTO> getManagerByTeamId(@PathVariable String teamId);
}
