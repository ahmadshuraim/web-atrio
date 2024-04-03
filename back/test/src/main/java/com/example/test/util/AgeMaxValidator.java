package com.example.test.util;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZonedDateTime;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AgeMaxValidator implements ConstraintValidator<AgeMax, ZonedDateTime> {

    @Override
    public void initialize(AgeMax constraintAnnotation) {
    }

    @Override
    public boolean isValid(ZonedDateTime dateOfBirth, ConstraintValidatorContext context) {
        if (dateOfBirth == null) {
            return true;
        }

        LocalDate birthDate = dateOfBirth.toLocalDate();
        LocalDate now = LocalDate.now();

        Period period = Period.between(birthDate, now);
        int age = period.getYears();

        return age < 150;
    }
}