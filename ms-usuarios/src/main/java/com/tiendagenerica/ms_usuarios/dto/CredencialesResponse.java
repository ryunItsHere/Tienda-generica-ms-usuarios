package com.tiendagenerica.ms_usuarios.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CredencialesResponse {
	private String username;
	private String password; // encriptada
	private String rol; // ADMIN o EMPLEADO
	private boolean activo;

	public CredencialesResponse() {
		// TODO Auto-generated constructor stub
	}

	public CredencialesResponse(String username, String password, String rol, boolean activo) {
		super();
		this.username = username;
		this.password = password;
		this.rol = rol;
		this.activo = activo;
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

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

}