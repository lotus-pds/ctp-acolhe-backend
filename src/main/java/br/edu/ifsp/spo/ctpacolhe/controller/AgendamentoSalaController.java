package br.edu.ifsp.spo.ctpacolhe.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.edu.ifsp.spo.ctpacolhe.dto.AgendamentoSalaCreateDto;
import br.edu.ifsp.spo.ctpacolhe.dto.AgendamentoSalaDto;
import br.edu.ifsp.spo.ctpacolhe.dto.filter.AgendamentoSalaFiltroDto;

public interface AgendamentoSalaController extends Controller {
	
	public ResponseEntity<AgendamentoSalaDto> criaAgendamento(@RequestBody AgendamentoSalaCreateDto agendamentoDto);
	
	public ResponseEntity<List<AgendamentoSalaDto>> buscaAgendamentos(Pageable paginacao, AgendamentoSalaFiltroDto filtro);

	public ResponseEntity<AgendamentoSalaDto> atualizaAgendamento(@PathVariable("idAgendamento") UUID idAgendamento,
			@RequestBody AgendamentoSalaCreateDto agendamentoDto);
	
	public ResponseEntity<Void> deletaAgendamento(@PathVariable("idAgendamento") UUID idAgendamento);
	
}
