package com.madsoftware.proyecto_final.controller;

import com.madsoftware.proyecto_final.model.Reserva;
import com.madsoftware.proyecto_final.repository.ReservaRepository;
import com.madsoftware.proyecto_final.repository.ClienteRepository;
import com.madsoftware.proyecto_final.repository.EventoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/reserva")
public class ReservaController {

    private final ReservaRepository reservaRepository;
    private final ClienteRepository clienteRepository;
    private final EventoRepository eventoRepository;

    public ReservaController(ReservaRepository reservaRepository, ClienteRepository clienteRepository, EventoRepository eventoRepository) {
        this.reservaRepository = reservaRepository;
        this.clienteRepository = clienteRepository;
        this.eventoRepository = eventoRepository;
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
    public String guardarReserva(@ModelAttribute Reserva reserva) {
        reservaRepository.save(reserva); // Guarda la reserva directamente
        return "redirect:/reserva"; // Redirige a la lista de reservas
    }
    

}
