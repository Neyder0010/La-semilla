package com.example.demo.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "ventas")
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_venta")
    private Integer idVenta;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @Column(nullable = false)
    private BigDecimal subtotal;

    @Column(name = "descuento_total")
    private BigDecimal descuentoTotal = BigDecimal.ZERO;

    @Column(nullable = false)
    private BigDecimal total;

    @Enumerated(EnumType.STRING)
    @Column(name = "metodo_pago")
    private MetodoPago metodoPago = MetodoPago.Efectivo;

    @Enumerated(EnumType.STRING)
    private Estado estado = Estado.Completada;

    @Column(name = "fecha_venta", updatable = false)
    private LocalDateTime fechaVenta;
    
    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL)
    private List<DetalleVenta> detalles;

    @PrePersist
    protected void onCreate() { this.fechaVenta = LocalDateTime.now(); }

    public enum MetodoPago { Efectivo, Transferencia, Nequi, Daviplata }
    public enum Estado { Completada, Pendiente, Cancelada }

    // Getters y Setters...
    public Integer getIdVenta() { return idVenta; }
    public void setIdVenta(Integer idVenta) { this.idVenta = idVenta; }
    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
    public BigDecimal getSubtotal() { return subtotal; }
    public void setSubtotal(BigDecimal subtotal) { this.subtotal = subtotal; }
    public BigDecimal getDescuentoTotal() { return descuentoTotal; }
    public void setDescuentoTotal(BigDecimal descuentoTotal) { this.descuentoTotal = descuentoTotal; }
    public BigDecimal getTotal() { return total; }
    public void setTotal(BigDecimal total) { this.total = total; } 
    public MetodoPago getMetodoPago() { return metodoPago; }
    public void setMetodoPago(MetodoPago metodoPago) { this.metodoPago = metodoPago; }
    public Estado getEstado() { return estado; }
    public void setEstado(Estado estado) { this.estado = estado; }
    public LocalDateTime getFechaVenta() { return fechaVenta; }
    
    public List<DetalleVenta> getDetalles() {
    return detalles;
}

public void setDetalles(List<DetalleVenta> detalles) {
    this.detalles = detalles;
}
}