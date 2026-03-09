package com.tiendagenerica.ms_usuarios;

import com.tiendagenerica.ms_usuarios.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MsUsuariosApplication implements CommandLineRunner {

	@Autowired
	private UsuarioService usuarioService;

	public static void main(String[] args) {
		SpringApplication.run(MsUsuariosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		usuarioService.inicializarDatos();
	}
}