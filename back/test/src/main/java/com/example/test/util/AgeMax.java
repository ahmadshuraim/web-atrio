package com.example.test.util;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = AgeMaxValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AgeMax {
    String message() default "L'âge maximum autorisé est de 149 ans";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
