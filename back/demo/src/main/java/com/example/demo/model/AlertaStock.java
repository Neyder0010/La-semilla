package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "alertas_stock")
public class AlertaStock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_alerta")
    private Integer idAlerta;

    @ManyToOne
    @JoinColumn(name = "id_producto", nullable = false)
    private Producto producto;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String mensaje;

    @Enumerated(EnumType.STRING)
    private Estado alEstado = Estado.Pendiente;

    @Column(name = "fecha_alerta")
    private LocalDateTime fechaAlerta;

    @PrePersist
    protected void onCreate() { this.fechaAlerta = LocalDateTime.now(); }

    public enum Estado { Pendiente, Vista }

    // Getters y Setters...
    public Integer getIdAlerta() { return idAlerta; }
    public void setIdAlerta(Integer idAlerta) { this.idAlerta = idAlerta; }
    public Producto getProducto() { return producto; }
    public void setProducto(Producto producto) { this.producto = producto; }
    public String getMensaje() { return mensaje; }
    public void setMensaje(String mensaje) { this.mensaje = mensaje; }
    public Estado getAlEstado() { return alEstado; } // Cambiado a alEstado
    public void setAlEstado(Estado alEstado) { this.alEstado = alEstado; }
    public LocalDateTime getFechaAlerta() { return fechaAlerta; }
}