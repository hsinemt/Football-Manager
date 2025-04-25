package tn.esprit.microservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import tn.esprit.microservice.DTO.CompetitionDTO;
import tn.esprit.microservice.DTO.CompetitionMapper;
import tn.esprit.microservice.DTO.EquipeInfo;
import tn.esprit.microservice.client.EquipeClient;
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
    private final EquipeClient equipeClient;


    @Autowired
    public CompetitionService(CompetitionRepository competitionRepository,
                              SmsService smsService,
                              EmailService emailService , EquipeClient equipeClient) {
        this.competitionRepository = competitionRepository;
        this.smsService = smsService;
        this.emailService = emailService;
        this.equipeClient = equipeClient;
    }

    public List<Competition> getAllCompetitions() {
        return competitionRepository.findAll();
    }

    public Optional<Competition> getCompetitionById(UUID id) {
        return competitionRepository.findById(id);
    }

    public Competition addCompetition(Competition competition, String telephone, String email) {
        if (competition.getDateDebut().isAfter(competition.getDateFin())) {
            throw new IllegalArgumentException("La date de début ne peut pas être après la date de fin.");
        }

        Competition savedCompetition = competitionRepository.save(competition);

        // Debug: Afficher le numéro de téléphone avant envoi
        System.err.println("Tentative d'envoi SMS à: " + telephone);

        // Envoyer le SMS avec les détails
        try {
            smsService.sendCompetitionDetailsSms(
                    telephone,
                    competition.getNom(),
                    competition.getDateDebut(),
                    competition.getDateFin()
            );
            System.err.println("SMS envoyé avec succès à " + telephone);
        } catch (Exception e) {
            System.err.println("ÉCHEC envoi SMS à " + telephone + " - Erreur: " + e.getMessage());
            e.printStackTrace();
        }

        // Envoyer l'email
        try {
            emailService.sendCompetitionEmail(
                    email,
                    competition.getNom(),
                    competition.getDateDebut(),
                    competition.getDateFin()
            );
        } catch (Exception e) {
            System.err.println("Erreur lors de l'envoi de l'email: " + e.getMessage());
        }

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





    // Recherche par nom
    public List<Competition> searchByNom(String nom) {
        return competitionRepository.findByNomContainingIgnoreCase(nom);
    }

    public List<Competition> searchBySaison(String saison) {
        return competitionRepository.findBySaisonContainingIgnoreCase(saison);
    }

    public List<Competition> sortByDateDebut(String sortDirection) {
        Sort.Direction direction = sortDirection.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        return competitionRepository.findAll(Sort.by(direction, "dateDebut"));
    }
    public CompetitionDTO createCompetition(CompetitionDTO dto) {
        Competition competition = CompetitionMapper.toEntity(dto);
        Competition saved = competitionRepository.save(competition);
        return CompetitionMapper.toDTO(saved);
    }
    public CompetitionDTO getCompetitionWithEquipes(UUID id) {
        Competition competition = competitionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Competition not found"));

        List<EquipeInfo> equipes = equipeClient.getEquipesByCompetition(id);

        return CompetitionMapper.toDTOWithEquipes(competition, equipes);
    }
    }

