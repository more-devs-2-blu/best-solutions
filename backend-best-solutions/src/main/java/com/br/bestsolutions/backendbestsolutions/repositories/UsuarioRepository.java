package com.br.bestsolutions.backendbestsolutions.repositories;

import com.br.bestsolutions.backendbestsolutions.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {

    Optional<Usuario> findByEmail(String email);
}