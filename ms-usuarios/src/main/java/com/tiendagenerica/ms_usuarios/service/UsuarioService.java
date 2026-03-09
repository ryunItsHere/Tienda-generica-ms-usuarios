package com.tiendagenerica.ms_usuarios.service;

import com.tiendagenerica.ms_usuarios.dto.*;
import com.tiendagenerica.ms_usuarios.model.Rol;
import com.tiendagenerica.ms_usuarios.model.Usuario;
import com.tiendagenerica.ms_usuarios.repository.RolRepository;
import com.tiendagenerica.ms_usuarios.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // ─────────────────────────────────────────
    // Crear usuario
    // ─────────────────────────────────────────
    public UsuarioDTO crearUsuario(CrearUsuarioRequest request) {

        if (usuarioRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("El username ya está en uso");
        }
        if (usuarioRepository.existsByCedula(request.getCedula())) {
            throw new RuntimeException("La cédula ya está registrada");
        }
        if (usuarioRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("El email ya está registrado");
        }

        Rol rol = rolRepository.findById(request.getIdRol())
                .orElseThrow(() -> new RuntimeException(
                        "Rol no encontrado con id: " + request.getIdRol()));

        Usuario usuario = new Usuario();
        usuario.setCedula(request.getCedula());
        usuario.setNombreCompleto(request.getNombreCompleto());
        usuario.setEmail(request.getEmail());
        usuario.setUsername(request.getUsername());
        usuario.setPassword(passwordEncoder.encode(request.getPassword()));
        usuario.setActivo(true);
        usuario.setRol(rol);

        return convertirADTO(usuarioRepository.save(usuario));
    }

    // ─────────────────────────────────────────
    // Listar todos
    // ─────────────────────────────────────────
    public List<UsuarioDTO> listarTodos() {
        return usuarioRepository.findAll()
                .stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    // ─────────────────────────────────────────
    // Buscar por cédula
    // ─────────────────────────────────────────
    public UsuarioDTO buscarPorCedula(String cedula) {
        Usuario usuario = usuarioRepository.findByCedula(cedula)
                .orElseThrow(() -> new RuntimeException(
                        "Usuario no encontrado con cédula: " + cedula));
        return convertirADTO(usuario);
    }

    // ─────────────────────────────────────────
    // Actualizar usuario
    // ─────────────────────────────────────────
    public UsuarioDTO actualizar(String cedula,
                                 CrearUsuarioRequest request) {
        Usuario usuario = usuarioRepository.findByCedula(cedula)
                .orElseThrow(() -> new RuntimeException(
                        "Usuario no encontrado con cédula: " + cedula));

        Rol rol = rolRepository.findById(request.getIdRol())
                .orElseThrow(() -> new RuntimeException(
                        "Rol no encontrado"));

        usuario.setNombreCompleto(request.getNombreCompleto());
        usuario.setEmail(request.getEmail());
        usuario.setPassword(
                passwordEncoder.encode(request.getPassword()));
        usuario.setRol(rol);

        return convertirADTO(usuarioRepository.save(usuario));
    }

    // ─────────────────────────────────────────
    // Activar o desactivar usuario
    // ─────────────────────────────────────────
    public String cambiarEstado(String cedula) {
        Usuario usuario = usuarioRepository.findByCedula(cedula)
                .orElseThrow(() -> new RuntimeException(
                        "Usuario no encontrado con cédula: " + cedula));

        usuario.setActivo(!usuario.isActivo());
        usuarioRepository.save(usuario);

        String estado = usuario.isActivo() ? "activado" : "desactivado";
        return "Usuario " + estado + " correctamente";
    }

    // ─────────────────────────────────────────
    // Credenciales para MS-Auth
    // ─────────────────────────────────────────
    public CredencialesResponse obtenerCredenciales(String username) {
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException(
                        "Usuario no encontrado: " + username));

        return new CredencialesResponse(
                usuario.getUsername(),
                usuario.getPassword(),
                usuario.getRol().getNombre(),
                usuario.isActivo()
        );
    }

    // ─────────────────────────────────────────
    // Método auxiliar: convierte Usuario a DTO
    // ─────────────────────────────────────────
    private UsuarioDTO convertirADTO(Usuario usuario) {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(usuario.getId());
        dto.setCedula(usuario.getCedula());
        dto.setNombreCompleto(usuario.getNombreCompleto());
        dto.setEmail(usuario.getEmail());
        dto.setUsername(usuario.getUsername());
        dto.setActivo(usuario.isActivo());
        dto.setRol(usuario.getRol().getNombre());
        return dto;
    }

    // ─────────────────────────────────────────
    // Crear roles y admin inicial al arrancar
    // ─────────────────────────────────────────
    public void inicializarDatos() {

        // Crea roles si no existen
        if (rolRepository.findByNombre("ADMIN").isEmpty()) {
            Rol admin = new Rol();
            admin.setNombre("ADMIN");
            admin.setDescripcion("Acceso total al sistema");
            rolRepository.save(admin);
            System.out.println(">>> Rol ADMIN creado");
        }

        if (rolRepository.findByNombre("EMPLEADO").isEmpty()) {
            Rol empleado = new Rol();
            empleado.setNombre("EMPLEADO");
            empleado.setDescripcion("Acceso limitado al sistema");
            rolRepository.save(empleado);
            System.out.println(">>> Rol EMPLEADO creado");
        }

        // Crea usuario admin inicial si no existe
        if (!usuarioRepository.existsByUsername("admin")) {
            Rol rolAdmin = rolRepository.findByNombre("ADMIN").get();

            CrearUsuarioRequest request = new CrearUsuarioRequest();
            request.setCedula("0000000000");
            request.setNombreCompleto("Administrador");
            request.setEmail("admin@tiendagenerica.com");
            request.setUsername("admin");
            request.setPassword("admin123456");
            request.setIdRol(rolAdmin.getId());

            crearUsuario(request);
            System.out.println(">>> Usuario admin creado");
        }
    }
}