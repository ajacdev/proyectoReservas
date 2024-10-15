package com.example.springbootdemo.controller;

import com.example.springbootdemo.model.Reserva;
import com.example.springbootdemo.repository.ClienteRepository;
import com.example.springbootdemo.repository.EventoRepository;
import com.example.springbootdemo.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ReservaController {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EventoRepository eventoRepository;

    @GetMapping("/reservas")
    public String listReservas(Model model) {
        model.addAttribute("reservas", reservaRepository.findAll());
        return "reservas";
    }

    @GetMapping("/reserva/new")
    public String newReserva(Model model) {
        model.addAttribute("reserva", new Reserva());
        model.addAttribute("clientes", clienteRepository.findAll());
        model.addAttribute("eventos", eventoRepository.findAll());
        return "reserva-form";
    }

    @PostMapping("/reserva")
    public String saveReserva(Reserva reserva) {
        reservaRepository.save(reserva);
        return "redirect:/reservas";
    }
}
