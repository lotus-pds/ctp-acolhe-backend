package br.edu.ifsp.spo.ctpacolhe.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifsp.spo.ctpacolhe.dto.IncidenteDto;
import br.edu.ifsp.spo.ctpacolhe.entity.Incidente;
import br.edu.ifsp.spo.ctpacolhe.mapper.IncidenteMapper;
import br.edu.ifsp.spo.ctpacolhe.service.IncidenteService;

@RestController
@RequestMapping("/incidente")
public class IncidenteController {
	
	@Autowired
	private IncidenteService incidenteService;
	
	@Autowired
	private IncidenteMapper incidenteMapper;
	
	@GetMapping("/{idIncidente}")
	@ResponseBody
	public ResponseEntity<IncidenteDto> buscaIncidente(@PathVariable("idIncidente") UUID idIncidente) {
		Incidente incidente = incidenteService.buscaIncidente(idIncidente);
		IncidenteDto dto = incidenteMapper.toCustom(incidente);
		return ResponseEntity.ok(dto);
	}
}
