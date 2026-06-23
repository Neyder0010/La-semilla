package com.example.demo.service;

import com.example.demo.model.Usuario;
import java.util.List;
import com.example.demo.model.LoginRequest;


public interface UsuarioService {
    Usuario validarLogin(LoginRequest loginRequest);
    List<Usuario> listarTodos();
    Usuario guardar(Usuario usuario);
    Usuario buscarPorId(Integer id);
    void eliminar(Integer id);
}