package com.example.demo.controller;

import com.example.demo.model.Usuario;
import com.example.demo.service.UsuarioService;
import java.util.List;

import java.util.Collections;
import com.example.demo.model.LoginRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "http://localhost:3000", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, allowedHeaders = "*", allowCredentials = "true")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(
            UsuarioService usuarioService) {

        this.usuarioService = usuarioService;
    }

    @PostMapping("/login")
public ResponseEntity<?> login(@RequestBody LoginRequest request) {
    Usuario usuario = usuarioService.validarLogin(request);
    if (usuario != null) {
        // En lugar de devolver solo un String, devolvemos un objeto con el ID
        return ResponseEntity.ok(Collections.singletonMap("id", usuario.getIdUsuario()));
    } else {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales incorrectas");
    }
}

    @GetMapping
    public List<Usuario> listarTodos() {
        return usuarioService.listarTodos();
    }

    

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarPorId(
            @PathVariable Integer id) {

        return ResponseEntity.ok(
                usuarioService.buscarPorId(id));
    }

    @PostMapping
    public Usuario guardar(
            @RequestBody Usuario usuario) {

        return usuarioService.guardar(usuario);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizar(
        @PathVariable Integer id,
        @RequestBody Usuario usuario) {

    usuario.setIdUsuario(id);

    return ResponseEntity.ok(
            usuarioService.guardar(usuario));
}
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
        @PathVariable Integer id) {

    usuarioService.eliminar(id);

    return ResponseEntity.noContent().build();
    }

    
}