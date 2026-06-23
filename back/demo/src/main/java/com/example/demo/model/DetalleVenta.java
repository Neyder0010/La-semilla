package com.example.demo.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "detalle_venta", indexes = {
    @Index(name = "idx_venta", columnList = "id_venta"),
    @Index(name = "idx_producto", columnList = "id_producto")
})
public class DetalleVenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle")
    private Integer idDetalle;

    @ManyToOne
    @JoinColumn(name = "id_venta", nullable = false)
    private Venta venta;

    @ManyToOne
    @JoinColumn(name = "id_producto", nullable = false)
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "id_presentacion", nullable = false)
    private Presentacion presentacion;

    @Column(nullable = false)
    private Integer cantidad;

    @Column(name = "unidades_vendidas", nullable = false)
    private Integer unidadesVendidas;

    @Column(name = "precio_unitario", nullable = false)
    private BigDecimal precioUnitario;

    @Column(name = "descuento_unitario")
    private BigDecimal descuentoUnitario = BigDecimal.ZERO;

    @Column(name = "precio_final_unitario", nullable = false)
    private BigDecimal precioFinalUnitario;

    @Column(nullable = false)
    private BigDecimal subtotal;

    @Column(name = "costo_unitario_venta", nullable = false)
    private BigDecimal costoUnitarioVenta;

    @Column(name = "ganancia_unitaria", nullable = false)
    private BigDecimal gananciaUnitaria;

    @Column(name = "ganancia_total", nullable = false)
    private BigDecimal gananciaTotal;

    // Getters y Setters...
    public Integer getIdDetalle() { return idDetalle; }
    public void setIdDetalle(Integer idDetalle) { this.idDetalle = idDetalle; }
    public Venta getVenta() { return venta; }
    public void setVenta(Venta venta) { this.venta = venta; }
    public Producto getProducto() { return producto; }
    public void setProducto(Producto producto) { this.producto = producto; }
    public Presentacion getPresentacion() { return presentacion; }
    public void setPresentacion(Presentacion presentacion) { this.presentacion = presentacion; }
    public Integer getCantidad() { return cantidad; }
    public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }
    public Integer getUnidadesVendidas() { return unidadesVendidas; }
    public void setUnidadesVendidas(Integer unidadesVendidas) { this.unidadesVendidas = unidadesVendidas; }
    public BigDecimal getPrecioUnitario() { return precioUnitario; }
    public void setPrecioUnitario(BigDecimal precioUnitario) { this.precioUnitario = precioUnitario; }
    public BigDecimal getDescuentoUnitario() { return descuentoUnitario; }
    public void setDescuentoUnitario(BigDecimal descuentoUnitario) { this.descuentoUnitario = descuentoUnitario; }
    public BigDecimal getPrecioFinalUnitario() { return precioFinalUnitario; }
    public void setPrecioFinalUnitario(BigDecimal precioFinalUnitario) { this.precioFinalUnitario = precioFinalUnitario; }
    public BigDecimal getSubtotal() { return subtotal; }
    public void setSubtotal(BigDecimal subtotal) { this.subtotal = subtotal; }
    public BigDecimal getCostoUnitarioVenta() { return costoUnitarioVenta; }
    public void setCostoUnitarioVenta(BigDecimal costoUnitarioVenta) { this.costoUnitarioVenta = costoUnitarioVenta; }
    public BigDecimal getGananciaUnitaria() { return gananciaUnitaria; }
    public void setGananciaUnitaria(BigDecimal gananciaUnitaria) { this.gananciaUnitaria = gananciaUnitaria; }
    public BigDecimal getGananciaTotal() { return gananciaTotal; }
    public void setGananciaTotal(BigDecimal gananciaTotal) { this.gananciaTotal = gananciaTotal; }
}