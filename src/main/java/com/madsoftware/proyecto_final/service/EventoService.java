package com.madsoftware.proyecto_final.service;

import com.madsoftware.proyecto_final.model.Evento;
import com.madsoftware.proyecto_final.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventoService {

    private final EventoRepository eventoRepository;

    @Autowired
    public EventoService(EventoRepository eventoRepository) {
        this.eventoRepository = eventoRepository;
    }

    public List<Evento> findAll() {
        return eventoRepository.findAll();
    }

    public Evento save(Evento evento) {
        return eventoRepository.save(evento);
    }

    // Añade más métodos según sea necesario
}