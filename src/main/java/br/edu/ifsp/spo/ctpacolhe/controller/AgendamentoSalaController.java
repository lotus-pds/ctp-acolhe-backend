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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Agendamento de Sala")
public interface AgendamentoSalaController extends Controller {
	
	@Operation(summary = "Cria agendamento de sala")
	public ResponseEntity<AgendamentoSalaDto> criaAgendamento(@RequestBody AgendamentoSalaCreateDto agendamentoDto);
	
	@Operation(summary = "Retorna agendamentos de sala")
	public ResponseEntity<List<AgendamentoSalaDto>> buscaAgendamentos(Pageable paginacao, AgendamentoSalaFiltroDto filtro);

	@Operation(summary = "Altera um agendamento de sala")
	public ResponseEntity<AgendamentoSalaDto> atualizaAgendamento(@PathVariable("idAgendamento") UUID idAgendamento,
			AgendamentoSalaCreateDto agendamentoDto);
	
	@Operation(summary = "Remove um agendamento de sala")
	public ResponseEntity<Void> deletaAgendamento(@PathVariable("idAgendamento") UUID idAgendamento);
	
}
