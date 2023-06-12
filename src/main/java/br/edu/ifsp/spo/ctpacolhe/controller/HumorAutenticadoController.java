package br.edu.ifsp.spo.ctpacolhe.controller;

import java.net.URI;

import javax.validation.Valid;

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

import br.edu.ifsp.spo.ctpacolhe.dto.HumorCreateDto;
import br.edu.ifsp.spo.ctpacolhe.dto.HumorDto;
import br.edu.ifsp.spo.ctpacolhe.entity.Humor;
import br.edu.ifsp.spo.ctpacolhe.filter.HumorFiltro;
import br.edu.ifsp.spo.ctpacolhe.mapper.HumorMapper;
import br.edu.ifsp.spo.ctpacolhe.service.HumorService;

@RestController
@RequestMapping("/usuario/autenticado/humor")
public class HumorAutenticadoController implements Controller {
	
	@Autowired
	private HumorService humorService;
	
	@Autowired
	private HumorMapper humorMapper;
	
	@PostMapping
	@ResponseBody
	public ResponseEntity<HumorDto> registroHumor(@RequestBody @Valid HumorCreateDto humorDto) {
		Humor humor = humorService.criaHumor(humorDto);
		
		HumorDto dto = humorMapper.to(humor);
		
		URI uri = uriCreated("/humor/{idHumor}", dto.getIdHumor());
		return ResponseEntity.created(uri).body(dto);
	}
	
	@GetMapping
	@ResponseBody
	public ResponseEntity<Page<HumorDto>> buscaHumores(HumorFiltro filtro, Pageable paginacao) {
		Page<Humor> humores = humorService.buscaHumores(filtro, paginacao);

		Page<HumorDto> dtos = humorMapper.to(humores);

		return respostaPaginada(dtos);
	}
}
