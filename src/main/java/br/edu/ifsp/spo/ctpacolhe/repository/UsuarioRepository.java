package br.edu.ifsp.spo.ctpacolhe.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.edu.ifsp.spo.ctpacolhe.entity.Usuario;
import br.edu.ifsp.spo.ctpacolhe.repository.custom.UsuarioRepositoryCustom;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID>, UsuarioRepositoryCustom {
	@Query("SELECT u FROM Usuario u LEFT JOIN FETCH u.curso LEFT JOIN FETCH u.perfis WHERE u.idUsuario = ?1")
	Optional<Usuario> findById(UUID idUsuario);
	Optional<Usuario> findByEmail(String email);
	List<Usuario> findByEmailOrProntuario(String email, String prontuario);
	boolean existsByProntuarioAndIdUsuarioNot(String prontuario, UUID idUsuario);
	List<Usuario> findAllByEmailConfirmado(Boolean ativo);
}
