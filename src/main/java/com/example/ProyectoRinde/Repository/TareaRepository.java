package com.example.ProyectoRinde.Repository;

import com.example.ProyectoRinde.Model.Tarea;
import com.example.ProyectoRinde.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TareaRepository extends JpaRepository<Tarea, Long> {

    List<Tarea> findByUsuario(Usuario usuario);
    List<Tarea> findByNombre(String nombre);

}
