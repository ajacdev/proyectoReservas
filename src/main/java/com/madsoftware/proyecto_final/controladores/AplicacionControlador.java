package com.madsoftware.proyecto_final.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AplicacionControlador {

    @GetMapping("/bienvenida")
    public String mostrarBienvenida(Model modelo) {
        System.out.println("ejecutando bienvenida");

        String nombreUsuario = "Homero Simpson";
        modelo.addAttribute("nombre", nombreUsuario);
        return "inicio";
    }
}
