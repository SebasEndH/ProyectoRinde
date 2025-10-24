package com.example.ProyectoRinde.Model;

import com.example.ProyectoRinde.Validator.UniqueEmail;
import com.example.ProyectoRinde.Validator.ValidPassword;
import jakarta.persistence.*;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @Column(unique = true)
    private String email;

    private String password;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Tarea> tareas;

}
