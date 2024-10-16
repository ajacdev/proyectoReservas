package com.madsoftware.proyecto_final.controller;

import com.madsoftware.proyecto_final.model.Cliente;
import com.madsoftware.proyecto_final.repository.ClienteRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cliente")
public class ClienteController {

    private final ClienteRepository clienteRepository;

    public ClienteController(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @GetMapping
    public String listarClientes(Model model) {
        model.addAttribute("clientes", clienteRepository.findAll());
        return "cliente-list";
    }

    @GetMapping("/form")
    public String mostrarFormulario(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "cliente-form";
    }

    @PostMapping
    public String guardarCliente(@ModelAttribute Cliente cliente) {
        clienteRepository.save(cliente);
        return "redirect:/cliente";
    }
}
