package com.madsoftware.proyecto_final.service;

import com.madsoftware.proyecto_final.model.Reserva;
import com.madsoftware.proyecto_final.repository.ReservaRepository;
import org.springframework.stereotype.Service;

@Service
public class ReservaService {

    private final ReservaRepository reservaRepository;

    public ReservaService(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    public boolean verificarDisponibilidad(Reserva nuevaReserva) {
        int reservasExistentes = reservaRepository.countByEventoAndFechaHora(nuevaReserva.getEvento(), nuevaReserva.getFechaHora());
        return reservasExistentes < nuevaReserva.getEvento().getCapacidad();
    }
}