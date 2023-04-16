package br.edu.ifsp.spo.ctpacolhe.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifsp.spo.ctpacolhe.dto.UsuarioCreateDto;
import br.edu.ifsp.spo.ctpacolhe.dto.UsuarioDto;
import br.edu.ifsp.spo.ctpacolhe.entity.Usuario;
import br.edu.ifsp.spo.ctpacolhe.mapper.UsuarioMapper;
import br.edu.ifsp.spo.ctpacolhe.service.CadastroService;

@RestController
@RequestMapping("/conta")
public class CadastroController implements Controller {
	
	@Autowired
	private CadastroService cadastroService;
	
	@Autowired
	private UsuarioMapper usuarioMapper;
	
	@PostMapping(path = "/cadastro")
	@ResponseBody
	public ResponseEntity<UsuarioDto> cadastro(@RequestBody @Valid UsuarioCreateDto usuarioDto) {
		Usuario usuario = cadastroService.criar(usuarioDto);
		
		UsuarioDto dto = usuarioMapper.to(usuario);
		
		URI uri = uriCreated("/conta/cadastro/{idUsuario}", dto.getIdUsuario());
		return ResponseEntity.created(uri).body(dto);
	}
	
}
