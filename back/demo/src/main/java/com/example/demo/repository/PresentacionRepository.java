package com.example.demo.repository;

import com.example.demo.model.Presentacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PresentacionRepository extends JpaRepository<Presentacion, Integer> {
    
    List<Presentacion> findByActivoTrue();
}