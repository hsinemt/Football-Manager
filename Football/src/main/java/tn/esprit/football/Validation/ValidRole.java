package tn.esprit.football.Validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = RoleValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidRole {
    String message() default "Le role est incorrect, doit être ADMIN, UTILISATEUR ou JOURNALISTE";
    Class <?>[] groups() default {};
    Class <? extends Payload>[] payload() default{};
}