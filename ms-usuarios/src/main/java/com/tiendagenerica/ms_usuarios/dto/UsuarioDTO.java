package com.tiendagenerica.ms_usuarios.dto;

import lombok.Data;

@Data
public class UsuarioDTO {
    private Long id;
    private String cedula;
    private String nombreCompleto;
    private String email;
    private String username;
    private boolean activo;
    private String rol; // solo el nombre del rol
}