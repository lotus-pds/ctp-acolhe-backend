package br.edu.ifsp.spo.ctpacolhe.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import br.edu.ifsp.spo.ctpacolhe.common.exception.MensagemExceptionType;
import br.edu.ifsp.spo.ctpacolhe.common.exception.ValidationException;
import br.edu.ifsp.spo.ctpacolhe.entity.Usuario;
import br.edu.ifsp.spo.ctpacolhe.repository.UsuarioRepository;

@Service
@Transactional
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	public Usuario buscaUsuario() {
		Usuario usuarioAutenticado = buscaUsuarioAutenticado();
		
		Usuario usuario = usuarioRepository.findById(usuarioAutenticado.getIdUsuario())
				.orElseThrow(() -> new ValidationException(MensagemExceptionType.USUARIO_NAO_ENCONTRADO));
		
		return usuario;
	}
	
	public Usuario buscaUsuarioAutenticado() {
		return (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}

}
