package com.example.demo.service;

import com.example.demo.model.MovimientoInventario;

import java.util.List;

public interface MovimientoInventarioService {

    List<MovimientoInventario> listarTodos();

    List<MovimientoInventario>
    listarPorProducto(Integer idProducto);
}