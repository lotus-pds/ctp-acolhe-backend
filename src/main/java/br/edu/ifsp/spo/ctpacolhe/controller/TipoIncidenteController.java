package br.edu.ifsp.spo.ctpacolhe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifsp.spo.ctpacolhe.dto.TipoIncidenteDto;
import br.edu.ifsp.spo.ctpacolhe.dto.filter.TipoIncidenteFiltroDto;
import br.edu.ifsp.spo.ctpacolhe.entity.TipoIncidente;
import br.edu.ifsp.spo.ctpacolhe.mapper.TipoIncidenteMapper;
import br.edu.ifsp.spo.ctpacolhe.service.TipoIncidenteService;

@RestController
@RequestMapping("/tipoIncidente")
public class TipoIncidenteController implements Controller {
	
	@Autowired
	private TipoIncidenteService tipoIncidenteService;
	
	@Autowired
	private TipoIncidenteMapper tipoIncidenteMapper;
	
	@GetMapping
	@ResponseBody
	public ResponseEntity<List<TipoIncidenteDto>> buscaTipos(Pageable paginacao, TipoIncidenteFiltroDto filtro) {
		Page<TipoIncidente> tipos = tipoIncidenteService.buscaTipos(filtro.toWrapper(paginacao));
		List<TipoIncidenteDto> dtos = tipoIncidenteMapper.to(tipos.getContent());
		return respostaPaginada(tipos).body(dtos);
	}
}
