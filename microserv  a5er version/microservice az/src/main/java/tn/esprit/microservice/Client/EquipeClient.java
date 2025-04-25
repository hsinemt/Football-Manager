package tn.esprit.microservice.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import tn.esprit.microservice.DTO.EquipeDTO;

@FeignClient(name = "match")
public interface EquipeClient {
    @GetMapping("/equipe/{id}")
    EquipeDTO getEquipeById(@PathVariable Long id);

//    @GetMapping("/equipe/exists/{id}")
//    boolean equipeExists(@PathVariable Long id);
}
