package com.madsoftware.proyecto_final.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
}
