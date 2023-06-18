package br.edu.ifsp.spo.ctpacolhe.dto.filter;

import java.time.LocalDate;

import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import br.edu.ifsp.spo.ctpacolhe.common.wrapper.FiltroWrapper;
import br.edu.ifsp.spo.ctpacolhe.entity.filter.HumorFiltro;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HumorAutenticadoFiltroDto implements FiltroDto {	
	@DateTimeFormat(pattern = "yyyy-MM-dd", iso = ISO.DATE)
	private LocalDate dataInicial;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd", iso = ISO.DATE)
	private LocalDate dataFinal;
	
	public FiltroWrapper toWrapper(Pageable paginacao) {
		HumorFiltro humorFiltro = HumorFiltro.builder()
				.dataInicial(dataInicial)
				.dataFinal(dataFinal)
				.build();
		
		
		return FiltroWrapper.builder().paginacao(paginacao).filtro(humorFiltro).build();
	}
}
