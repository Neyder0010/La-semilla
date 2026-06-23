package com.example.demo.controller;

import com.example.demo.model.Presentacion;
import com.example.demo.service.PresentacionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/presentaciones")
// Igualamos la configuración de CORS a la del UsuarioController
@CrossOrigin(origins = "http://localhost:3000", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS}, allowedHeaders = "*", allowCredentials = "true")
public class PresentacionController {

    private final PresentacionService presentacionService;

    public PresentacionController(PresentacionService presentacionService) {
        this.presentacionService = presentacionService;
    }

    @GetMapping
    public List<Presentacion> listarTodas() {
        return presentacionService.listarTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Presentacion> buscarPorId(@PathVariable Integer id) {
        Presentacion p = presentacionService.buscarPorId(id);
        return (p != null) ? ResponseEntity.ok(p) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public Presentacion guardar(@RequestBody Presentacion presentacion) {
        return presentacionService.guardar(presentacion);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Presentacion> actualizar(@PathVariable Integer id, @RequestBody Presentacion presentacion) {
        presentacion.setIdPresentacion(id);
        return ResponseEntity.ok(presentacionService.guardar(presentacion));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        presentacionService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}