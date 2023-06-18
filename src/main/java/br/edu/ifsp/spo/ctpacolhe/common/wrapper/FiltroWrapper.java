package br.edu.ifsp.spo.ctpacolhe.common.wrapper;

import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import br.edu.ifsp.spo.ctpacolhe.entity.filter.Filtro;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class FiltroWrapper {
	Filtro filtro;
	Pageable paginacao;
	
	public Pageable getPaginacao(Long totalRegistros) {
		return Optional.ofNullable(paginacao).orElseGet(() -> PageRequest.of(0, totalRegistros.intValue())); 
	}
	
	public Boolean hasPaginacao() {
		return this.paginacao != null && this.paginacao.getPageNumber() >= 0 && this.paginacao.getPageSize() > 0;
	}
}
