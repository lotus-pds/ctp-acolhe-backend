package br.edu.ifsp.spo.ctpacolhe.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifsp.spo.ctpacolhe.dto.PerfilDto;
import br.edu.ifsp.spo.ctpacolhe.dto.UsuarioDto;
import br.edu.ifsp.spo.ctpacolhe.dto.filter.UsuarioFiltroDto;
import br.edu.ifsp.spo.ctpacolhe.entity.Usuario;
import br.edu.ifsp.spo.ctpacolhe.mapper.UsuarioMapper;
import br.edu.ifsp.spo.ctpacolhe.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioControllerImpl implements UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private UsuarioMapper usuarioMapper;
	
	@GetMapping
	@ResponseBody
	public ResponseEntity<List<UsuarioDto>> buscaUsuarios(Pageable paginacao, UsuarioFiltroDto filtro) {
		Page<Usuario> usuarios = usuarioService.buscaUsuarios(filtro.toWrapper(paginacao));
		List<UsuarioDto> dtos = usuarioMapper.to(usuarios.getContent());
		return respostaPaginada(usuarios).body(dtos);
	}
	
	@PatchMapping("/{idUsuario}/perfil")
	@ResponseBody
    public ResponseEntity<UsuarioDto> alteraPerfis(@PathVariable("idUsuario") UUID idUsuario, @RequestBody List<PerfilDto> perfisAtualizados) {
        usuarioService.alteraPerfis(idUsuario, perfisAtualizados);
        
        Usuario usuario = usuarioService.buscaUsuario(idUsuario);
        UsuarioDto dto = usuarioMapper.to(usuario);
        
        return ResponseEntity.ok(dto);
    }
}
