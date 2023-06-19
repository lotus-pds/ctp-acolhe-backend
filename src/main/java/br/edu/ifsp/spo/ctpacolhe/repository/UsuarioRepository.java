package br.edu.ifsp.spo.ctpacolhe.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.edu.ifsp.spo.ctpacolhe.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
	@Query("SELECT u FROM Usuario u LEFT JOIN FETCH u.curso WHERE u.idUsuario = ?1")
	Optional<Usuario> findById(UUID idUsuario);
	Optional<Usuario> findByEmail(String email);
	List<Usuario> findByEmailOrProntuario(String email, String prontuario);
}
