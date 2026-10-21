package com.example.prestamos.service;

import com.example.prestamos.dto.PrestamoRequest;
import com.example.prestamos.model.Herramienta;
import com.example.prestamos.model.Prestamo;
import com.example.prestamos.repository.HerramientaRepository;
import com.example.prestamos.repository.PrestamoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class PrestamoServiceImpl implements PrestamoService {


    private final PrestamoRepository prestamoRepository;


    private final HerramientaRepository herramientaRepository;

    @Override
    public List<Prestamo> listarPrestamos() {
        return prestamoRepository.findAll();
    }

    @Override
    public List<Prestamo> listarPrestamosPorHerramienta(Long herramientaId) {
        return prestamoRepository.findByHerramientaId(herramientaId);
    }

    @Override
    @Transactional
    public Prestamo registrarPrestamo(PrestamoRequest request) {
        // 1. Comprobar que la herramienta exista
        Herramienta herramienta = herramientaRepository.findById(request.getHerramientaId())
                .orElseThrow(() -> new IllegalArgumentException("La herramienta con ID " + request.getHerramientaId() + " no existe."));

        // 2. Reglas de negocio: Rechazar prestamos si cantidad <= 0 o superior a disponibles
        if (request.getCantidad() <= 0) {
            throw new IllegalArgumentException("La cantidad del préstamo debe ser mayor a cero.");
        }
        if (request.getCantidad() > herramienta.getCantidad()) {
            throw new IllegalArgumentException("Cantidad solicitada (" + request.getCantidad() +
                    ") es superior a las unidades disponibles (" + herramienta.getCantidad() + ").");
        }

        // 3. Descontar las unidades del inventario
        herramienta.setCantidad(herramienta.getCantidad() - request.getCantidad());
        herramientaRepository.save(herramienta);

        // 4. Crear y registrar la transaccion del préstamo
        Prestamo prestamo = new Prestamo();
        prestamo.setResponsable(request.getResponsable());
        prestamo.setFechaPrestamo(request.getFechaPrestamo() != null ? request.getFechaPrestamo() : LocalDate.now());
        prestamo.setCantidad(request.getCantidad());
        prestamo.setHerramienta(herramienta);

        return prestamoRepository.save(prestamo);
    }
}