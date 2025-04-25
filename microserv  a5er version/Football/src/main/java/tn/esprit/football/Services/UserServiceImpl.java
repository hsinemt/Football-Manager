package tn.esprit.football.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.football.Entity.*;
import tn.esprit.football.Login.AuthResponse;
import tn.esprit.football.Repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImpl implements InterfaceUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private InterfaceTwoFactorAuthService twoFactorAuthService;

    @Override
    public User registerUser(User user) {
        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new IllegalArgumentException("Email already exists");
        }
        user.setCreationdate(LocalDateTime.now());
        return userRepository.save(user);
    }

    @Override
    public AuthResponse loginUser(String email, String password) {
        User user = userRepository.findByEmail(email);
        AuthResponse response = new AuthResponse();

        if (user != null && user.getPassword().equals(password)) {
            boolean is2FAEnabled = twoFactorAuthService.isTwoFactorAuthEnabled(user);

            if (is2FAEnabled) {
                // Si 2FA est activé, retourner une réponse indiquant qu'une vérification 2FA est nécessaire
                response.setUser(new User());  // Ne pas renvoyer les détails de l'utilisateur avant la vérification 2FA
                response.getUser().setId(user.getId());
                response.setRequires2FA(true);
                response.setIs2FAEnabled(true);
            } else {
                // Si 2FA n'est pas activé, connecter l'utilisateur directement
                user.setLastconnection(LocalDateTime.now());
                userRepository.save(user);

                response.setUser(user);
                response.setRequires2FA(false);
                response.setIs2FAEnabled(false);
            }

            return response;
        }

        return null;
    }

    @Override
    public AuthResponse verifyTwoFactorAuth(Long userId, String code) {
        User user = userRepository.findById(userId).orElse(null);
        AuthResponse response = new AuthResponse();

        if (user != null && twoFactorAuthService.verifyCode(user, code)) {
            user.setLastconnection(LocalDateTime.now());
            userRepository.save(user);

            response.setUser(user);
            response.setRequires2FA(false);
            response.setIs2FAEnabled(true);

            return response;
        }

        return null;
    }

    @Override
    public TwoFactorAuth setupTwoFactorAuth(Long userId) {
        User user = userRepository.findById(userId).orElse(null);

        if (user != null) {
            return twoFactorAuthService.setupTwoFactorAuth(user);
        }

        return null;
    }

    @Override
    public boolean disableTwoFactorAuth(Long userId) {
        User user = userRepository.findById(userId).orElse(null);

        if (user != null) {
            twoFactorAuthService.disableTwoFactorAuth(user);
            return true;
        }

        return false;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User updateUser(Long id, User user) {
        User userExist = userRepository.findById(id).orElse(null);
        if (userExist != null) {
            userExist.setName(user.getName());
            userExist.setEmail(user.getEmail());
            userExist.setPassword(user.getPassword());
            userExist.setUserrole(user.getUserrole());
            return userRepository.save(userExist);
        }
        return null;
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}