package com.madsoftware.proyecto_final.repository;

import com.madsoftware.proyecto_final.model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventoRepository extends JpaRepository<Evento, Long> {
}
