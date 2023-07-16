package br.edu.ifsp.spo.ctpacolhe.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifsp.spo.ctpacolhe.dto.IncidenteCreateDto;
import br.edu.ifsp.spo.ctpacolhe.dto.IncidenteDto;
import br.edu.ifsp.spo.ctpacolhe.dto.filter.IncidenteAutenticadoFiltroDto;
import br.edu.ifsp.spo.ctpacolhe.entity.Incidente;
import br.edu.ifsp.spo.ctpacolhe.mapper.IncidenteMapper;
import br.edu.ifsp.spo.ctpacolhe.service.IncidenteService;

@RestController
@RequestMapping("/usuario/autenticado/incidente")
public class IncidenteAutenticadoController implements Controller  {
	
	@Autowired
	private IncidenteService incidenteService;
	
	@Autowired
	private IncidenteMapper incidenteMapper;

	@GetMapping
	@ResponseBody
	public ResponseEntity<List<IncidenteDto>> buscaIncidentes(Pageable paginacao, IncidenteAutenticadoFiltroDto filtro) {
		Page<Incidente> incidentes = incidenteService.buscaIncidentes(filtro.toWrapper(paginacao));
		List<IncidenteDto> dtos = incidenteMapper.toCustom(incidentes.getContent());
		return respostaPaginada(incidentes).body(dtos);
	}
	
	@PostMapping
	@ResponseBody
	public ResponseEntity<IncidenteDto> criaIncidente(@RequestBody IncidenteCreateDto incidenteDto) {
		Incidente incidente = incidenteService.criaIncidente(incidenteDto);
		
		IncidenteDto dto = incidenteMapper.toCustom(incidente);
		
		URI uri = uriCreated("/incidente/{idIncidente}", dto.getIdIncidente());
		return ResponseEntity.created(uri).body(dto);
	}
}
