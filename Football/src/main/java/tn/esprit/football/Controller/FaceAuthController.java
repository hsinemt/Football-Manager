package tn.esprit.football.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.football.Entity.User;
import tn.esprit.football.Login.AuthResponse;
import tn.esprit.football.Login.FaceAuthRequest;
import tn.esprit.football.Services.FaceRecognitionService;
import tn.esprit.football.Services.InterfaceUserService;

@RestController
@RequestMapping("/face-auth")
public class FaceAuthController {

    @Autowired
    private FaceRecognitionService faceRecognitionService;

    @Autowired
    private InterfaceUserService userService;

    @PostMapping("/register/{userId}")
    public ResponseEntity<?> registerFace(@PathVariable Long userId, @RequestBody FaceAuthRequest request) {
        User user = userService.getUserById(userId);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        boolean success = faceRecognitionService.registerFace(userId, request.getImageBase64());

        if (success) {
            return ResponseEntity.ok().body("Face registered successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to register face");
        }
    }

    @PostMapping("/verify/{userId}")
    public ResponseEntity<?> verifyFace(@PathVariable Long userId, @RequestBody FaceAuthRequest request) {
        User user = userService.getUserById(userId);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        boolean verified = faceRecognitionService.verifyFace(userId, request.getImageBase64());

        if (verified) {
            // Générer la réponse d'authentification
            AuthResponse response = new AuthResponse();
            user.setLastconnection(java.time.LocalDateTime.now());
            userService.updateUser(userId, user);

            response.setUser(user);
            response.setRequires2FA(false);

            return ResponseEntity.ok().body(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Face verification failed");
        }
    }
}