package com.madsoftware.proyecto_final.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.FutureOrPresent;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @NotNull(message = "El cliente es obligatorio")
    private Cliente cliente;

    @ManyToOne
    @NotNull(message = "El evento es obligatorio")
    private Evento evento;

    @FutureOrPresent(message = "La fecha debe ser futura o presente")
    private LocalDateTime fechaHora;
}