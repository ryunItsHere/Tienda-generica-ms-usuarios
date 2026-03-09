package com.tiendagenerica.ms_usuarios.repository;

import com.tiendagenerica.ms_usuarios.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByUsername(String username);
    Optional<Usuario> findByCedula(String cedula);
    boolean existsByUsername(String username);
    boolean existsByCedula(String cedula);
    boolean existsByEmail(String email);
}