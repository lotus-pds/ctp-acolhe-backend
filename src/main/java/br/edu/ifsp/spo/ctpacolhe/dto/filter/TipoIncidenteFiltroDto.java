package br.edu.ifsp.spo.ctpacolhe.dto.filter;

import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;

import br.edu.ifsp.spo.ctpacolhe.common.wrapper.FiltroWrapper;
import br.edu.ifsp.spo.ctpacolhe.entity.filter.TipoIncidenteFiltro;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ParameterObject
public class TipoIncidenteFiltroDto implements FiltroDto {
	
	@Schema(example = "true")
	private Boolean ativo;
	
	@Override
	public FiltroWrapper toWrapper(Pageable paginacao) {
		TipoIncidenteFiltro tipoIncidenteFiltro = TipoIncidenteFiltro.builder()
				.ativo(ativo)
				.build();
		
		return FiltroWrapper.builder().paginacao(paginacao).filtro(tipoIncidenteFiltro).build();
	}
}
