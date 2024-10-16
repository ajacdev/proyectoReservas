package com.madsoftware.proyecto_final.controller;

import com.madsoftware.proyecto_final.model.Cliente;
import com.madsoftware.proyecto_final.model.Evento;
import com.madsoftware.proyecto_final.model.Reserva;
import com.madsoftware.proyecto_final.repository.ClienteRepository;
import com.madsoftware.proyecto_final.repository.EventoRepository;
import com.madsoftware.proyecto_final.repository.ReservaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

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
        reserva.setFecha(LocalDate.now());
        reservaRepository.save(reserva);
        return "redirect:/reserva";
    }
}
