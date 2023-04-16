package br.edu.ifsp.spo.ctpacolhe.service;

import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.edu.ifsp.spo.ctpacolhe.common.constant.PerfilUsuario;
import br.edu.ifsp.spo.ctpacolhe.common.exception.ValidationException;
import br.edu.ifsp.spo.ctpacolhe.dto.UsuarioCreateDto;
import br.edu.ifsp.spo.ctpacolhe.entity.Perfil;
import br.edu.ifsp.spo.ctpacolhe.entity.Usuario;
import br.edu.ifsp.spo.ctpacolhe.repository.UsuarioRepository;

@Service
@Transactional
public class CadastroService {
	
	@Autowired
    private UsuarioRepository usuarioRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public Usuario criar(UsuarioCreateDto dto) {
		validaUsuario(dto);
		
		Usuario usuario = Usuario.builder()
				.idUsuario(UUID.randomUUID())
				.nome(dto.getNome())
				.email(dto.getEmail())
				.telefone(dto.getTelefone())
				.curso(dto.getCurso())
				.periodo(dto.getPeriodo())
				.turma(dto.getTurma())
				.prontuario(dto.getProntuario())
				.senha(passwordEncoder.encode(dto.getSenha()))
				.build();
		
		usuario.addPerfil(new Perfil(PerfilUsuario.ALU));
		
		return usuarioRepository.save(usuario);
	}

	private void validaUsuario(UsuarioCreateDto dto) {
		List<Usuario> usuariosBuscados = usuarioRepository.findByEmailOrProntuario(dto.getEmail(), dto.getProntuario());
		
		usuariosBuscados.stream().findFirst().ifPresent(usuario -> {
			if(usuario.getEmail().equals(dto.getEmail())) {
				throw new ValidationException("E-mail já cadastrado");
			} else {
				throw new ValidationException("Prontuário já cadastrado");
			}
		});
	}

}
