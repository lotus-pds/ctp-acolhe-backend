package br.edu.ifsp.spo.ctpacolhe.repository;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifsp.spo.ctpacolhe.entity.VerificacaoToken;

public interface VerificacaoTokenRepository extends JpaRepository<VerificacaoToken, UUID> {
	Optional<VerificacaoToken> findByToken(UUID token);
	boolean existsByExpiraEmAfter(Instant now);
	Optional<VerificacaoToken> findByIdUsuario(UUID idUsuario);
}
