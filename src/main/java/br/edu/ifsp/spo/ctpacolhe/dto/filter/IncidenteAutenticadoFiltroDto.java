package br.edu.ifsp.spo.ctpacolhe.dto.filter;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import br.edu.ifsp.spo.ctpacolhe.common.wrapper.FiltroWrapper;
import br.edu.ifsp.spo.ctpacolhe.entity.filter.IncidenteFiltro;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IncidenteAutenticadoFiltroDto implements FiltroDto {
	private String assunto;
	private List<UUID> idTipoIncidente;
	private String idStatus;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd", iso = ISO.DATE)
	private LocalDate dataIncidenteInicial;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd", iso = ISO.DATE)
	private LocalDate dataIncidenteFinal;

	@Override
	public FiltroWrapper toWrapper(Pageable paginacao) {
		IncidenteFiltro incidenteFiltro = IncidenteFiltro.builder()
				.assunto(assunto)
				.idTipoIncidente(idTipoIncidente)
				.idStatus(idStatus)
				.dataIncidenteInicial(dataIncidenteInicial)
				.dataIncidenteFinal(dataIncidenteFinal)
				.build();
		
		return FiltroWrapper.builder().paginacao(paginacao).filtro(incidenteFiltro).build();
	}
	
}