package com.example.ProyectoRinde.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.ProyectoRinde.Model.Usuario;
import com.example.ProyectoRinde.Service.UsuarioService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UsuarioController {
    private final UsuarioService usuarioService;

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password, HttpSession session, RedirectAttributes attrs) {
        Usuario usuario = usuarioService.autenticar(email, password);
        if (usuario != null) {
            String username = usuario.getNombre().split(" ")[0];
            session.setAttribute("usuario", username);
            session.setAttribute("id", usuario.getId());
            session.setAttribute("email", usuario.getEmail());
            return "redirect:/";
        } else {
            attrs.addFlashAttribute("loginError", "Correo o contraseña incorrectos.");
            return "redirect:/";
        }
    }

    @GetMapping("/login")
    public String mostrarFormularioLogin(Model model) {
        model.addAttribute("titulo", "Iniciar Sesión");
        return "/";
    }


    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("usuario");
        session.removeAttribute("id");
        session.removeAttribute("email");
        return "redirect:/";
    }

    @PostMapping("/registro")
    public String registro(@ModelAttribute Usuario usuario, RedirectAttributes attrs) {
        try {
            usuarioService.registrar(usuario);
            return "redirect:/";
        } catch (RuntimeException e) {
            attrs.addFlashAttribute("registroError", e.getMessage());
            return "redirect:/";
        }
    }


}