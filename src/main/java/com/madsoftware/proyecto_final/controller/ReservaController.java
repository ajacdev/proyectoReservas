package com.madsoftware.proyecto_final.controller;

import com.madsoftware.proyecto_final.model.Reserva;
import com.madsoftware.proyecto_final.repository.ReservaRepository;
import com.madsoftware.proyecto_final.repository.ClienteRepository;
import com.madsoftware.proyecto_final.repository.EventoRepository;
import com.madsoftware.proyecto_final.service.ReservaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/reserva")
public class ReservaController {

    private final ReservaRepository reservaRepository;
    private final ClienteRepository clienteRepository;
    private final EventoRepository eventoRepository;
    private final ReservaService reservaService;

    public ReservaController(ReservaRepository reservaRepository, ClienteRepository clienteRepository,
                             EventoRepository eventoRepository, ReservaService reservaService) {
        this.reservaRepository = reservaRepository;
        this.clienteRepository = clienteRepository;
        this.eventoRepository = eventoRepository;
        this.reservaService = reservaService;
    }

    @GetMapping
    public String listarReservas(Model model) {
        model.addAttribute("reservas", reservaRepository.findAll());
        return "reserva-list";
    }

    @GetMapping("/form")
    public String mostrarFormulario(Model model) {
        model.addAttribute("reserva", new Reserva());
        model.addAttribute("clientes", clienteRepository.findAll());
        model.addAttribute("eventos", eventoRepository.findAll());
        return "reserva-form";
    }

    @PostMapping
    public String guardarReserva(@Valid @ModelAttribute Reserva reserva, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("clientes", clienteRepository.findAll());
            model.addAttribute("eventos", eventoRepository.findAll());
            return "reserva-form";
        }
        
        if (!reservaService.verificarDisponibilidad(reserva)) {
            result.rejectValue("evento", "error.reserva", "No hay disponibilidad para este evento");
            model.addAttribute("clientes", clienteRepository.findAll());
            model.addAttribute("eventos", eventoRepository.findAll());
            return "reserva-form";
        }
        
        reservaRepository.save(reserva);
        return "redirect:/reserva";
    }

    @GetMapping("/editar/{id}")
    public String editarReserva(@PathVariable Long id, Model model) {
        model.addAttribute("reserva", reservaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Reserva no válida con id: " + id)));
        model.addAttribute("clientes", clienteRepository.findAll());
        model.addAttribute("eventos", eventoRepository.findAll());
        return "reserva-form";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarReserva(@PathVariable Long id) {
        reservaRepository.deleteById(id);
        return "redirect:/reserva";
    }
}