package tn.esprit.football.Services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class FaceRecognitionService {

    @Value("${face.recognition.api.url:http://localhost:5000}")
    private String faceApiUrl;

    private final RestTemplate restTemplate;

    public FaceRecognitionService() {
        this.restTemplate = new RestTemplate();
    }

    public boolean registerFace(Long userId, String base64Image) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            Map<String, Object> requestBody = new HashMap<>();
            requestBody.put("userId", userId.toString());
            requestBody.put("image", base64Image);

            HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);

            ResponseEntity<Map> response = restTemplate.postForEntity(
                    faceApiUrl + "/api/register-face",
                    request,
                    Map.class
            );

            return response.getStatusCode().is2xxSuccessful() &&
                    Boolean.TRUE.equals(response.getBody().get("success"));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean verifyFace(Long userId, String base64Image) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            Map<String, Object> requestBody = new HashMap<>();
            requestBody.put("userId", userId.toString());
            requestBody.put("image", base64Image);

            HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);

            ResponseEntity<Map> response = restTemplate.postForEntity(
                    faceApiUrl + "/api/verify-face",
                    request,
                    Map.class
            );

            return response.getStatusCode().is2xxSuccessful() &&
                    Boolean.TRUE.equals(response.getBody().get("success"));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}