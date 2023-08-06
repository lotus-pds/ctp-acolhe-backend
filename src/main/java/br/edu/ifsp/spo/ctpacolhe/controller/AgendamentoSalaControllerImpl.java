package br.edu.ifsp.spo.ctpacolhe.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifsp.spo.ctpacolhe.dto.AgendamentoSalaCreateDto;
import br.edu.ifsp.spo.ctpacolhe.dto.AgendamentoSalaDto;
import br.edu.ifsp.spo.ctpacolhe.entity.AgendamentoSala;
import br.edu.ifsp.spo.ctpacolhe.mapper.AgendamentoSalaMapper;
import br.edu.ifsp.spo.ctpacolhe.service.AgendamentoSalaService;

@RestController
@RequestMapping("/agendamentoSala")
public class AgendamentoSalaControllerImpl implements AgendamentoSalaController {
	
	@Autowired
	private AgendamentoSalaService agendamentoSalaService;
	
	@Autowired
	private AgendamentoSalaMapper agendamentoSalaMapper;
	
	@PostMapping
	@ResponseBody
	public ResponseEntity<AgendamentoSalaDto> criaAgendamento(@RequestBody AgendamentoSalaCreateDto agendamentoDto) {
		AgendamentoSala agendamentoSala = agendamentoSalaService.criaAgendamento(agendamentoDto);

		//TODO: adicionar GET unitário de agendamento
		AgendamentoSalaDto dto = agendamentoSalaMapper.to(agendamentoSala);
		
		URI uri = uriCreated("/agendamentoSala/{idAgendamento}", dto.getIdAgendamento());
		return ResponseEntity.created(uri).body(dto);
	}
	
}
