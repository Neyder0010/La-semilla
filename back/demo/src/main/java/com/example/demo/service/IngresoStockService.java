package com.example.demo.service;

import com.example.demo.model.IngresoStock;
import java.util.List;

public interface IngresoStockService {
    List<IngresoStock> listarTodos();
    IngresoStock registrarIngreso(IngresoStock ingreso);
}