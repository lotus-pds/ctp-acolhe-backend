package br.edu.ifsp.spo.ctpacolhe.controller;

import java.util.List;


import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;


import br.edu.ifsp.spo.ctpacolhe.dto.CursoDto;
import br.edu.ifsp.spo.ctpacolhe.dto.filter.CursoFiltroDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Curso")
public interface CursoController extends Controller {
	
	
	@Operation(summary = "Retorna os cursos")
	public ResponseEntity<List<CursoDto>> buscaCursos(Pageable paginacao, CursoFiltroDto filtro);
		
}
