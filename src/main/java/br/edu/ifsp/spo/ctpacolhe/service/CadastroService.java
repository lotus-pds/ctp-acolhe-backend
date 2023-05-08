package br.edu.ifsp.spo.ctpacolhe.service;

import java.util.List;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.edu.ifsp.spo.ctpacolhe.common.constant.PerfilUsuario;
import br.edu.ifsp.spo.ctpacolhe.common.email.EmailService;
import br.edu.ifsp.spo.ctpacolhe.common.exception.ValidationException;
import br.edu.ifsp.spo.ctpacolhe.dto.UsuarioCreateDto;
import br.edu.ifsp.spo.ctpacolhe.entity.Perfil;
import br.edu.ifsp.spo.ctpacolhe.entity.Usuario;
import br.edu.ifsp.spo.ctpacolhe.entity.VerificacaoToken;
import br.edu.ifsp.spo.ctpacolhe.repository.UsuarioRepository;
import br.edu.ifsp.spo.ctpacolhe.repository.VerificacaoTokenRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class CadastroService {
	
	@Autowired
    private UsuarioRepository usuarioRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private VerificacaoTokenRepository verificacaoTokenRepository;
	
	@Autowired
	private EmailService emailService;
	
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
		
		try {
			usuario = usuarioRepository.save(usuario);
			log.info("Usuário com id {} foi criado", usuario.getIdUsuario());
			
			VerificacaoToken verificacaoToken =
                    new VerificacaoToken(usuario, 1800);
			verificacaoTokenRepository.save(verificacaoToken);
            log.debug("Token de verificação {} para o e-mail {} foi criado", verificacaoToken.getToken(), usuario.getEmail());

            emailService.enviaEmailDeVerificacao(usuario, verificacaoToken);
            log.info("E-mail de verificação foi enviado para {}", usuario.getEmail());

            return usuario;
		} catch (MessagingException e) {
			log.error("Erro ao tentar enviar e-mail de confirmação para {}", usuario.getEmail(), e);
            throw new ValidationException("Problema com o envio do e-mail de verificação, tente novamente mais tarde");
		}
	}
	
	public Usuario verificar(UUID token) {
		VerificacaoToken verificacaoToken = verificacaoTokenRepository.findByToken(token)
                .orElseThrow(() -> new ValidationException("Token de verificação não encontrado"));

		//TODO: Criar recurso de reenviar confirmação/verificação de e-mail
//		if (verificacaoToken.getExpiraEm().isBefore(Instant.now())) {
//			throw new ValidationException(
//					"Token de verificação com e-mail " + verificacaoToken.getUsuario().getEmail() + " expirou");
//		}

        Usuario usuario = verificacaoToken.getUsuario();
        usuario.setEmailConfirmado(true);
        
        usuarioRepository.save(usuario);
        log.info("Conta com e-mail {} foi verificada", usuario.getEmail());
        
        verificacaoTokenRepository.delete(verificacaoToken);
        log.info("Token de verificação com id {} foi deletado", verificacaoToken.getIdVerificacaoToken());
        
        return usuario;
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
