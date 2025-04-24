package tn.esprit.football.Services;

import tn.esprit.football.Entity.TwoFactorAuth;
import tn.esprit.football.Entity.User;
import tn.esprit.football.Login.AuthResponse;

import java.util.List;

public interface InterfaceUserService {
    User registerUser(User user);
    AuthResponse loginUser(String email, String password);
    AuthResponse verifyTwoFactorAuth(Long userId, String code);
    TwoFactorAuth setupTwoFactorAuth(Long userId);
    boolean disableTwoFactorAuth(Long userId);
    List<User> getAllUsers();
    User getUserById(Long id);
    User updateUser(Long id, User user);
    void deleteUser(Long id);
}