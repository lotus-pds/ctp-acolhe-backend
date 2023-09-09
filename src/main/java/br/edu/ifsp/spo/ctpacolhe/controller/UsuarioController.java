package br.edu.ifsp.spo.ctpacolhe.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import br.edu.ifsp.spo.ctpacolhe.dto.PerfilDto;
import br.edu.ifsp.spo.ctpacolhe.dto.UsuarioDto;
import br.edu.ifsp.spo.ctpacolhe.dto.filter.UsuarioFiltroDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


@Tag(name = "Usuário")
public interface UsuarioController extends Controller {
	
	@Operation(summary = "Retorna usuários")
	public ResponseEntity<List<UsuarioDto>> buscaUsuarios(Pageable paginacao, UsuarioFiltroDto filtro);
	
	@Operation(summary = "Altera perfil do usuário por ID")
    public ResponseEntity<UsuarioDto> alteraPerfis(@PathVariable("idUsuario") UUID idUsuario, List<PerfilDto> perfisAtualizados);
	
}
