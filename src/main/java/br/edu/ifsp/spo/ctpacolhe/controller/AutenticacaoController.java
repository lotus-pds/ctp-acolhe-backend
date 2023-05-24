package br.edu.ifsp.spo.ctpacolhe.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifsp.spo.ctpacolhe.dto.AcessoCreateDto;
import br.edu.ifsp.spo.ctpacolhe.dto.AcessoDto;
import br.edu.ifsp.spo.ctpacolhe.service.AutenticacaoService;

@RestController
@RequestMapping("/conta")
public class AutenticacaoController {
	
	@Autowired
	private AutenticacaoService autenticacaoService;
	
	@PostMapping(path = "/acesso")
	@ResponseBody
	public ResponseEntity<AcessoDto> acesso(@RequestBody @Valid AcessoCreateDto acessoDto) {
		AcessoDto dto = autenticacaoService.montaAutenticacao(acessoDto);
		
		return ResponseEntity.ok(dto);
	}
	
	@PostMapping("/renovar-token")
    public ResponseEntity<AcessoDto> renovarToken(@Valid @RequestBody @NotBlank String renovacaoToken) {
		AcessoDto dto = autenticacaoService.renovacaoToken(renovacaoToken);

		return ResponseEntity.ok(dto);
    }
	
	@DeleteMapping("/sair")
    public ResponseEntity<Void> sair(Authentication usuarioAutenticado) {
        autenticacaoService.sair(usuarioAutenticado);

        return ResponseEntity.noContent().build();
    }
}
