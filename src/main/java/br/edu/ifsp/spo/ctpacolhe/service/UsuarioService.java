package br.edu.ifsp.spo.ctpacolhe.service;

import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.edu.ifsp.spo.ctpacolhe.common.constant.MensagemExceptionType;
import br.edu.ifsp.spo.ctpacolhe.common.exception.ValidationException;
import br.edu.ifsp.spo.ctpacolhe.common.wrapper.FiltroWrapper;
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
	
	public Usuario buscaUsuario(UUID idUsuario) {
		Usuario usuario = usuarioRepository.findById(idUsuario)
				.orElseThrow(() -> new ValidationException(MensagemExceptionType.USUARIO_NAO_ENCONTRADO));
		
		return usuario;
	}

	public Usuario alteraDados(UsuarioUpdateDto dto) {
		Usuario usuario = validaUsuarioAutenticado();
		
		if (usuarioRepository.existsByProntuarioAndIdUsuarioNot(dto.getProntuario(), usuario.getIdUsuario())) {
			throw new ValidationException(MensagemExceptionType.PRONTUARIO_JA_CADASTRADO);
		}
		
		usuario.setNome(dto.getNome());
		usuario.setTelefone(dto.getTelefone());
		usuario.setIdCurso(dto.getIdCurso());
		usuario.setPeriodo(dto.getPeriodo());
		usuario.setTurma(dto.getTurma());
		usuario.setProntuario(dto.getProntuario());
		
		usuario = usuarioRepository.save(usuario);
		
		return usuario;
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
	
	public Usuario reativar() {
		Usuario usuario = validaUsuarioAutenticado();
		
		if (usuario.getAtivo()) {
			throw new ValidationException(MensagemExceptionType.USUARIO_JA_ATIVO);
		}
		
		usuario.setAtivo(true);
		
		return usuarioRepository.save(usuario);
	}
	
	public Page<Usuario> buscaUsuarios(FiltroWrapper filtroWrapper) {
		Page<Usuario> usuarios = usuarioRepository.findAll(filtroWrapper);
		return usuarios;
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
