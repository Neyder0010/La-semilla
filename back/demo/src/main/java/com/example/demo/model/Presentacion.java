package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.math.BigDecimal;


@Entity
@Table(name = "presentaciones") // Apunta a la tabla de tu diagrama
public class Presentacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_presentacion")
    private Integer idPresentacion;

    @Column(nullable = false, length = 100)
    private String nombre; // Mapea a "nombre"

    @Column(name = "equivalencia_unidades", nullable = false)
    private Integer equivalenciaUnidades = 1; // Mapea a "equivalencia_unidades"

    @Column(name = "precio_venta", nullable = false)
    private BigDecimal precioVenta = BigDecimal.ZERO; // Mapea a "precio_venta"

    @Column(nullable = false)
    private Boolean activo = true; // Mapea al campo "activo BIT(1)"

    @ManyToOne
    @JoinColumn(name = "id_producto", nullable = false, foreignKey = @ForeignKey(name = "FK_presentacion_producto"))
    @JsonBackReference // Evita bucles infinitos en el JSON
    private Producto producto;

    // --- GETTERS Y SETTERS ---
    public Integer getIdPresentacion() { return idPresentacion; }
    public void setIdPresentacion(Integer idPresentacion) { this.idPresentacion = idPresentacion; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Integer getEquivalenciaUnidades() { return equivalenciaUnidades; }
    public void setEquivalenciaUnidades(Integer equivalenciaUnidades) { this.equivalenciaUnidades = equivalenciaUnidades; }

    public BigDecimal getPrecioVenta() { return precioVenta; }
    public void setPrecioVenta(BigDecimal precioVenta) { this.precioVenta = precioVenta; }

    public Boolean getActivo() { return activo; }
    public void setActivo(Boolean activo) { this.activo = activo; }

    public Producto getProducto() { return producto; }
    public void setProducto(Producto producto) { this.producto = producto; }
}