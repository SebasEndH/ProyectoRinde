package com.example.ProyectoRinde.Repository;

import com.example.ProyectoRinde.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    //Usuario findByEmailAndPassword(String email, String password);
    Usuario findByEmail(String email);
    boolean existsByEmail(String email);
}
