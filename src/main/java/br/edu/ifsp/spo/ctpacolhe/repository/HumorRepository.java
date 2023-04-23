package br.edu.ifsp.spo.ctpacolhe.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifsp.spo.ctpacolhe.entity.Humor;
import br.edu.ifsp.spo.ctpacolhe.entity.Usuario;

public interface HumorRepository extends JpaRepository<Humor, UUID> {
	Optional<Humor> findById(UUID Id);
	List<Humor> findByUsuario(Usuario usuario);
}