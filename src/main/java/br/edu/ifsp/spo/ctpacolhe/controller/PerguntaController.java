package br.edu.ifsp.spo.ctpacolhe.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

import br.edu.ifsp.spo.ctpacolhe.dto.PerguntaDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Pergunta")
public interface PerguntaController extends Controller {
	
	@Operation(summary = "Retorna perguntas")
	public ResponseEntity<List<PerguntaDto>> buscaPerguntas();
}
