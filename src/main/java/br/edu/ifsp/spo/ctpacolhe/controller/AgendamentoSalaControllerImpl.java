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

import br.edu.ifsp.spo.ctpacolhe.dto.AgendamentoSalaCreateDto;
import br.edu.ifsp.spo.ctpacolhe.dto.AgendamentoSalaDto;
import br.edu.ifsp.spo.ctpacolhe.dto.filter.AgendamentoSalaFiltroDto;
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

		agendamentoSala = agendamentoSalaService.buscaAgendamento(agendamentoSala.getIdAgendamento());
		AgendamentoSalaDto dto = agendamentoSalaMapper.to(agendamentoSala);
		
		URI uri = uriCreated("/agendamentoSala/{idAgendamento}", dto.getIdAgendamento());
		return ResponseEntity.created(uri).body(dto);
	}
	
	@GetMapping
	@ResponseBody
	public ResponseEntity<List<AgendamentoSalaDto>> buscaAgendamentos(Pageable paginacao, AgendamentoSalaFiltroDto filtro) {
		Page<AgendamentoSala> agendamentos = agendamentoSalaService.buscaAgendamentos(filtro.toWrapper(paginacao));
		List<AgendamentoSalaDto> dtos = agendamentoSalaMapper.to(agendamentos.getContent());
		return respostaPaginada(agendamentos).body(dtos);
	}
	
}
