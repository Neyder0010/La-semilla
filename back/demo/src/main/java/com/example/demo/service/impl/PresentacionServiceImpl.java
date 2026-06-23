package com.example.demo.service.impl;

import com.example.demo.model.Presentacion;
import com.example.demo.repository.PresentacionRepository;
import com.example.demo.service.PresentacionService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class PresentacionServiceImpl implements PresentacionService {

    private final PresentacionRepository presentacionRepository;

    public PresentacionServiceImpl(
            PresentacionRepository presentacionRepository) {

        this.presentacionRepository = presentacionRepository;
    }

    @Override
    public List<Presentacion> listarTodas() {
        return presentacionRepository.findAll();
    }

    @Override
    public Presentacion guardar(Presentacion presentacion) {
        return presentacionRepository.save(presentacion);
    }

    @Override
    public Presentacion buscarPorId(Integer id) {
        return presentacionRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Presentación no encontrada"));
    }

    @Override
    public void eliminar(Integer id) {
        presentacionRepository.deleteById(id);
    }
}