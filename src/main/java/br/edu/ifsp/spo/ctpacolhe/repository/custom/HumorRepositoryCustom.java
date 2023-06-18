package br.edu.ifsp.spo.ctpacolhe.repository.custom;

import org.springframework.data.domain.Page;

import br.edu.ifsp.spo.ctpacolhe.common.wrapper.FiltroWrapper;
import br.edu.ifsp.spo.ctpacolhe.entity.Humor;

public interface HumorRepositoryCustom {
	Page<Humor> findAll(FiltroWrapper filtroWrapper);
}
