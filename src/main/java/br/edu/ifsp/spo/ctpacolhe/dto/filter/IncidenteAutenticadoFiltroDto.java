package br.edu.ifsp.spo.ctpacolhe.dto.filter;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import br.edu.ifsp.spo.ctpacolhe.common.wrapper.FiltroWrapper;
import br.edu.ifsp.spo.ctpacolhe.entity.filter.IncidenteFiltro;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ParameterObject
public class IncidenteAutenticadoFiltroDto implements FiltroDto {
	
	@Schema(example = "Dificuldade com os estudos")
	private String assunto;
	
	@Schema(example = "8aa3785e-4f60-4c08-a18e-b5710d1e8c9b")
	private List<UUID> idTipoIncidente;
	
	@Schema(example = "e3114512-ccc9-4bac-abb3-ff7005e10ae2")
	private String idStatus;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd", iso = ISO.DATE)
	@Schema(example = "2023-09-09")
	private LocalDate dataIncidenteInicial;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd", iso = ISO.DATE)
	@Schema(example = "2023-09-10")
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