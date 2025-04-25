package tn.esprit.microservice.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendCompetitionEmail(String to, String competitionName,
                                     LocalDate dateDebut, LocalDate dateFin) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            helper.setTo(to);
            helper.setSubject("Nouvelle Compétition: " + competitionName);
            helper.setText(
                    "<h1>Nouvelle Compétition Créée</h1>" +
                            "<p>Détails de la compétition :</p>" +
                            "<ul>" +
                            "<li><strong>Nom:</strong> " + competitionName + "</li>" +
                            "<li><strong>Date début:</strong> " + dateDebut.format(formatter) + "</li>" +
                            "<li><strong>Date fin:</strong> " + dateFin.format(formatter) + "</li>" +
                            "</ul>",
                    true
            );

            mailSender.send(message);
        } catch (MessagingException e) {
            System.err.println("Erreur lors de l'envoi de l'email: " + e.getMessage());
        }
    }
}