package br.edu.ifsp.spo.ctpacolhe.controller;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifsp.spo.ctpacolhe.dto.HumorCreateDto;
import br.edu.ifsp.spo.ctpacolhe.dto.HumorDto;
import br.edu.ifsp.spo.ctpacolhe.entity.Humor;
import br.edu.ifsp.spo.ctpacolhe.mapper.HumorMapper;
import br.edu.ifsp.spo.ctpacolhe.service.HumorService;

@RestController
@RequestMapping("/usuario/autenticado/humor")
public class HumorAutenticadoController implements Controller {
	
	@Autowired
	private HumorService humorService;
	
	@Autowired
	private HumorMapper humorMapper;
	
	@ResponseBody
	public ResponseEntity<HumorDto> registroHumor(@RequestBody @Valid HumorCreateDto humorDto) {
		Humor humor = humorService.criaHumor(humorDto);
		
		HumorDto dto = humorMapper.to(humor);
		
		URI uri = uriCreated("/humor/{idHumor}", dto.getIdHumor());
		return ResponseEntity.created(uri).body(dto);
	}
	
	@ResponseBody
	public ResponseEntity<List<HumorDto>> buscaHumores(
			@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd", iso = ISO.DATE) LocalDate dataHumor) {
		List<Humor> humores = humorService.buscaHumores(dataHumor);

		List<HumorDto> dtos = humorMapper.to(humores);

		return ResponseEntity.ok(dtos);
	}
}
