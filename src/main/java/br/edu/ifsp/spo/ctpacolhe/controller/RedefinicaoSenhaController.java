package br.edu.ifsp.spo.ctpacolhe.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifsp.spo.ctpacolhe.service.RedefinicaoSenhaService;
import br.edu.ifsp.spo.ctpacolhe.dto.RedefinicaoSenhaDto;

@RestController
@RequestMapping("conta/senha")
public class RedefinicaoSenhaController implements Controller {
	@Autowired
	private RedefinicaoSenhaService redefinicaoSenhaService;

	@PostMapping("esqueci")
	public ResponseEntity<Void> esqueciSenha(@RequestBody String email) {
		redefinicaoSenhaService.enviarEmailEsqueciSenha(email);
		return ResponseEntity.accepted().build();
	}

	@PostMapping("esqueci/reenviar-email")
	public ResponseEntity<Void> reenviarEmail(@RequestBody String reenviarEmail) {
		redefinicaoSenhaService.reenviarEmail(reenviarEmail);
		return ResponseEntity.accepted().build();
	}

	@PatchMapping("redefinir")
	public ResponseEntity<Void> redefinicao(@Valid @RequestBody RedefinicaoSenhaDto dto) {
		redefinicaoSenhaService.redefinicaoSenha(dto);
		return ResponseEntity.noContent().build();
	}
}
