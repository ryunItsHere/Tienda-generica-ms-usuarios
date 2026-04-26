package com.tiendagenerica.ms_usuarios.controller;

import com.tiendagenerica.ms_usuarios.dto.*;
import com.tiendagenerica.ms_usuarios.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")

public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // Crear usuario (solo ADMIN)
    @PostMapping("/crear")
    public ResponseEntity<UsuarioDTO> crear(
            @Valid @RequestBody CrearUsuarioRequest request) {
        return ResponseEntity.ok(usuarioService.crearUsuario(request));
    }

    // Listar todos (solo ADMIN)
    @GetMapping("/listar")
    public ResponseEntity<List<UsuarioDTO>> listar() {
        return ResponseEntity.ok(usuarioService.listarTodos());
    }

    // Buscar por cédula (solo ADMIN)
    @GetMapping("/buscar/{cedula}")
    public ResponseEntity<UsuarioDTO> buscar(
            @PathVariable String cedula) {
        return ResponseEntity.ok(
                usuarioService.buscarPorCedula(cedula));
    }

    // Actualizar usuario (solo ADMIN)
    @PutMapping("/actualizar/{cedula}")
    public ResponseEntity<UsuarioDTO> actualizar(
            @PathVariable String cedula,
            @Valid @RequestBody CrearUsuarioRequest request) {
        return ResponseEntity.ok(
                usuarioService.actualizar(cedula, request));
    }

    // Activar o desactivar usuario (solo ADMIN)
    @PatchMapping("/estado/{cedula}")
    public ResponseEntity<String> cambiarEstado(
            @PathVariable String cedula) {
        return ResponseEntity.ok(
                usuarioService.cambiarEstado(cedula));
    }

    // Este endpoint lo usa MS-Auth internamente para el login
    @GetMapping("/credenciales/{username}")
    public ResponseEntity<CredencialesResponse> credenciales(
            @PathVariable String username) {
        return ResponseEntity.ok(
                usuarioService.obtenerCredenciales(username));
    }
}