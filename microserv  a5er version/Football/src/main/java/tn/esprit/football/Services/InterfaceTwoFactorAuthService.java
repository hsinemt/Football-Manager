package tn.esprit.football.Services;

import tn.esprit.football.Entity.TwoFactorAuth;
import tn.esprit.football.Entity.User;

public interface InterfaceTwoFactorAuthService {
    TwoFactorAuth setupTwoFactorAuth(User user);
    boolean verifyCode(User user, String code);
    boolean isTwoFactorAuthEnabled(User user);
    TwoFactorAuth getTwoFactorAuth(User user);
    void disableTwoFactorAuth(User user);
}