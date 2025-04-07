package tn.esprit.football.Login;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TwoFactorAuthDTO {
    @NotBlank(message = "Le code d'authentification est obligatoire")
    private String code;

    private Long userId;
}