package tn.esprit.football.Controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.football.Entity.*;
import tn.esprit.football.Login.AuthResponse;
import tn.esprit.football.Login.LoginRequest;
import tn.esprit.football.Services.InterfaceUserService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private InterfaceUserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        try {
            user.register();
            User newUser = userService.registerUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@Valid @RequestBody LoginRequest loginRequest) {
        AuthResponse response = userService.loginUser(loginRequest.getEmail(), loginRequest.getPassword());

        if (response != null) {
            if (response.isRequires2FA()) {
                // Si l'authentification à deux facteurs est requise
                return ResponseEntity.status(HttpStatus.OK).body(response);
            } else {
                // Si l'authentification directe est réussie
                return ResponseEntity.status(HttpStatus.OK).body(response);
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Email ou mot de passe incorrect");
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        User updatedUser = userService.updateUser(id, user);
        if (updatedUser != null) {
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }


    @PostMapping("/admin/{id}/manageTeams")
    public ResponseEntity<Void> manageTeams(@PathVariable Long id) {
        User user = userService.getUserById(id);
        if (user instanceof Admin) {
            ((Admin) user).manageTeams();
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @PostMapping("/admin/{id}/manageMatches")
    public ResponseEntity<Void> manageMatches(@PathVariable Long id) {
        User user = userService.getUserById(id);
        if (user instanceof Admin) {
            ((Admin) user).manageMatches();
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @PostMapping("/admin/{id}/superviseCompetition")
    public ResponseEntity<Void> superviseCompetition(@PathVariable Long id) {
        User user = userService.getUserById(id);
        if (user instanceof Admin) {
            ((Admin) user).superviseCompetition();
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }


    @PostMapping("/spectateur/{id}/followTeam")
    public ResponseEntity<Void> followTeam(@PathVariable Long id) {
        User user = userService.getUserById(id);
        if (user instanceof Spectator) {
            ((Spectator) user).followTeam();
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @PostMapping("/spectateur/{id}/followMatch")
    public ResponseEntity<Void> followMatch(@PathVariable Long id) {
        User user = userService.getUserById(id);
        if (user instanceof Spectator) {
            ((Spectator) user).followMatch();
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @PostMapping("/spectateur/{id}/followCompetition")
    public ResponseEntity<Void> followCompetition(@PathVariable Long id) {
        User user = userService.getUserById(id);
        if (user instanceof Spectator) {
            ((Spectator) user).followCompetition();
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }



}