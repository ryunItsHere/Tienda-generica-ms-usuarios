package com.tiendagenerica.ms_usuarios.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class CrearUsuarioRequest {

    @NotBlank(message = "La cédula es obligatoria")
    private String cedula;

    @NotBlank(message = "El nombre completo es obligatorio")
    private String nombreCompleto;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El email no tiene formato válido")
    private String email;

    @NotBlank(message = "El username es obligatorio")
    private String username;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 8, message = "La contraseña debe tener mínimo 8 caracteres")
    private String password;

    @NotNull(message = "El rol es obligatorio")
    private Long idRol; // 1=ADMIN, 2=EMPLEADO
}