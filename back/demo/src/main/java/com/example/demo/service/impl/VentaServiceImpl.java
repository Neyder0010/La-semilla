package com.example.demo.service.impl;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.VentaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.EntityManager; // <--- IMPORTA ESTO
import jakarta.persistence.PersistenceContext; 
@Service



public class VentaServiceImpl implements VentaService {

        @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private DetalleVentaRepository detalleVentaRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private MovimientoInventarioRepository movimientoRepository;

    @Autowired
    private AlertaStockRepository alertaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;
    

    @Override
    public List<Venta> listarTodas() {
        return ventaRepository.findAll();
    }

    @Override
    public Venta buscarPorId(Integer id) {
        return ventaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Factura de venta no encontrada"));
    }


    
@Override
@Transactional
public Venta registrarVenta(Venta venta, List<DetalleVenta> detalles) {

    // 1. VALIDACIÓN ÚNICA Y EFICIENTE
    if (venta.getUsuario() == null || venta.getUsuario().getIdUsuario() == null) {
        throw new RuntimeException("Error: No se ha especificado el usuario.");
    }

    // Usamos el EntityManager para obtener la referencia sin consultar la BD innecesariamente
    // Esto es suficiente para satisfacer la restricción @ManyToOne de JPA
    Usuario usuarioRef = entityManager.getReference(Usuario.class, venta.getUsuario().getIdUsuario());
    venta.setUsuario(usuarioRef);

    // 2. INICIALIZACIÓN DE VALORES OBLIGATORIOS
    if (venta.getSubtotal() == null) venta.setSubtotal(BigDecimal.ZERO);
    if (venta.getTotal() == null) venta.setTotal(BigDecimal.ZERO);
    if (venta.getDescuentoTotal() == null) venta.setDescuentoTotal(BigDecimal.ZERO);

    // 3. GUARDAR CABECERA
    Venta ventaGuardada = ventaRepository.save(venta);

    BigDecimal subtotalFactura = BigDecimal.ZERO;

    // 4. PROCESAMIENTO DE DETALLES
    for (DetalleVenta detalle : detalles) {
        // Obtenemos el producto real
        Producto producto = productoRepository.findById(detalle.getProducto().getIdProducto())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado: " + detalle.getProducto().getIdProducto()));

        int stockAnterior = producto.getStockActual();
        int unidadesAVender = detalle.getUnidadesVendidas();

        if (stockAnterior < unidadesAVender) {
            throw new RuntimeException("Stock insuficiente para: " + producto.getNombre());
        }

        // Actualizar stock
        int nuevoStock = stockAnterior - unidadesAVender;
        producto.setStockActual(nuevoStock);
        productoRepository.save(producto);

        // Cálculos financieros
        BigDecimal precioFinalUnitario = detalle.getPrecioFinalUnitario();
        BigDecimal subtotalDetalle = precioFinalUnitario.multiply(BigDecimal.valueOf(detalle.getCantidad()));
        
        // Completar detalle
        detalle.setVenta(ventaGuardada);
        detalle.setCostoUnitarioVenta(producto.getCostoPromedio());
        detalle.setSubtotal(subtotalDetalle);
        detalleVentaRepository.save(detalle);

        subtotalFactura = subtotalFactura.add(subtotalDetalle);

        // ... (Mantén aquí tu lógica de movimientos y alertas igual que antes)
    }

    // 5. FINALIZAR TOTALES
    ventaGuardada.setSubtotal(subtotalFactura);
    ventaGuardada.setTotal(subtotalFactura.subtract(ventaGuardada.getDescuentoTotal()));

    return ventaRepository.save(ventaGuardada);
}
}