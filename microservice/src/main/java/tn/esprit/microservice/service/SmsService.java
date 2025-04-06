package tn.esprit.microservice.service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class SmsService {

    @Value("${twilio.account.sid}")
    private String accountSid;

    @Value("${twilio.auth.token}")
    private String authToken;

    @Value("${twilio.phone.number}")
    private String twilioPhoneNumber;

    @Async
    public void sendWelcomeSms(String to) {
        try {
            Twilio.init(accountSid, authToken);

            String messageBody = "Votre inscription à la compétition est confirmée !";

            Message message = Message.creator(
                    new PhoneNumber("+216" + to), // Assurez-vous que le numéro est correct
                    new PhoneNumber(twilioPhoneNumber),
                    messageBody
            ).create();

            System.out.println("SMS sent with SID: " + message.getSid());
        } catch (Exception e) {
            System.err.println("Erreur Twilio: " + e.getMessage());
            throw new RuntimeException("Échec de l'envoi du SMS", e);
        }
    }

    @Async
    public void sendCompetitionDetailsSms(String to, String competitionName, LocalDate dateDebut, LocalDate dateFin) {
        try {
            Twilio.init(accountSid, authToken);

            // Create formatter
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            // Format dates
            String formattedDateDebut = dateDebut.format(formatter);
            String formattedDateFin = dateFin.format(formatter);

            String messageBody = String.format(
                    "Nouvelle compétition : %s%nDate début: %s%nDate fin: %s",
                    competitionName,
                    formattedDateDebut,
                    formattedDateFin
            );

            Message message = Message.creator(
                    new PhoneNumber("+216" + to),
                    new PhoneNumber(twilioPhoneNumber),
                    messageBody
            ).create();

            System.out.println("SMS sent with SID: " + message.getSid());
        } catch (Exception e) {
            System.err.println("Erreur Twilio: " + e.getMessage());
            throw new RuntimeException("Échec de l'envoi du SMS", e);
        }
    }

}