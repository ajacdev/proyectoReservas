package com.madsoftware.proyecto_final.model;

import jakarta.persistence.*;

@Entity
public class Reserva {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Cliente cliente;

    @ManyToOne
    private Evento evento;

    // Getters y Setters
}
