package com.example.demo.service.impl;

import com.example.demo.model.LoginRequest;
import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepository;
import com.example.demo.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public List<Usuario> listarTodos() { return usuarioRepository.findAll(); }

    @Override
    public Usuario guardar(Usuario usuario) { return usuarioRepository.save(usuario); }

    @Override
    public Usuario buscarPorId(Integer id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

        @Override
    public void eliminar(Integer id) {
        usuarioRepository.deleteById(id);
    }

    @Override
public Usuario validarLogin(LoginRequest loginRequest) {
    // Ahora usa los getters del objeto
    return usuarioRepository.findByUsuarioAndContrasena(
        loginRequest.getUsuario(), 
        loginRequest.getContrasena()
    ).orElse(null);
}
}