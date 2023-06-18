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

import br.edu.ifsp.spo.ctpacolhe.dto.CursoDto;
import br.edu.ifsp.spo.ctpacolhe.dto.filter.CursoFiltroDto;
import br.edu.ifsp.spo.ctpacolhe.entity.Curso;
import br.edu.ifsp.spo.ctpacolhe.mapper.CursoMapper;
import br.edu.ifsp.spo.ctpacolhe.service.CursoService;

@RestController
@RequestMapping("/curso")
public class CursoController implements Controller {
	
	@Autowired
	private CursoService cursoService;
	
	@Autowired
	private CursoMapper cursoMapper;
	
	@GetMapping
	@ResponseBody
	public ResponseEntity<List<CursoDto>> buscaHumores(Pageable paginacao, CursoFiltroDto filtro) {
		Page<Curso> cursos = cursoService.buscaCursos(filtro.toWrapper(paginacao));
		List<CursoDto> dtos = cursoMapper.to(cursos.getContent());
		return respostaPaginada(cursos).body(dtos);
	}
}
