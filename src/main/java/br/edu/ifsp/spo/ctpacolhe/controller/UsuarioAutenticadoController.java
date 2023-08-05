package br.edu.ifsp.spo.ctpacolhe.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifsp.spo.ctpacolhe.dto.AvatarUpdateDto;
import br.edu.ifsp.spo.ctpacolhe.dto.SenhaUpdateDto;
import br.edu.ifsp.spo.ctpacolhe.dto.UsuarioDto;
import br.edu.ifsp.spo.ctpacolhe.dto.UsuarioUpdateDto;
import br.edu.ifsp.spo.ctpacolhe.entity.Usuario;
import br.edu.ifsp.spo.ctpacolhe.mapper.UsuarioMapper;
import br.edu.ifsp.spo.ctpacolhe.service.UsuarioService;

@RestController
@RequestMapping("/usuario/autenticado")
public class UsuarioAutenticadoController implements Controller {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private UsuarioMapper usuarioMapper;
	
	@GetMapping
	@ResponseBody
	public ResponseEntity<UsuarioDto> buscaUsuario() {
		Usuario usuario = usuarioService.validaUsuarioAutenticado();

		UsuarioDto dto = usuarioMapper.to(usuario);

		return ResponseEntity.ok(dto);
	}
	
	@PutMapping
	@ResponseBody
	public ResponseEntity<UsuarioDto> alteraDados(@RequestBody @Valid UsuarioUpdateDto usuarioDto) {
		Usuario usuario = usuarioService.alteraDados(usuarioDto);
		usuario = usuarioService.buscaUsuario(usuario.getIdUsuario());
		
		UsuarioDto dto = usuarioMapper.to(usuario);

		return ResponseEntity.ok(dto);
	}
	
	@PatchMapping("/alterar-senha")
	public ResponseEntity<Void> alteraSenha(@RequestBody @Valid SenhaUpdateDto senhasDto) {
		usuarioService.alteraSenha(senhasDto);

		return ResponseEntity.noContent().build();
	}
	
	@PatchMapping("/alterar-avatar")
	public ResponseEntity<Void> alteraAvatar(@RequestBody @Valid AvatarUpdateDto urlAvatarDto) {
		usuarioService.alteraAvatar(urlAvatarDto);

		return ResponseEntity.noContent().build();
	}
	
}
