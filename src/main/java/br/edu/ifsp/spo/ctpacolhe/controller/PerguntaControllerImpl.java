package br.edu.ifsp.spo.ctpacolhe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifsp.spo.ctpacolhe.dto.PerguntaDto;
import br.edu.ifsp.spo.ctpacolhe.entity.Pergunta;
import br.edu.ifsp.spo.ctpacolhe.mapper.PerguntaMapper;
import br.edu.ifsp.spo.ctpacolhe.service.PerguntaService;

@RestController
@RequestMapping("/pergunta")
public class PerguntaControllerImpl implements PerguntaController {
	
	@Autowired
	private PerguntaService perguntaService;
	
	@Autowired
	private PerguntaMapper perguntaMapper;
	
	@GetMapping
	@ResponseBody
	public ResponseEntity<List<PerguntaDto>> buscaPerguntas() {
		List<Pergunta> perguntas = perguntaService.buscaPerguntas();
		List<PerguntaDto> dtos = perguntaMapper.toCustom(perguntas);
		return ResponseEntity.ok(dtos);
	}
}
