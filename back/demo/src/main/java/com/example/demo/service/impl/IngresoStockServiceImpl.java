package com.example.demo.service.impl;

import com.example.demo.model.IngresoStock;
import com.example.demo.model.MovimientoInventario;
import com.example.demo.model.Presentacion;
import com.example.demo.model.Producto;
import com.example.demo.repository.IngresoStockRepository;
import com.example.demo.repository.MovimientoInventarioRepository;
import com.example.demo.repository.PresentacionRepository;
import com.example.demo.repository.ProductoRepository;
import com.example.demo.service.IngresoStockService;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class IngresoStockServiceImpl
        implements IngresoStockService {

    private final IngresoStockRepository ingresoStockRepository;
    private final ProductoRepository productoRepository;
    private final PresentacionRepository presentacionRepository;
    private final MovimientoInventarioRepository movimientoRepository;

    public IngresoStockServiceImpl(
            IngresoStockRepository ingresoStockRepository,
            ProductoRepository productoRepository,
            PresentacionRepository presentacionRepository,
            MovimientoInventarioRepository movimientoRepository) {

        this.ingresoStockRepository = ingresoStockRepository;
        this.productoRepository = productoRepository;
        this.presentacionRepository = presentacionRepository;
        this.movimientoRepository = movimientoRepository;
    }

    @Override
    public List<IngresoStock> listarTodos() {
        return ingresoStockRepository.findAll();
    }

    @Override
    @Transactional
    public IngresoStock registrarIngreso(
            IngresoStock ingreso) {

        // OBTENER PRODUCTO COMPLETO
        Producto producto = productoRepository.findById(
                ingreso.getProducto().getIdProducto())
                .orElseThrow(() ->
                        new RuntimeException("Producto no encontrado"));

        // OBTENER PRESENTACION COMPLETA
        Presentacion presentacion =
                presentacionRepository.findById(
                        ingreso.getPresentacion()
                                .getIdPresentacion())
                .orElseThrow(() ->
                        new RuntimeException("Presentación no encontrada"));

        // CALCULAR UNIDADES
        int unidadesIngresadas =
                ingreso.getCantidadComprada()
                * presentacion.getEquivalenciaUnidades();

        int stockAnterior = producto.getStockActual();
        int nuevoStock = stockAnterior + unidadesIngresadas;

        // ACTUALIZAR PRODUCTO
        producto.setStockActual(nuevoStock);

        // CALCULAR COSTO UNITARIO
        BigDecimal costoUnitario =
                ingreso.getCostoTotal()
                        .divide(
                                BigDecimal.valueOf(unidadesIngresadas),
                                2,
                                java.math.RoundingMode.HALF_UP);

        producto.setCostoPromedio(costoUnitario);

        productoRepository.save(producto);

        // COMPLETAR DATOS DEL INGRESO
        ingreso.setProducto(producto);
        ingreso.setPresentacion(presentacion);
        ingreso.setUnidadesAgregadas(unidadesIngresadas);
        ingreso.setStockAnterior(stockAnterior);
        ingreso.setNuevoStock(nuevoStock);
        ingreso.setCostoUnitario(costoUnitario);

        // CREAR MOVIMIENTO
        MovimientoInventario movimiento =
                new MovimientoInventario();

        movimiento.setProducto(producto);
        movimiento.setTipo(
                MovimientoInventario.TipoMovimiento.Ingreso);
        movimiento.setCantidad(unidadesIngresadas);
        movimiento.setStockAnterior(stockAnterior);
        movimiento.setStockNuevo(nuevoStock);
        movimiento.setDescripcion("Ingreso de stock");

        movimientoRepository.save(movimiento);

        // GUARDAR INGRESO
        return ingresoStockRepository.save(ingreso);
    }
}