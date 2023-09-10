package br.edu.ifsp.spo.ctpacolhe.dto.filter;

import java.time.LocalDate;

import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import br.edu.ifsp.spo.ctpacolhe.common.wrapper.FiltroWrapper;
import br.edu.ifsp.spo.ctpacolhe.entity.filter.AgendamentoSalaFiltro;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ParameterObject
public class AgendamentoSalaFiltroDto implements FiltroDto {
	@DateTimeFormat(pattern = "yyyy-MM-dd", iso = ISO.DATE)
	@Schema(example = "2023-09-09")
	private LocalDate dataAtendimentoInicial;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd", iso = ISO.DATE)
	@Schema(example = "2023-09-10")
	private LocalDate dataAtendimentoFinal;

	@Override
	public FiltroWrapper toWrapper(Pageable paginacao) {
		AgendamentoSalaFiltro agendamentoSalaFiltro = AgendamentoSalaFiltro.builder()
				.dataAtendimentoInicial(dataAtendimentoInicial)
				.dataAtendimentoFinal(dataAtendimentoFinal)
				.build();
		
		return FiltroWrapper.builder().paginacao(paginacao).filtro(agendamentoSalaFiltro).build();
	}

}
