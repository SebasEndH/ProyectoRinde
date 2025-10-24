package com.example.ProyectoRinde.Controller;

import com.example.ProyectoRinde.DTO.TareaDTO;
import com.example.ProyectoRinde.Model.Usuario;
import com.example.ProyectoRinde.Service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.ProyectoRinde.Model.Tarea;
import com.example.ProyectoRinde.Service.TareaService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class TareaController {

    private final TareaService tareaService;
    private final UsuarioService usuarioService;



    @GetMapping("/tareas")
    public String verTareas(Model model, HttpSession session, RedirectAttributes attrs) {
        Long id = (Long) session.getAttribute("id");
        if (id == null) {
            attrs.addFlashAttribute("loginError", "Debes iniciar sesión para agregar tareas.");
            return "redirect:/herramienta/pomodoro";
        }
        Usuario user = usuarioService.obtenerPorId(id);

        if (user == null) {
            session.invalidate();
            return "redirect:/herramienta/pomodoro";

        }

        model.addAttribute("tareas", tareaService.listarTareas(user));
        model.addAttribute("titulo", "tareas");

        Object u = session.getAttribute("usuario");
        if (u != null) model.addAttribute("usuario", u.toString());
        return "tareas/tareas";
    }

    @PostMapping("/tareas")
    public String agregarTarea(@RequestParam String nombre, @RequestParam(required = false) String descripcion, HttpSession session, Model model) {
        TareaDTO tareaDTO = new TareaDTO();
            tareaDTO.setNombre(nombre);
            tareaDTO.setDescripcion(descripcion != null ? descripcion : "");

        Long id = (Long) session.getAttribute("id");
        Usuario user = usuarioService.obtenerPorId(id);

        Tarea nueva = tareaService.convertirDTOaTarea(tareaDTO, user);
        tareaService.agregarTarea(nueva);

        Object u = session.getAttribute("usuario");
        if (u != null) model.addAttribute("usuario", u.toString());
        return "redirect:/tareas";
    }

    @PostMapping("/tareas/eliminar")
    public String eliminarTarea(@RequestParam String nombre, HttpSession session, Model model) {

        Long id = (Long) session.getAttribute("id");
        Usuario user = usuarioService.obtenerPorId(id);

        tareaService.listarTareas(user).forEach(t -> {
            if (t.getNombre().equals(nombre)) {
                tareaService.eliminarTarea(t.getId());
            }
        });

        return "redirect:/tareas";
    }
}



