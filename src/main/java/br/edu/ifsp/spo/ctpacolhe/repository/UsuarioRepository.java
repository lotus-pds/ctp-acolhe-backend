package br.edu.ifsp.spo.ctpacolhe.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifsp.spo.ctpacolhe.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
	Optional<Usuario> findByEmail(String email);
}
