package tn.esprit.football.Validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import tn.esprit.football.Entity.UserRole;

public class RoleValidator implements ConstraintValidator<ValidRole, UserRole> {

    @Override
    public void initialize(ValidRole constraintAnnotation) {}

    @Override
    public boolean isValid(UserRole role, ConstraintValidatorContext context) {
        if (role == null) {
            return false;
        }
        return role == UserRole.ADMIN || role == UserRole.SPECTATOR ;
    }
}