package com.madsoftware.proyecto_final.controller;

import com.madsoftware.proyecto_final.model.Evento;
import com.madsoftware.proyecto_final.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EventoController {

    @Autowired
    private EventoRepository eventoRepository;

    @GetMapping("/eventos")
    public String listEventos(Model model) {
        model.addAttribute("eventos", eventoRepository.findAll());
        return "eventos";
    }

    @GetMapping("/evento/new")
    public String newEvento(Model model) {
        model.addAttribute("evento", new Evento());
        return "evento-form";
    }

    @PostMapping("/evento")
    public String saveEvento(Evento evento) {
        eventoRepository.save(evento);
        return "redirect:/eventos";
    }
}
