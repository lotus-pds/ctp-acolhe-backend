package br.edu.ifsp.spo.ctpacolhe.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.edu.ifsp.spo.ctpacolhe.entity.AgendamentoSala;
import br.edu.ifsp.spo.ctpacolhe.repository.custom.AgendamentoSalaRepositoryCustom;

public interface AgendamentoSalaRepository extends JpaRepository<AgendamentoSala, UUID>, AgendamentoSalaRepositoryCustom {
	@Query("SELECT a FROM AgendamentoSala a"
			+ " WHERE (a.dataAtendimentoInicial > ?1 AND a.dataAtendimentoInicial < ?2)"
			+ " OR (a.dataAtendimentoFinal > ?1 AND a.dataAtendimentoFinal < ?2)"
			+ " OR (a.dataAtendimentoInicial = ?1 AND a.dataAtendimentoFinal = ?2)")
	List<AgendamentoSala> findAllByPeriod(LocalDateTime dataInicial, LocalDateTime dataFinal);
	
	@Query("SELECT a FROM AgendamentoSala a"
			+ " LEFT JOIN FETCH a.criadoPor u"
			+ " LEFT JOIN FETCH u.curso"
			+ " LEFT JOIN FETCH u.perfis"
			+ " WHERE a.idAgendamento = ?1")
	Optional<AgendamentoSala> findById(UUID idAgendamento);
}
