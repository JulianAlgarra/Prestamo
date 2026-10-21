package com.example.prestamos.controller;

import com.example.prestamos.dto.PrestamoRequest;
import com.example.prestamos.model.Prestamo;
import com.example.prestamos.service.PrestamoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prestamos")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class PrestamoController {


    private final PrestamoService prestamoService;

    @GetMapping
    public ResponseEntity<List<Prestamo>> consultarTodosLosPrestamos() {
        return ResponseEntity.ok(prestamoService.listarPrestamos());
    }

    @PostMapping
    public ResponseEntity<?> registrarPrestamo(@Valid @RequestBody PrestamoRequest request) {
        try {
            Prestamo prestamo = prestamoService.registrarPrestamo(request);
            return new ResponseEntity<>(prestamo, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno: " + e.getMessage());
        }
    }

    @GetMapping("/herramienta/{id}")
    public ResponseEntity<List<Prestamo>> consultarPrestamosPorHerramienta(@PathVariable Long id) {
        return ResponseEntity.ok(prestamoService.listarPrestamosPorHerramienta(id));
    }
}