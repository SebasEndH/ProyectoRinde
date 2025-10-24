package com.example.ProyectoRinde.DTO;

import com.example.ProyectoRinde.Validator.UniqueEmail;
import com.example.ProyectoRinde.Validator.ValidPassword;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class UsuarioDTO {

    @NotBlank
    private String nombre;

    @NotBlank
    @Email(message = "El email debe ser válido")
    @UniqueEmail
    private String email;

    @NotBlank
    @ValidPassword
    private String password;

}
