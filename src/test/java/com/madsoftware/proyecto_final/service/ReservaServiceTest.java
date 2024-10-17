package com.madsoftware.proyecto_final.service;

import com.madsoftware.proyecto_final.model.Evento;
import com.madsoftware.proyecto_final.model.Reserva;
import com.madsoftware.proyecto_final.repository.ReservaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReservaServiceTest {

    @Mock
    private ReservaRepository reservaRepository;

    @InjectMocks
    private ReservaService reservaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void verificarDisponibilidad_cuandoHayDisponibilidad_debeRetornarTrue() {
        Evento evento = new Evento();
        evento.setCapacidad(10);
        
        Reserva nuevaReserva = new Reserva();
        nuevaReserva.setEvento(evento);
        nuevaReserva.setFechaHora(LocalDateTime.now());

        when(reservaRepository.countByEventoAndFechaHora(evento, nuevaReserva.getFechaHora())).thenReturn(5);

        boolean resultado = reservaService.verificarDisponibilidad(nuevaReserva);

        assertTrue(resultado);
        verify(reservaRepository, times(1)).countByEventoAndFechaHora(evento, nuevaReserva.getFechaHora());
    }

    @Test
    void verificarDisponibilidad_cuandoNoHayDisponibilidad_debeRetornarFalse() {
        Evento evento = new Evento();
        evento.setCapacidad(10);
        
        Reserva nuevaReserva = new Reserva();
        nuevaReserva.setEvento(evento);
        nuevaReserva.setFechaHora(LocalDateTime.now());

        when(reservaRepository.countByEventoAndFechaHora(evento, nuevaReserva.getFechaHora())).thenReturn(10);

        boolean resultado = reservaService.verificarDisponibilidad(nuevaReserva);

        assertFalse(resultado);
        verify(reservaRepository, times(1)).countByEventoAndFechaHora(evento, nuevaReserva.getFechaHora());
    }
}