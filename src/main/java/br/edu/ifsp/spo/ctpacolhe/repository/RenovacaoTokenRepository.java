package br.edu.ifsp.spo.ctpacolhe.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifsp.spo.ctpacolhe.entity.RenovacaoToken;

public interface RenovacaoTokenRepository extends JpaRepository<RenovacaoToken, UUID> {
	void deleteAllByIdUsuario(UUID idUsuario);
	Optional<RenovacaoToken> findByIdUsuario(UUID accountId);
}
