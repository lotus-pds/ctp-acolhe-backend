package br.edu.ifsp.spo.ctpacolhe.service;

import java.time.LocalDateTime;

import javax.mail.MessagingException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import br.edu.ifsp.spo.ctpacolhe.common.email.EmailService;
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
		Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new ValidationException(MensagemExceptionType.CAD_EMAIL_NAO_ENCONTRADO));
		
		if (!usuario.getEmailConfirmado()) {
			throw new ValidationException(MensagemExceptionType.AUT_EMAIL_NAO_CONFIRMADO);
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
		// TODO Auto-generated method stub
		
	}
}
