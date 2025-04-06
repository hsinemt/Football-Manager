package tn.esprit.microservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.microservice.entity.Competition;
import tn.esprit.microservice.repository.CompetitionRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CompetitionService {
    private final CompetitionRepository competitionRepository;
    private final SmsService smsService;
    private final EmailService emailService; // Ajout du EmailService

    @Autowired
    public CompetitionService(CompetitionRepository competitionRepository,
                              SmsService smsService,
                              EmailService emailService) {
        this.competitionRepository = competitionRepository;
        this.smsService = smsService;
        this.emailService = emailService;
    }

    public List<Competition> getAllCompetitions() {
        return competitionRepository.findAll();
    }

    public Optional<Competition> getCompetitionById(UUID id) {
        return competitionRepository.findById(id);
    }

    public Competition addCompetition(Competition competition, String telephone , String email) {
        if (competition.getDateDebut().isAfter(competition.getDateFin())) {
            throw new IllegalArgumentException("La date de début ne peut pas être après la date de fin.");
        }

        Competition savedCompetition = competitionRepository.save(competition);

        // Envoyer le SMS avec les détails
        try {
            smsService.sendCompetitionDetailsSms(
                    telephone,
                    competition.getNom(),
                    competition.getDateDebut(),
                    competition.getDateFin()
            );
        } catch (Exception e) {
            System.err.println("Erreur lors de l'envoi du SMS: " + e.getMessage());
            // Vous pouvez choisir de logger sans interrompre le flux
        }
        // Envoyer l'email
        emailService.sendCompetitionEmail(
                email,
                competition.getNom(),
                competition.getDateDebut(),
                competition.getDateFin()
        );


        return savedCompetition;
    }
    public Competition updateCompetition(UUID id, Competition competition) {
        if (competition.getDateDebut().isAfter(competition.getDateFin())) {
            throw new IllegalArgumentException("La date de début ne peut pas être après la date de fin.");
        }
        return competitionRepository.findById(id)
                .map(existingComp -> {
                    existingComp.setNom(competition.getNom());
                    existingComp.setSaison(competition.getSaison());
                    existingComp.setDateDebut(competition.getDateDebut());
                    existingComp.setDateFin(competition.getDateFin());
                    return competitionRepository.save(existingComp);
                })
                .orElseThrow(() -> new IllegalArgumentException("Competition not found with ID: " + id));
    }

    public void deleteCompetition(UUID id) {
        competitionRepository.deleteById(id);
    }
}