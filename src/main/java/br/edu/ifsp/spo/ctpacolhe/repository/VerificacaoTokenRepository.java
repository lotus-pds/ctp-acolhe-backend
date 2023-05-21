package br.edu.ifsp.spo.ctpacolhe.repository;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifsp.spo.ctpacolhe.entity.VerificacaoEmailToken;

public interface VerificacaoTokenRepository extends JpaRepository<VerificacaoEmailToken, UUID> {
	Optional<VerificacaoEmailToken> findByToken(UUID token);
	boolean existsByExpiraEmAfter(Instant now);
	Optional<VerificacaoEmailToken> findByIdUsuario(UUID idUsuario);
}
