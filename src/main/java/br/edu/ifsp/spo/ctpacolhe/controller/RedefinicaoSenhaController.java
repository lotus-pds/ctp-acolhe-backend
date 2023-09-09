package br.edu.ifsp.spo.ctpacolhe.controller;

import org.springframework.http.ResponseEntity;

import br.edu.ifsp.spo.ctpacolhe.dto.RedefinicaoSenhaDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


@Tag(name = "Redefinição de Senha")
public interface RedefinicaoSenhaController extends Controller {
	
	@Operation(summary = "Envia email para redefinição de senha")
	public ResponseEntity<Void> esqueciSenha(String email);

	@Operation(summary = "Reenvia email para redefinição de senha")
	public ResponseEntity<Void> reenviarEmail(String reenviarEmail);

	@Operation(summary = "Redefine a senha")
	public ResponseEntity<Void> redefinicao(RedefinicaoSenhaDto dto);
}
