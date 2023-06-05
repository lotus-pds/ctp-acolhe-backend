package br.edu.ifsp.spo.ctpacolhe.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.edu.ifsp.spo.ctpacolhe.common.constant.MensagemExceptionType;
import br.edu.ifsp.spo.ctpacolhe.common.exception.ValidationException;
import br.edu.ifsp.spo.ctpacolhe.dto.SenhaUpdateDto;
import br.edu.ifsp.spo.ctpacolhe.dto.UsuarioUpdateDto;
import br.edu.ifsp.spo.ctpacolhe.entity.Usuario;
import br.edu.ifsp.spo.ctpacolhe.repository.UsuarioRepository;

@Service
@Transactional
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	public Usuario buscaUsuario() {
		Usuario usuario = validaUsuarioAutenticado();
		
		return usuario;
	}

	public Usuario alteraDados(UsuarioUpdateDto usuarioDto) {
		Usuario usuario = validaUsuarioAutenticado();
		
		usuario.setNome(usuarioDto.getNome());
		usuario.setTelefone(usuarioDto.getTelefone());
		usuario.setCurso(usuarioDto.getCurso());
		usuario.setPeriodo(usuarioDto.getPeriodo());
		usuario.setTurma(usuarioDto.getTurma());
		usuario.setProntuario(usuarioDto.getProntuario());
		
		return usuarioRepository.save(usuario);
	}
	
	public void alteraSenha(SenhaUpdateDto senhasDto) {
		Usuario usuario = validaUsuarioAutenticado();
		
		if (!passwordEncoder.matches(senhasDto.getSenhaAtual(), usuario.getSenha())) {
			throw new ValidationException(MensagemExceptionType.SENHA_ATUAL_INCORRETA);
		}
		
		if (senhasDto.getSenhaAtual().equals(senhasDto.getSenhaNova())) {
			throw new ValidationException(MensagemExceptionType.SENHA_ATUAL_E_NOVA_IGUAIS);
		}
		
		usuario.setSenha(passwordEncoder.encode(senhasDto.getSenhaNova()));
		
		usuarioRepository.save(usuario);
	}
	
	public Usuario inativar() {
		Usuario usuario = validaUsuarioAutenticado();
		
		if (!usuario.getAtivo()) {
			throw new ValidationException(MensagemExceptionType.USUARIO_JA_INATIVO);
		}
		
		usuario.setAtivo(false);
		
		return usuarioRepository.save(usuario);
	}

	private Usuario validaUsuarioAutenticado() {
		Usuario usuarioAutenticado = buscaUsuarioAutenticado();
		
		Usuario usuario = usuarioRepository.findById(usuarioAutenticado.getIdUsuario())
				.orElseThrow(() -> new ValidationException(MensagemExceptionType.USUARIO_NAO_ENCONTRADO));
		
		return usuario;
	}

	public Usuario buscaUsuarioAutenticado() {
		return (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
}
