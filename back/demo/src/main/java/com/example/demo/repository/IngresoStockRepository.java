package com.example.demo.repository;

import com.example.demo.model.IngresoStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngresoStockRepository extends JpaRepository<IngresoStock, Integer> {
}