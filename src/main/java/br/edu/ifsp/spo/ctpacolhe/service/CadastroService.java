package br.edu.ifsp.spo.ctpacolhe.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.edu.ifsp.spo.ctpacolhe.common.constant.MensagemExceptionType;
import br.edu.ifsp.spo.ctpacolhe.common.constant.PerfilUsuario;
import br.edu.ifsp.spo.ctpacolhe.common.email.EmailService;
import br.edu.ifsp.spo.ctpacolhe.common.exception.ValidationException;
import br.edu.ifsp.spo.ctpacolhe.dto.UsuarioCreateDto;
import br.edu.ifsp.spo.ctpacolhe.entity.Perfil;
import br.edu.ifsp.spo.ctpacolhe.entity.Usuario;
import br.edu.ifsp.spo.ctpacolhe.entity.VerificacaoEmailToken;
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
	
	@Value("${account.verification-token-expires-in}")
	private Integer VERIFICACAO_TOKEN_EXPIRA_EM;
	
	public Usuario criar(UsuarioCreateDto dto) {
		validaUsuario(dto);
		
		Usuario usuario = Usuario.builder()
				.idUsuario(UUID.randomUUID())
				.nome(dto.getNome())
				.email(dto.getEmail())
				.telefone(dto.getTelefone())
				.idCurso(dto.getIdCurso())
				.periodo(dto.getPeriodo())
				.turma(dto.getTurma())
				.prontuario(dto.getProntuario())
				.senha(passwordEncoder.encode(dto.getSenha()))
				.urlAvatar(dto.getUrlAvatar())
				.build();
		
		usuario.addPerfil(new Perfil(PerfilUsuario.ALU));
		
		try {
			usuario = usuarioRepository.save(usuario);
			log.info("Usuário com id {} foi criado", usuario.getIdUsuario());
			
			VerificacaoEmailToken verificacaoToken =
                    new VerificacaoEmailToken(usuario, VERIFICACAO_TOKEN_EXPIRA_EM);
			verificacaoTokenRepository.save(verificacaoToken);
            log.debug("Token de verificação {} para o e-mail {} foi criado", verificacaoToken.getToken(), usuario.getEmail());

            emailService.enviaEmailDeVerificacao(usuario, verificacaoToken);
            log.info("E-mail de verificação foi enviado para {}", usuario.getEmail());

            return usuario;
		} catch (MessagingException e) {
			log.error("Erro ao tentar enviar e-mail de confirmação para {}", usuario.getEmail(), e);
            throw new ValidationException(MensagemExceptionType.PROBLEMA_COM_ENVIO_EMAIL_VERIFICACAO);
		}
	}
	
	public Usuario verificar(UUID token) {
		VerificacaoEmailToken verificacaoToken = verificacaoTokenRepository.findByToken(token)
                .orElseThrow(() -> new ValidationException(MensagemExceptionType.TOKEN_VERIFICACAO_NAO_ENCONTRADO));
		
		if (verificacaoToken.getExpiraEm().isBefore(LocalDateTime.now())) {
			throw new ValidationException(MensagemExceptionType.TOKEN_VERIFICACAO_EXPIROU, verificacaoToken.getUsuario().getEmail());
		}

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
				throw new ValidationException(MensagemExceptionType.EMAIL_JA_CADASTRADO);
			} else {
				throw new ValidationException(MensagemExceptionType.PRONTUARIO_JA_CADASTRADO);
			}
		});
	}

	public void reenviarEmail(String reenviarEmail) {
		Usuario usuario = usuarioRepository.findByEmail(reenviarEmail)
                .orElseThrow(() -> new ValidationException(MensagemExceptionType.EMAIL_NAO_ENCONTRADO));
		
		VerificacaoEmailToken verificacaoToken = verificacaoTokenRepository.findByIdUsuario(usuario.getIdUsuario())
        		.orElseThrow(() -> new ValidationException(MensagemExceptionType.TOKEN_VERIFICACAO_NAO_ENCONTRADO));

        if (verificacaoToken.getGeradoEm().plusSeconds(60).isAfter(LocalDateTime.now())) {
        	throw new ValidationException(MensagemExceptionType.TOKEN_VERIFICACAO_AGUARDE_UM_MINUTO);
        }

        try {
        	verificacaoToken.setGeradoEm(LocalDateTime.now());
            verificacaoToken.setExpiraEm(LocalDateTime.now().plusSeconds(VERIFICACAO_TOKEN_EXPIRA_EM));
            verificacaoTokenRepository.save(verificacaoToken);
            
            emailService.enviaEmailDeVerificacao(usuario, verificacaoToken);
            log.info("E-mail de verificação foi reenviado para {}", usuario.getEmail());
        } catch (MessagingException e) {
			log.error("Erro ao tentar reenviar e-mail de confirmação para {}", usuario.getEmail(), e);
            throw new ValidationException(MensagemExceptionType.PROBLEMA_COM_ENVIO_EMAIL_VERIFICACAO);
		}
	}

	public void removeCadastrosNaoVerificados() {
		List<Usuario> cadastrosNaoVerificados = usuarioRepository.findAllByEmailConfirmado(false);
		List<UUID> idsUsuarios = new ArrayList<>();
		
		cadastrosNaoVerificados.forEach(cadastro -> {
			if (cadastro.getDataCadastro().plusMinutes(30).isBefore(LocalDateTime.now())) {
				idsUsuarios.add(cadastro.getIdUsuario());
			}
		});
		
		usuarioRepository.removePerfisByIds(idsUsuarios);
		usuarioRepository.removeHumoresByIds(idsUsuarios);
		
		usuarioRepository.deleteAllById(idsUsuarios);
		log.info("Cadastros com e-mail não verificado removidos pelo sistema");
	}

}
