package com.murilo.segurancaAPI.repository;

import com.murilo.segurancaAPI.entidade.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
