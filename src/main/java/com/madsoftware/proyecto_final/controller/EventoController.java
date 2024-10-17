package com.madsoftware.proyecto_final.controller;

import com.madsoftware.proyecto_final.model.Evento;
import com.madsoftware.proyecto_final.repository.EventoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/evento")
public class EventoController {

    private final EventoRepository eventoRepository;

    public EventoController(EventoRepository eventoRepository) {
        this.eventoRepository = eventoRepository;
    }

    @GetMapping
    public String listarEventos(Model model) {
        model.addAttribute("eventos", eventoRepository.findAll());
        return "evento-list";
    }

    @GetMapping("/form")
    public String mostrarFormulario(Model model) {
        model.addAttribute("evento", new Evento());
        return "evento-form";
    }

    @PostMapping
    public String guardarEvento(@Valid @ModelAttribute Evento evento, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "evento-form";
        }
        eventoRepository.save(evento);
        return "redirect:/evento";
    }

    @GetMapping("/editar/{id}")
    public String editarEvento(@PathVariable Long id, Model model) {
        model.addAttribute("evento", eventoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Evento no válido con id: " + id)));
        return "evento-form";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarEvento(@PathVariable Long id) {
        eventoRepository.deleteById(id);
        return "redirect:/evento";
    }
}