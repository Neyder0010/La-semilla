package com.example.demo.service;

import com.example.demo.model.Categoria;
import java.util.List;

public interface CategoriaService {
    List<Categoria> listarTodas();
    Categoria guardar(Categoria categoria);
    Categoria buscarPorId(Integer id);
    void eliminar(Integer id);
}