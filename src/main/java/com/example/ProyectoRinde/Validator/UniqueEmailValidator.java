package com.example.ProyectoRinde.Validator;

import com.example.ProyectoRinde.Repository.UsuarioRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context){
        return email!=null && !usuarioRepository.existsByEmail(email);
    }
}
