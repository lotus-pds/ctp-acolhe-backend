package br.edu.ifsp.spo.ctpacolhe.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifsp.spo.ctpacolhe.common.exception.ValidationException;
import br.edu.ifsp.spo.ctpacolhe.dto.AcessoCreateDto;
import br.edu.ifsp.spo.ctpacolhe.dto.AcessoDto;
import br.edu.ifsp.spo.ctpacolhe.service.AcessoService;

@RestController
@RequestMapping("api/v1/")
public class AcessoController {
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private AcessoService acessoService;
	
	@PostMapping(path = "/acesso")
	@ResponseBody
	public ResponseEntity<AcessoDto> login(@RequestBody @Valid AcessoCreateDto acessoDto) {
		try {
			String email = acessoDto.getEmail();
			String senha = acessoDto.getSenha();
			
			Authentication authentication = authManager
					.authenticate(new UsernamePasswordAuthenticationToken(email, senha));
			
			AcessoDto dto = acessoService.montaAutenticacao(authentication);
			
			return ResponseEntity.ok(dto);
			
		} catch(BadCredentialsException ex) {
			throw new ValidationException("Usuário e/ou senha incorreto(s)");
		}
	}

}
