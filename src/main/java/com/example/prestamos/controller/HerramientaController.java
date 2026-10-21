package com.example.prestamos.controller;

import com.example.prestamos.model.Herramienta;
import com.example.prestamos.service.HerramientaService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/herramientas")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class HerramientaController {

    private final HerramientaService herramientaService;

    @GetMapping
    public ResponseEntity<List<Herramienta>> consultarHerramientas() {
        return ResponseEntity.ok(herramientaService.listarHerramientas());
    }

    @PostMapping
    public ResponseEntity<Herramienta> registrarHerramienta(@Valid @RequestBody Herramienta herramienta) {
        Herramienta nueva = herramientaService.guardarHerramienta(herramienta);
        return new ResponseEntity<>(nueva, HttpStatus.CREATED);
    }
}