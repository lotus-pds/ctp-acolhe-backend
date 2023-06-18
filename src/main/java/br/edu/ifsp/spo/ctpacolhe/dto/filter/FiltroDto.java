package br.edu.ifsp.spo.ctpacolhe.dto.filter;

import org.springframework.data.domain.Pageable;

import br.edu.ifsp.spo.ctpacolhe.common.wrapper.FiltroWrapper;

public interface FiltroDto {
	public FiltroWrapper toWrapper(Pageable paginacao);
}
