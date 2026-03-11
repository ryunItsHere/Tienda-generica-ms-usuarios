package com.tiendagenerica.ms_usuarios.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
@Entity
@Table(name = "usuarios")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "La cédula es obligatoria")
	@Column(unique = true, nullable = false)
	private String cedula;

	@NotBlank(message = "El nombre completo es obligatorio")
	@Column(name = "nombre_completo", nullable = false)
	private String nombreCompleto;

	@NotBlank(message = "El email es obligatorio")
	@Email(message = "El email no tiene formato válido")
	@Column(unique = true, nullable = false)
	private String email;

	@NotBlank(message = "El username es obligatorio")
	@Column(unique = true, nullable = false)
	private String username;

	@NotBlank(message = "La contraseña es obligatoria")
	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	private boolean activo = true;

	// Relación con Rol
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_rol", nullable = false)
	private Rol rol;

	public Usuario() {
		// TODO Auto-generated constructor stub
	}

	public Usuario(Long id, @NotBlank(message = "La cédula es obligatoria") String cedula,
			@NotBlank(message = "El nombre completo es obligatorio") String nombreCompleto,
			@NotBlank(message = "El email es obligatorio") @Email(message = "El email no tiene formato válido") String email,
			@NotBlank(message = "El username es obligatorio") String username,
			@NotBlank(message = "La contraseña es obligatoria") String password, boolean activo, Rol rol) {
		super();
		this.id = id;
		this.cedula = cedula;
		this.nombreCompleto = nombreCompleto;
		this.email = email;
		this.username = username;
		this.password = password;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

}