package com.tiendagenerica.ms_usuarios.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CredencialesResponse {
    private String username;
    private String password;   // encriptada
    private String rol;        // ADMIN o EMPLEADO
    private boolean activo;
}