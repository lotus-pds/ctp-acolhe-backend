package br.edu.ifsp.spo.ctpacolhe.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifsp.spo.ctpacolhe.entity.VerificacaoToken;

public interface VerificacaoTokenRepository extends JpaRepository<VerificacaoToken, UUID> {

}
