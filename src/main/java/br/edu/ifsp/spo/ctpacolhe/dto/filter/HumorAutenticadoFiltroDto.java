package br.edu.ifsp.spo.ctpacolhe.dto.filter;

import java.time.LocalDate;

import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import br.edu.ifsp.spo.ctpacolhe.common.wrapper.FiltroWrapper;
import br.edu.ifsp.spo.ctpacolhe.entity.filter.HumorFiltro;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ParameterObject
public class HumorAutenticadoFiltroDto implements FiltroDto {
	
	@DateTimeFormat(pattern = "yyyy-MM-dd", iso = ISO.DATE)
	@Schema(example = "2023-09-08")
	private LocalDate dataInicial;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd", iso = ISO.DATE)
	@Schema(example = "2023-09-09")
	private LocalDate dataFinal;
	
	public FiltroWrapper toWrapper(Pageable paginacao) {
		HumorFiltro humorFiltro = HumorFiltro.builder()
				.dataInicial(dataInicial)
				.dataFinal(dataFinal)
				.build();
		
		return FiltroWrapper.builder().paginacao(paginacao).filtro(humorFiltro).build();
	}
}
