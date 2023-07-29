package br.edu.ifsp.spo.ctpacolhe.dto.filter;

import org.springframework.data.domain.Pageable;

import br.edu.ifsp.spo.ctpacolhe.common.wrapper.FiltroWrapper;
import br.edu.ifsp.spo.ctpacolhe.entity.filter.TipoIncidenteFiltro;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TipoIncidenteFiltroDto implements FiltroDto {
	private Boolean ativo;
	
	@Override
	public FiltroWrapper toWrapper(Pageable paginacao) {
		TipoIncidenteFiltro tipoIncidenteFiltro = TipoIncidenteFiltro.builder()
				.ativo(ativo)
				.build();
		
		return FiltroWrapper.builder().paginacao(paginacao).filtro(tipoIncidenteFiltro).build();
	}
}
