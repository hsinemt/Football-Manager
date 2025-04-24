package tn.esprit.football.Services;

import dev.samstevens.totp.exceptions.QrGenerationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.football.Entity.TwoFactorAuth;
import tn.esprit.football.Entity.User;
import tn.esprit.football.Repository.TwoFactorAuthRepository;
import tn.esprit.football.Utils.TotpUtils;

import java.time.LocalDateTime;

@Service
public class TwoFactorAuthServiceImpl implements InterfaceTwoFactorAuthService {

    @Autowired
    private TwoFactorAuthRepository twoFactorAuthRepository;

    @Autowired
    private TotpUtils totpUtils;

    @Override
    public TwoFactorAuth setupTwoFactorAuth(User user) {
        TwoFactorAuth twoFactorAuth = twoFactorAuthRepository.findByUser(user);

        if (twoFactorAuth == null) {
            twoFactorAuth = new TwoFactorAuth();
            twoFactorAuth.setUser(user);
            twoFactorAuth.setCreatedAt(LocalDateTime.now());
        }

        String secret = totpUtils.generateSecret();
        twoFactorAuth.setSecretKey(secret);
        twoFactorAuth.setEnabled(false);

        try {
            String qrCodeImage = totpUtils.getQrCodeImage(secret, user.getEmail());
            twoFactorAuth.setQrCodeImage(qrCodeImage);
        } catch (QrGenerationException e) {
            throw new RuntimeException("Erreur lors de la génération du QR code", e);
        }

        return twoFactorAuthRepository.save(twoFactorAuth);
    }

    @Override
    public boolean verifyCode(User user, String code) {
        TwoFactorAuth twoFactorAuth = twoFactorAuthRepository.findByUser(user);

        if (twoFactorAuth != null) {
            boolean isValid = totpUtils.verifyCode(code, twoFactorAuth.getSecretKey());


            if (isValid && !twoFactorAuth.isEnabled()) {
                twoFactorAuth.setEnabled(true);
                twoFactorAuthRepository.save(twoFactorAuth);
            }

            return isValid;
        }

        return false;
    }

    @Override
    public boolean isTwoFactorAuthEnabled(User user) {
        TwoFactorAuth twoFactorAuth = twoFactorAuthRepository.findByUser(user);
        return twoFactorAuth != null && twoFactorAuth.isEnabled();
    }

    @Override
    public TwoFactorAuth getTwoFactorAuth(User user) {
        return twoFactorAuthRepository.findByUser(user);
    }

    @Override
    public void disableTwoFactorAuth(User user) {
        TwoFactorAuth twoFactorAuth = twoFactorAuthRepository.findByUser(user);

        if (twoFactorAuth != null) {
            twoFactorAuth.setEnabled(false);
            twoFactorAuthRepository.save(twoFactorAuth);
        }
    }
}