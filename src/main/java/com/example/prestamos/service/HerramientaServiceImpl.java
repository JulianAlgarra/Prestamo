package com.example.prestamos.service;

import com.example.prestamos.model.Herramienta;
import com.example.prestamos.repository.HerramientaRepository;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class HerramientaServiceImpl implements HerramientaService {


    private final HerramientaRepository herramientaRepository;


    @Override
    public List<Herramienta> listarHerramientas() {
        return herramientaRepository.findAll();
    }

    @Override
    public Herramienta guardarHerramienta(Herramienta herramienta) {
        if (herramienta.getCantidad() < 0) {
            throw new IllegalArgumentException("No se permiten cantidades negativas");
        }
        return herramientaRepository.save(herramienta);
    }
}