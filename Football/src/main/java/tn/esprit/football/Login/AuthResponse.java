package tn.esprit.football.Login;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tn.esprit.football.Entity.User;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
    private User user;
    private boolean requires2FA;
    private String qrCodeImage;
    private boolean is2FAEnabled;

    public void setIs2FAEnabled(boolean is2FAEnabled) {
        this.is2FAEnabled = is2FAEnabled;
    }

    public void setRequires2FA(boolean requires2FA) {
        this.requires2FA = requires2FA;
    }
}