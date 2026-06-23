package com.example.demo.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "ingresos_stock")
public class IngresoStock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ingreso")
    private Integer idIngreso;

    @ManyToOne
    @JoinColumn(name = "id_producto", nullable = false)
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "id_presentacion", nullable = false)
    private Presentacion presentacion;

    @Column(name = "cantidad_comprada", nullable = false)
    private Integer cantidadComprada;

    @Column(name = "unidades_agregadas", nullable = false)
    private Integer unidadesAgregadas;

    @Column(name = "costo_total", nullable = false)
    private BigDecimal costoTotal;

    @Column(name = "costo_unitario", nullable = false)
    private BigDecimal costoUnitario;

    @Column(name = "stock_anterior", nullable = false)
    private Integer stockAnterior;

    @Column(name = "nuevo_stock", nullable = false)
    private Integer nuevoStock;

    @Column(name = "fecha_ingreso", updatable = false)
    private LocalDateTime fechaIngreso;

    @PrePersist
    protected void onCreate() { this.fechaIngreso = LocalDateTime.now(); }

    // Getters y Setters (Omitidos brevemente por espacio, pero debes generarlos en tu entorno)
    public Integer getIdIngreso() { return idIngreso; }
    public void setIdIngreso(Integer idIngreso) { this.idIngreso = idIngreso; }
    public Producto getProducto() { return producto; }
    public void setProducto(Producto producto) { this.producto = producto; }
    public Presentacion getPresentacion() { return presentacion; }
    public void setPresentacion(Presentacion presentacion) { this.presentacion = presentacion; }
    public Integer getCantidadComprada() { return cantidadComprada; }
    public void setCantidadComprada(Integer cantidadComprada) { this.cantidadComprada = cantidadComprada; }
    public Integer getUnidadesAgregadas() { return unidadesAgregadas; }
    public void setUnidadesAgregadas(Integer unidadesAgregadas) { this.unidadesAgregadas = unidadesAgregadas; }
    public BigDecimal getCostoTotal() { return costoTotal; }
    public void setCostoTotal(BigDecimal costoTotal) { this.costoTotal = costoTotal; }
    public BigDecimal getCostoUnitario() { return costoUnitario; }
    public void setCostoUnitario(BigDecimal costoUnitario) { this.costoUnitario = costoUnitario; }
    public Integer getStockAnterior() { return stockAnterior; }
    public void setStockAnterior(Integer stockAnterior) { this.stockAnterior = stockAnterior; }
    public Integer getNuevoStock() { return nuevoStock; }
    public void setNuevoStock(Integer nuevoStock) { this.nuevoStock = nuevoStock; }
    public LocalDateTime getFechaIngreso() { return fechaIngreso; }
}