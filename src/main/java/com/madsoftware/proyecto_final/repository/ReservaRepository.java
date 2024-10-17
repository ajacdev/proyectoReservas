package com.madsoftware.proyecto_final.repository;

import com.madsoftware.proyecto_final.model.Reserva;
import com.madsoftware.proyecto_final.model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    int countByEventoAndFechaHora(Evento evento, LocalDateTime fechaHora);
}