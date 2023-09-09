package br.edu.ifsp.spo.ctpacolhe.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import br.edu.ifsp.spo.ctpacolhe.dto.IncidenteDto;
import br.edu.ifsp.spo.ctpacolhe.dto.filter.IncidenteFiltroDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Incidente")
public interface IncidenteController extends Controller {
	
	@Operation(summary = "Retorna os incidentes com filtros")
	public ResponseEntity<List<IncidenteDto>> buscaIncidentes(Pageable paginacao, IncidenteFiltroDto filtro);

	@Operation(summary = "Retorna um incidente por ID")
	public ResponseEntity<IncidenteDto> buscaIncidente(@PathVariable("idIncidente") UUID idIncidente);
	
	@Operation(summary = "Altera um incidente para status EM_PROCESSO")
	public ResponseEntity<IncidenteDto> processarIncidente(@PathVariable("idIncidente") UUID idIncidente);
	
	@Operation(summary = "Altera um incidente para status FINALIZADO")
	public ResponseEntity<IncidenteDto> finalizarIncidente(@PathVariable("idIncidente") UUID idIncidente);
}
