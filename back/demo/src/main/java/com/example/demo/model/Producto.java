package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonManagedReference; // ---- NUEVO ----
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList; // ---- NUEVO ----
import java.util.List; // ---- NUEVO ----

@Entity
@Table(name = "productos")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Integer idProducto;

    @Column(nullable = false, length = 150)
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "id_categoria", foreignKey = @ForeignKey(name = "FK_producto_categoria"))
    private Categoria categoria;

    @Column(name = "stock_actual")
    private Integer stockActual = 0;

    @Column(name = "stock_minimo")
    private Integer stockMinimo = 0;

    @Column(name = "costo_promedio")
    private BigDecimal costoPromedio = BigDecimal.ZERO;

    @Column(name = "fecha_registro", updatable = false)
    private LocalDateTime fechaRegistro;

    // ---- NUEVO: Relación en cascada hacia Presentacion ----
    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference // Evita bucles infinitos en el JSON mapeando la relación bidireccional
    private List<Presentacion> presentaciones = new ArrayList<>();

    @PrePersist
    protected void onCreate() { this.fechaRegistro = LocalDateTime.now(); }

    // Getters y Setters
    public Integer getIdProducto() { return idProducto; }
    public void setIdProducto(Integer idProducto) { this.idProducto = idProducto; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public Categoria getCategoria() { return categoria; }
    public void setCategoria(Categoria categoria) { this.categoria = categoria; }
    public Integer getStockActual() { return stockActual; }
    public void setStockActual(Integer stockActual) { this.stockActual = stockActual; }
    public Integer getStockMinimo() { return stockMinimo; }
    public void setStockMinimo(Integer stockMinimo) { this.stockMinimo = stockMinimo; }
    public BigDecimal getCostoPromedio() { return costoPromedio; }
    public void setCostoPromedio(BigDecimal costoPromedio) { this.costoPromedio = costoPromedio; }
    public LocalDateTime getFechaRegistro() { return fechaRegistro; }

    // ---- NUEVO: Getter y Setter de las Presentaciones ----
    public List<Presentacion> getPresentaciones() { return presentaciones; }
    public void setPresentaciones(List<Presentacion> presentaciones) { 
        this.presentaciones = presentaciones; 
        // Sincroniza automáticamente la relación bidireccional en memoria
        if (presentaciones != null) {
            for (Presentacion p : presentaciones) {
                p.setProducto(this);
            }
        }
    }
}