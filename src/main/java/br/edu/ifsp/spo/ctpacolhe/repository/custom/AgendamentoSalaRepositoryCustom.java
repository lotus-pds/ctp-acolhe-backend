package br.edu.ifsp.spo.ctpacolhe.repository.custom;

import org.springframework.data.domain.Page;

import br.edu.ifsp.spo.ctpacolhe.common.wrapper.FiltroWrapper;
import br.edu.ifsp.spo.ctpacolhe.entity.AgendamentoSala;

public interface AgendamentoSalaRepositoryCustom {
	Page<AgendamentoSala> findAll(FiltroWrapper filtroWrapper);
}
