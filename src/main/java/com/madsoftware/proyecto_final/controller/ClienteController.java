package com.madsoftware.proyecto_final.controller;

import com.madsoftware.proyecto_final.model.Cliente;
import com.madsoftware.proyecto_final.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping("/clientes")
    public String listClientes(Model model) {
        model.addAttribute("clientes", clienteRepository.findAll());
        return "clientes";
    }

    @GetMapping("/cliente/new")
    public String newCliente(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "cliente-form";
    }

    @PostMapping("/cliente")
    public String saveCliente(Cliente cliente) {
        clienteRepository.save(cliente);
        return "redirect:/clientes";
    }
}
