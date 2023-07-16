package br.edu.ifsp.spo.ctpacolhe.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.edu.ifsp.spo.ctpacolhe.entity.Pergunta;

public interface PerguntaRepository extends JpaRepository<Pergunta, UUID> {
	@Query("SELECT p FROM Pergunta p LEFT JOIN FETCH p.respostas")
	List<Pergunta> findAll();
}
