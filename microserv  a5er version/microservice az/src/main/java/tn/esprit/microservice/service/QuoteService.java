package tn.esprit.microservice.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.*;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

@Service
public class QuoteService {

    private final RestTemplate restTemplate;

    public QuoteService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        try {
            // Appeler la méthode pour désactiver la vérification SSL au démarrage du service
            disableSSLVerification();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    // Méthode pour désactiver la vérification SSL
    public static void disableSSLVerification() throws NoSuchAlgorithmException {
        TrustManager[] trustAllCertificates = new TrustManager[]{
                new X509TrustManager() {
                    public X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }

                    public void checkClientTrusted(X509Certificate[] certs, String authType) {
                    }

                    public void checkServerTrusted(X509Certificate[] certs, String authType) {
                    }
                }
        };

        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, trustAllCertificates, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Méthode pour récupérer une citation aléatoire de sport
    public String getRandomSportsQuote() {
        String url = "https://api.quotable.io/random";
        try {
            String response = restTemplate.getForObject(url, String.class);
            return response;
        } catch (Exception e) {
            return "Erreur lors de la récupération de la citation: " + e.getMessage();
        }
    }

    // Méthode pour récupérer une citation motivante
    public String getMotivationalQuote() {
        String url = "https://api.quotable.io/random?tags=motivational";
        try {
            String response = restTemplate.getForObject(url, String.class);
            return response;
        } catch (Exception e) {
            return "Erreur lors de la récupération de la citation: " + e.getMessage();
        }
    }
}
