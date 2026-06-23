package com.example.demo.controller;

import com.example.demo.model.Venta;
import com.example.demo.model.DetalleVenta;
import com.example.demo.service.VentaService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/ventas")
// Aplicamos la misma configuración de CORS que en tus otros controladores
@CrossOrigin(origins = "http://localhost:3000", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, allowedHeaders = "*", allowCredentials = "true")
public class VentaController {

    private final VentaService ventaService;

    // Inyección por constructor
    public VentaController(VentaService ventaService) {
        this.ventaService = ventaService;
    }

    @GetMapping
    public List<Venta> obtenerTodas() {
        return ventaService.listarTodas();
    }

    // Estructura interna (DTO) para recibir la venta y detalles en un solo bloque
    public static class RequestVenta {
        public Venta venta;
        public List<DetalleVenta> detalles;
    }

@PostMapping
public ResponseEntity<?> procesarVenta(@RequestBody RequestVenta request) {
    try {
        // Verificar existencia de los objetos principales
        if (request.venta == null) return ResponseEntity.badRequest().body("Venta nula");
        
        // Verificación crítica: ¿Es el objeto usuario nulo o el ID es nulo?
        if (request.venta.getUsuario() == null) {
            System.out.println("ERROR: El objeto 'usuario' dentro de 'venta' es nulo.");
        } else {
            System.out.println("ID Usuario recibido: " + request.venta.getUsuario().getIdUsuario());
        }

        return ResponseEntity.ok(ventaService.registrarVenta(request.venta, request.detalles));
    } catch (Exception e) {
        e.printStackTrace(); // Esto imprimirá el error real en tu consola de Java
        return ResponseEntity.status(500).body("Error: " + e.getMessage());
    }
}
}

    
