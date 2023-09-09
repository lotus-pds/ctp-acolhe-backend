package br.edu.ifsp.spo.ctpacolhe.controller;

import org.springframework.http.ResponseEntity;

import br.edu.ifsp.spo.ctpacolhe.dto.AvatarUpdateDto;
import br.edu.ifsp.spo.ctpacolhe.dto.SenhaUpdateDto;
import br.edu.ifsp.spo.ctpacolhe.dto.UsuarioDto;
import br.edu.ifsp.spo.ctpacolhe.dto.UsuarioUpdateDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Usuário Autenticado")
public interface UsuarioAutenticadoController extends Controller {
	
	@Operation(summary = "Retorna usuário")
	public ResponseEntity<UsuarioDto> buscaUsuario();
	
	@Operation(summary = "Altera dados do usuário")
	public ResponseEntity<UsuarioDto> alteraDados(UsuarioUpdateDto usuarioDto);
	
	@Operation(summary = "Altera senha do usuário")
	public ResponseEntity<Void> alteraSenha(SenhaUpdateDto senhasDto);
	
	@Operation(summary = "Altera avatar do usuário")
	public ResponseEntity<Void> alteraAvatar(AvatarUpdateDto urlAvatarDto);
	
}
