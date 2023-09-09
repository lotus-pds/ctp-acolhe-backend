package br.edu.ifsp.spo.ctpacolhe.controller;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import br.edu.ifsp.spo.ctpacolhe.dto.HumorCreateDto;
import br.edu.ifsp.spo.ctpacolhe.dto.HumorDto;
import br.edu.ifsp.spo.ctpacolhe.dto.filter.HumorAutenticadoFiltroDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


@Tag(name = "Humor Autenticado")
public interface HumorAutenticadoController extends Controller {

	
	@Operation(summary = "Cria humor")
	public ResponseEntity<HumorDto> registroHumor(HumorCreateDto humorDto);
	
	@Operation(summary = "Retorna humores")
	public ResponseEntity<List<HumorDto>> buscaHumores(Pageable paginacao, HumorAutenticadoFiltroDto filtro);
}
