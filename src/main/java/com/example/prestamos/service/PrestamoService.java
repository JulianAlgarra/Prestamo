package com.example.prestamos.service;

import com.example.prestamos.dto.PrestamoRequest;
import com.example.prestamos.model.Prestamo;
import java.util.List;

public interface PrestamoService {
    List<Prestamo> listarPrestamos();
    List<Prestamo> listarPrestamosPorHerramienta(Long herramientaId);
    Prestamo registrarPrestamo(PrestamoRequest request);
}