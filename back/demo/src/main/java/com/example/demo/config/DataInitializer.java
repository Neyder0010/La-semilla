package com.example.demo.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.List;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(JdbcTemplate jdbcTemplate) {
        return args -> {
            try {
                // Buscamos si existe el registro usando SQL directo
                List<Integer> ids = jdbcTemplate.queryForList(
                    "SELECT id_usuario FROM usuarios WHERE usuario = 'admin'", 
                    Integer.class
                );

                if (ids.isEmpty()) {
                    // Insertamos respetando las columnas exactas de tu log: 'contraseña' y 'rol'
                    jdbcTemplate.update(
                        "INSERT INTO usuarios (usuario, contraseña, nombre, rol, fecha_registro) " +
                        "VALUES (?, ?, ?, ?, NOW())",
                        "admin", "1234", "Administrador Semilla", "Administrador"
                    );
                    System.out.println("\n🌱 [La Semilla] ¡Usuario 'admin' creado exitosamente con JdbcTemplate!\n");
                } else {
                    System.out.println("\n🌱 [La Semilla] El usuario 'admin' ya existe. Saltando inicialización.\n");
                }
            } catch (Exception e) {
                System.err.println("\n⚠️ [La Semilla] Error al inicializar la base de datos: " + e.getMessage());
                e.printStackTrace();
            }

            // --- AQUÍ ES DONDE VAS A AGREGAR EL BLOQUE 2: PRODUCTOS ---
                    try {
            Integer count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM productos", Integer.class);
            if (count == 0) {
                // Nota: Asegúrate de que estos nombres de columnas coincidan EXACTAMENTE con tu BD
                jdbcTemplate.update("INSERT INTO productos (nombre, stock_actual, stock_minimo, costo_promedio, fecha_registro) VALUES (?, ?, ?, ?, ?)",
                        "Bolsa Negra", 8000, 1000, 300.0, java.time.LocalDateTime.now());
                
                Long idBolsa = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);
                
                // Inserta la presentación vinculada al ID del producto
                jdbcTemplate.update("INSERT INTO presentaciones (id_producto, nombre, equivalencia_unidades, precio_venta, activo) VALUES (?, ?, ?, ?, ?)", 
                        idBolsa, "Unidad", 1, 500.0, true);
                
                System.out.println("📦 [Semilla] Datos de prueba cargados correctamente.");
            }
        } catch (Exception e) {
            System.err.println("⚠️ Error en la semilla: " + e.getMessage());
            e.printStackTrace();
        }
        };
    }

    
}