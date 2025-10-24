package com.example.ProyectoRinde.Service;

import java.util.List;

import com.example.ProyectoRinde.DTO.TareaDTO;
import com.example.ProyectoRinde.Model.Usuario;
import com.example.ProyectoRinde.Repository.TareaRepository;
import com.example.ProyectoRinde.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ProyectoRinde.Model.Tarea;

@Service
public class TareaService {
    @Autowired
    private TareaRepository tareaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Tarea> listarTareas(Usuario user) {
        return tareaRepository.findByUsuario(user);
    }

    public void agregarTarea(Tarea tarea) {
        tareaRepository.save(tarea);
    }

    public void eliminarTarea(Long id) {
        tareaRepository.deleteById(id);
    }

    public TareaDTO convertirTareaADTO(Tarea tarea) {
        TareaDTO tareaDTO = new TareaDTO();
        tareaDTO.setId(tarea.getId());
        tareaDTO.setNombre(tarea.getNombre());
        tareaDTO.setDescripcion(tarea.getDescripcion());
        return tareaDTO;
    }

    public Tarea convertirDTOaTarea(TareaDTO tareaDTO, Usuario usuario) {
        Tarea tarea = new Tarea();
        tarea.setNombre(tareaDTO.getNombre());
        tarea.setDescripcion(tareaDTO.getDescripcion());
        tarea.setUsuario(usuario);
        return tarea;
    }


}
