package br.edu.ifsp.spo.ctpacolhe.repository;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifsp.spo.ctpacolhe.entity.Humor;
import br.edu.ifsp.spo.ctpacolhe.repository.custom.HumorRepositoryCustom;

public interface HumorRepository extends JpaRepository<Humor, UUID>, HumorRepositoryCustom {
	boolean existsByDataHumorAndIdUsuario(LocalDate dataHumor, UUID idUsuario);
}