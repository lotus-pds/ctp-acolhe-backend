package br.edu.ifsp.spo.ctpacolhe.controller;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import br.edu.ifsp.spo.ctpacolhe.dto.TipoIncidenteDto;
import br.edu.ifsp.spo.ctpacolhe.dto.filter.TipoIncidenteFiltroDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


@Tag(name = "Tipo Incidente")
public interface TipoIncidenteController extends Controller {
	
	@Operation(summary = "Retorna os tipos de incidentes")
	public ResponseEntity<List<TipoIncidenteDto>> buscaTipos(Pageable paginacao, TipoIncidenteFiltroDto filtro);
}
