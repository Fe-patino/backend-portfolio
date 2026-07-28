package com.auth.ms_auth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.auth.ms_auth.model.Usuario;



public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario>findByEmail(String email);
    boolean exexistsByEmail(String Email);

}
