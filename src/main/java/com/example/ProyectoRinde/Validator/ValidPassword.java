package com.example.ProyectoRinde.Validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidPasswordValidation.class)
public @interface ValidPassword {
    String message() default "La contraseña debe tener a menos 8 caracteres, una mayúscula, una minúscula, un número.";
    Class<?> [] groups() default {};
    Class<? extends Payload> [] payload() default {};
}
