package com.madsoftware.proyecto_final.repository;

import com.madsoftware.proyecto_final.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}