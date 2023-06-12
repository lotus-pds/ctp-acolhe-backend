package br.edu.ifsp.spo.ctpacolhe.repository;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifsp.spo.ctpacolhe.entity.Humor;

public interface HumorRepository extends JpaRepository<Humor, UUID> {
	boolean existsByDataHumorAndIdUsuario(LocalDate dataHumor, UUID idUsuario);
}