package br.edu.ifsp.spo.ctpacolhe.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifsp.spo.ctpacolhe.dto.HumorCreateDto;
import br.edu.ifsp.spo.ctpacolhe.dto.HumorDto;
import br.edu.ifsp.spo.ctpacolhe.entity.Humor;
import br.edu.ifsp.spo.ctpacolhe.mapper.HumorMapper;
import br.edu.ifsp.spo.ctpacolhe.service.HumorService;

@RestController
@RequestMapping("/humor")
public class HumorController implements Controller {
	
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
}
