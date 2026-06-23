package com.example.demo.service;

import com.example.demo.model.Presentacion;
import java.util.List;

public interface PresentacionService {

    List<Presentacion> listarTodas();

    Presentacion guardar(Presentacion presentacion);

    Presentacion buscarPorId(Integer id);

    void eliminar(Integer id);
}