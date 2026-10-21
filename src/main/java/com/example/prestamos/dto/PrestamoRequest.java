package com.example.prestamos.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDate;

@Data
public class PrestamoRequest {
    @NotBlank(message = "El responsable es obligatorio")
    private String responsable;

    private LocalDate fechaPrestamo;

    @Min(value = 1, message = "La cantidad debe ser mayor a cero")
    private int cantidad;

    @NotNull(message = "El ID de la herramienta es obligatorio")
    private Long herramientaId;
}