package com.example.demo.service.impl;

import com.example.demo.model.MovimientoInventario;
import com.example.demo.repository.MovimientoInventarioRepository;
import com.example.demo.service.MovimientoInventarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovimientoInventarioServiceImpl
        implements MovimientoInventarioService {

    @Autowired
    private MovimientoInventarioRepository repository;

    @Override
    public List<MovimientoInventario> listarTodos() {
        return repository.findAll();
    }

    @Override
    public List<MovimientoInventario>
    listarPorProducto(Integer idProducto) {

        return repository.findByProductoIdProducto(idProducto);
    }
}