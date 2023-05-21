package br.edu.ifsp.spo.ctpacolhe.service;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import br.edu.ifsp.spo.ctpacolhe.common.email.EmailService;
import br.edu.ifsp.spo.ctpacolhe.common.exception.CamposDinamicosType;
import br.edu.ifsp.spo.ctpacolhe.common.exception.MensagemExceptionType;
import br.edu.ifsp.spo.ctpacolhe.common.exception.ValidationException;
import br.edu.ifsp.spo.ctpacolhe.dto.RedefinicaoSenhaDTO;
import br.edu.ifsp.spo.ctpacolhe.entity.RedefinicaoSenhaToken;
import br.edu.ifsp.spo.ctpacolhe.entity.Usuario;
import br.edu.ifsp.spo.ctpacolhe.repository.RedefinicaoTokenRepository;
import br.edu.ifsp.spo.ctpacolhe.repository.UsuarioRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RedefinicaoSenhaService {
	
	@Autowired
    private UsuarioRepository usuarioRepository;
	
	@Autowired
	private RedefinicaoTokenRepository redefinicaoTokenRepository;
	
	@Autowired
	private EmailService emailService;
	
	@Value("${account.password-reset-token-expires-in}")
	private Integer REDEFINICAO_TOKEN_EXPIRA_EM;
	
	public void enviarEmailEsqueciSenha(String email) {
		Usuario usuario = buscaUsuario(email);
		
		if (!usuario.getEmailConfirmado()) {
			throw new ValidationException(MensagemExceptionType.EMAIL_NAO_CONFIRMADO);
		}
		
		if (redefinicaoTokenRepository.existsByIdUsuarioAndExpiraEmAfter(usuario.getIdUsuario(), LocalDateTime.now())) {
			throw new ValidationException(MensagemExceptionType.EMAIL_REDEFINICAO_SENHA_JA_ENVIADO);
		}
		
		try {
			RedefinicaoSenhaToken redefinicaoSenhaToken = new RedefinicaoSenhaToken(usuario, REDEFINICAO_TOKEN_EXPIRA_EM);
            redefinicaoTokenRepository.save(redefinicaoSenhaToken);
            log.debug("Token de redefinição de senha {} para o e-mail {} foi criado", redefinicaoSenhaToken.getToken(), usuario.getEmail());
            
            emailService.enviaEmailDeRedefinicao(usuario, redefinicaoSenhaToken);
            log.info("E-mail de redefinição de senha foi enviado para {}", usuario.getEmail());
        } catch (MessagingException e) {
        	log.error("Erro ao tentar enviar e-mail de confirmação para {}", usuario.getEmail(), e);
        }
	}

	public void redefinicaoSenha(@Valid RedefinicaoSenhaDTO dto) {
		// TODO Auto-generated method stub
		
	}
	
	public void reenviarEmail(String reenviarEmail) {
		Usuario usuario = buscaUsuario(reenviarEmail);
		
		if (!usuario.getEmailConfirmado()) {
			throw new ValidationException(MensagemExceptionType.EMAIL_NAO_CONFIRMADO);
		}
		
		RedefinicaoSenhaToken redefinicaoToken = validaRedefinicaoToken(usuario.getIdUsuario());
		
		try {
        	redefinicaoToken.setGeradoEm(LocalDateTime.now());
            redefinicaoToken.setExpiraEm(LocalDateTime.now().plusSeconds(REDEFINICAO_TOKEN_EXPIRA_EM));
            redefinicaoTokenRepository.save(redefinicaoToken);
            
            emailService.enviaEmailDeRedefinicao(usuario, redefinicaoToken);
            log.info("E-mail de redefinição de senha foi reenviado para {}", usuario.getEmail());
        } catch (MessagingException e) {
			log.error("Erro ao tentar reenviar e-mail de redefinição de senha para {}", usuario.getEmail(), e);
		}
	}

	private RedefinicaoSenhaToken validaRedefinicaoToken(UUID idUsuario) {
		RedefinicaoSenhaToken redefinicaoToken = redefinicaoTokenRepository.findByIdUsuario(idUsuario)
				.orElseThrow(() -> new ValidationException(MensagemExceptionType.TOKEN_NAO_ENCONTRADO, CamposDinamicosType.REDEFINICAO_SENHA));

		if (redefinicaoToken.getGeradoEm().plusSeconds(60).isAfter(LocalDateTime.now())) {
			throw new ValidationException(MensagemExceptionType.TOKEN_AGUARDE_UM_MINUTO,
					CamposDinamicosType.REDEFINICAO_SENHA);
		}
		
		return redefinicaoToken;
	}

	private Usuario buscaUsuario(String email) {
		return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new ValidationException(MensagemExceptionType.EMAIL_NAO_ENCONTRADO));
	}
}
