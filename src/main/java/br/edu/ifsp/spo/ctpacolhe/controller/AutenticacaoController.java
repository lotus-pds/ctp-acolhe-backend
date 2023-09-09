package br.edu.ifsp.spo.ctpacolhe.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import br.edu.ifsp.spo.ctpacolhe.dto.AcessoCreateDto;
import br.edu.ifsp.spo.ctpacolhe.dto.AcessoDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Autenticação")
public interface AutenticacaoController extends Controller{
	
	@Operation(summary = "Acesso do usuário autenticado à sessão")
	public ResponseEntity<AcessoDto> acesso(AcessoCreateDto acessoDto);
	
	@Operation(summary = "Renova token de autenticação")
    public ResponseEntity<AcessoDto> renovarToken(String renovacaoToken);
	
	@Operation(summary = "Remove usuário autenticado da sessão")
    public ResponseEntity<Void> sair(Authentication usuarioAutenticado);
	
}
