package com.example.demo.repository;

import com.example.demo.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {
    // Aquí puedes crear en el futuro búsquedas por nombre si lo necesitas:
    // List<Producto> findByNombreContainingIgnoreCase(String nombre);
}