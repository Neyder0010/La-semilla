package com.example.demo.controller;

import com.example.demo.model.MovimientoInventario;
import com.example.demo.service.MovimientoInventarioService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/movimientos")
// Estandarizamos el CORS para asegurar la comunicación con el frontend
@CrossOrigin(origins = "http://localhost:3000", methods = {RequestMethod.GET, RequestMethod.OPTIONS}, allowedHeaders = "*", allowCredentials = "true")
public class MovimientoInventarioController {

    private final MovimientoInventarioService service;

    // Inyección por constructor
    public MovimientoInventarioController(MovimientoInventarioService service) {
        this.service = service;
    }

    @GetMapping
    public List<MovimientoInventario> listarTodos() {
        return service.listarTodos();
    }

    @GetMapping("/producto/{idProducto}")
    public List<MovimientoInventario> listarPorProducto(@PathVariable Integer idProducto) {
        return service.listarPorProducto(idProducto);
    }
}