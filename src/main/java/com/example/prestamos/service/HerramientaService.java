package com.example.prestamos.service;

import com.example.prestamos.model.Herramienta;
import java.util.List;

public interface HerramientaService {
    List<Herramienta> listarHerramientas();
    Herramienta guardarHerramienta(Herramienta herramienta);
}