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

	public UsuarioDTO() {
		// TODO Auto-generated constructor stub
	}

	public UsuarioDTO(Long id, String cedula, String nombreCompleto, String email, String username, boolean activo,
			String rol) {
		super();
		this.id = id;
		this.cedula = cedula;
		this.nombreCompleto = nombreCompleto;
		this.email = email;
		this.username = username;
		this.activo = activo;
		this.rol = rol;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

}