package com.example.demo.controller;

import com.example.demo.model.IngresoStock;
import com.example.demo.service.IngresoStockService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/ingresos-stock")
// Aplicamos la configuración estándar que sí funciona en tu proyecto
@CrossOrigin(origins = "http://localhost:3000", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, allowedHeaders = "*", allowCredentials = "true")
public class IngresoStockController {

    private final IngresoStockService ingresoStockService;

    public IngresoStockController(IngresoStockService ingresoStockService) {
        this.ingresoStockService = ingresoStockService;
    }

    @GetMapping
    public List<IngresoStock> listarTodos() {
        return ingresoStockService.listarTodos();
    }

    @PostMapping
    public IngresoStock registrarIngreso(@RequestBody IngresoStock ingreso) {
        return ingresoStockService.registrarIngreso(ingreso);
    }
}