package com.example.demo.service;

import com.example.demo.model.Venta;
import com.example.demo.model.DetalleVenta;
import java.util.List;

public interface VentaService {
    List<Venta> listarTodas();
    Venta buscarPorId(Integer id);
    // Este método procesará la cabecera de la venta junto con su lista de productos
    Venta registrarVenta(Venta venta, List<DetalleVenta> detalles);
}