package br.edu.ifsp.spo.ctpacolhe.controller;

import java.util.UUID;

import org.springframework.http.ResponseEntity;

import br.edu.ifsp.spo.ctpacolhe.dto.UsuarioCreateDto;
import br.edu.ifsp.spo.ctpacolhe.dto.UsuarioDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Cadastro")
public interface CadastroController extends Controller {
	
	
	@Operation(summary = "Cadastra novo usuário")
	public ResponseEntity<UsuarioDto> cadastro(UsuarioCreateDto usuarioDto);
	
	@Operation(summary = "Verificação de conta através de token")
    public ResponseEntity<UsuarioDto> verificacao(UUID token);
	
	@Operation(summary = "Reenvia email de confirmação")
    public ResponseEntity<Void> reenviarEmail(String reenviarEmail);
	
}
