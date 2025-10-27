package com.example.ProyectoRinde.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class PrincipalController {

    @GetMapping({"/", "/index"})
    public String index(Model model, HttpSession session) {
        model.addAttribute("titulo", "Bienvenido a Rinde");
        Object u = session.getAttribute("usuario");
        if (u != null) model.addAttribute("usuario", u.toString());
        return "index";
    }

    @GetMapping("/herramienta/pomodoro")
    public String pomodoro(Model model, HttpSession session) {
        Object u = session.getAttribute("usuario");
        if (u != null) model.addAttribute("usuario", u.toString());
        return "herramienta/pomodoro";
    }

}




