package tn.esprit.football.Entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tn.esprit.football.Validation.ValidRole;

import java.time.LocalDateTime;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "app_user")

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "Le prenom est obligatoire")
    private String name;

    @Column(nullable = false)
    @NotBlank(message = "Le nom est obligatoire")
    private String lastname;

//    @Column(nullable = false)
//    @NotBlank(message = "L'email est obligatoire")
//    @Email(message = "L'email doit etre valide")
    private String email;

    @Column(nullable = false)
    @NotBlank(message = "le mot de passe est obligatoire")
    @Size(min=8, message = "Le mot de passe doit contenir au moins 8 caractères")
//    @Pattern(
//            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=\\S+$).{8,}$",
//            message = "Le mot de passe doit contenir au moins une majuscule, une minuscule, un chiffre et un caractère spécial")
    private String password;

    @Column(nullable = false)
    @NotNull(message = "Le rôle est obligatoire")
    @ValidRole
    @Enumerated(EnumType.STRING)
    private UserRole userrole;

    private LocalDateTime creationdate;

    private LocalDateTime lastconnection;


    public void register() {
        this.creationdate = LocalDateTime.now();
    }

    public void login() {
        this.lastconnection = LocalDateTime.now();
    }

    public User getUser() {
        return this;
    }

    public void updateUser(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.userrole = user.getUserrole();
    }
}