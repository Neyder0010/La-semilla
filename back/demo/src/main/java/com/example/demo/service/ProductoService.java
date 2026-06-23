package com.example.demo.service;

import com.example.demo.model.Producto;
import java.util.List;

public interface ProductoService {
    List<Producto> listarTodos();
    Producto guardar(Producto producto);
    Producto buscarPorId(Integer id);
    void eliminar(Integer id);
}