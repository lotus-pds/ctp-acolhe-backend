package br.edu.ifsp.spo.ctpacolhe.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.edu.ifsp.spo.ctpacolhe.entity.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, UUID> {
	public Optional<Usuario> findByEmail(@Param("email") String email);
}
