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
    
    public CrearUsuarioRequest() {
		// TODO Auto-generated constructor stub
	}

	public CrearUsuarioRequest(@NotBlank(message = "La cédula es obligatoria") String cedula,
			@NotBlank(message = "El nombre completo es obligatorio") String nombreCompleto,
			@NotBlank(message = "El email es obligatorio") @Email(message = "El email no tiene formato válido") String email,
			@NotBlank(message = "El username es obligatorio") String username,
			@NotBlank(message = "La contraseña es obligatoria") @Size(min = 8, message = "La contraseña debe tener mínimo 8 caracteres") String password,
			@NotNull(message = "El rol es obligatorio") Long idRol) {
		super();
		this.cedula = cedula;
		this.nombreCompleto = nombreCompleto;
		this.email = email;
		this.username = username;
		this.password = password;
		this.idRol = idRol;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getIdRol() {
		return idRol;
	}

	public void setIdRol(Long idRol) {
		this.idRol = idRol;
	}
    
    
}