package com.madsoftware.proyecto_final.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import java.time.LocalDate;

@Entity
public class Evento {
    @Id
    @GeneratedValue
    private Long id;
    private String nombre;
    private LocalDate fecha;

    // Getters y Setters
}
