package br.edu.ifsp.spo.ctpacolhe.repository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifsp.spo.ctpacolhe.entity.RedefinicaoSenhaToken;

public interface RedefinicaoTokenRepository extends JpaRepository<RedefinicaoSenhaToken, UUID> {
	boolean existsByIdUsuarioAndExpiraEmAfter(UUID idUsuario, LocalDateTime now);
	Optional<RedefinicaoSenhaToken> findByIdUsuario(UUID idUsuario);
	Optional<RedefinicaoSenhaToken> findByToken(UUID token);
}
