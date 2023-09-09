package br.edu.ifsp.spo.ctpacolhe.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import br.edu.ifsp.spo.ctpacolhe.dto.IncidenteCreateDto;
import br.edu.ifsp.spo.ctpacolhe.dto.IncidenteDto;
import br.edu.ifsp.spo.ctpacolhe.dto.filter.IncidenteAutenticadoFiltroDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


@Tag(name = "Incidente Autenticado")
public interface IncidenteAutenticadoController extends Controller {
	
	@Operation(summary = "Retorna Incidentes")
	public ResponseEntity<List<IncidenteDto>> buscaIncidentes(Pageable paginacao, IncidenteAutenticadoFiltroDto filtro);
	
	@Operation(summary = "Cria incidente")
	public ResponseEntity<IncidenteDto> criaIncidente(IncidenteCreateDto incidenteDto);
	
	@Operation(summary = "Cancela incidente por ID")
	public ResponseEntity<IncidenteDto> cancelaIncidente(@PathVariable("idIncidente") UUID idIncidente);
}
