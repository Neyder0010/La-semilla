package com.example.demo.repository;

import com.example.demo.model.AlertaStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AlertaStockRepository extends JpaRepository<AlertaStock, Integer> {
    // Para traer de golpe solo las alertas que el administrador no ha revisado todavía
    List<AlertaStock> findByAlEstado(AlertaStock.Estado estado);
}