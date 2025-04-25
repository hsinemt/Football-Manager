package tn.esprit.football.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.football.Entity.TwoFactorAuth;
import tn.esprit.football.Entity.User;
import tn.esprit.football.Login.AuthResponse;
import tn.esprit.football.Login.TwoFactorAuthDTO;
import tn.esprit.football.Services.InterfaceUserService;

@RestController
@RequestMapping("/2fa")
public class TwoFactorAuthController {

    @Autowired
    private InterfaceUserService userService;

    @PostMapping("/setup/{userId}")
    public ResponseEntity<?> setupTwoFactorAuth(@PathVariable Long userId) {
        TwoFactorAuth twoFactorAuth = userService.setupTwoFactorAuth(userId);

        if (twoFactorAuth != null) {
            return ResponseEntity.ok().body(twoFactorAuth);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Utilisateur non trouvé");
        }
    }

    @PostMapping("/verify")
    public ResponseEntity<?> verifyTwoFactorAuth(@RequestBody TwoFactorAuthDTO twoFactorAuthDTO) {
        AuthResponse response = userService.verifyTwoFactorAuth(twoFactorAuthDTO.getUserId(), twoFactorAuthDTO.getCode());

        if (response != null) {
            return ResponseEntity.ok().body(response);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Code invalide ou utilisateur non trouvé");
        }
    }

    @PostMapping("/disable/{userId}")
    public ResponseEntity<?> disableTwoFactorAuth(@PathVariable Long userId) {
        boolean success = userService.disableTwoFactorAuth(userId);

        if (success) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Utilisateur non trouvé");
        }
    }
}