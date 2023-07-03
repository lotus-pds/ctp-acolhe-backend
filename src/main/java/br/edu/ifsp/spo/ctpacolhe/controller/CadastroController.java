package br.edu.ifsp.spo.ctpacolhe.controller;

import java.net.URI;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
import br.edu.ifsp.spo.ctpacolhe.service.UsuarioService;

@RestController
@RequestMapping("/conta")
public class CadastroController implements Controller {
	
	@Autowired
	private CadastroService cadastroService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private UsuarioMapper usuarioMapper;
	
	@PostMapping("/cadastro")
	@ResponseBody
	public ResponseEntity<UsuarioDto> cadastro(@RequestBody @Valid UsuarioCreateDto usuarioDto) {
		Usuario usuario = cadastroService.criar(usuarioDto);
		usuario = usuarioService.buscaUsuario(usuario.getIdUsuario());
		
		UsuarioDto dto = usuarioMapper.to(usuario);
		
		URI uri = uriCreated("/conta/cadastro/{idUsuario}", dto.getIdUsuario());
		return ResponseEntity.created(uri).body(dto);
	}
	
	@PatchMapping("cadastro/verificacao/{token}")
	@ResponseBody
    public ResponseEntity<UsuarioDto> verificacao(@PathVariable UUID token) {
        Usuario usuario = cadastroService.verificar(token);
        usuario = usuarioService.buscaUsuario(usuario.getIdUsuario());

        return ResponseEntity.ok(usuarioMapper.to(usuario));
    }
	
	@PostMapping("cadastro/verificacao/reenviar-email")
    public ResponseEntity<Void> reenviarEmail(@RequestBody String reenviarEmail) {
        cadastroService.reenviarEmail(reenviarEmail);
        
        return ResponseEntity.accepted().build();
    }
	
}
