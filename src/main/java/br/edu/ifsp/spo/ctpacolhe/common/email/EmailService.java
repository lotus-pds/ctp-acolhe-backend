package br.edu.ifsp.spo.ctpacolhe.common.email;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import br.edu.ifsp.spo.ctpacolhe.common.constant.EmailContentType;
import br.edu.ifsp.spo.ctpacolhe.entity.RedefinicaoSenhaToken;
import br.edu.ifsp.spo.ctpacolhe.entity.Usuario;
import br.edu.ifsp.spo.ctpacolhe.entity.VerificacaoEmailToken;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailService {
	private final JavaMailSender mailSender;
	
	@Autowired
	private EmailContentService emailContentService;
	
	@Value("${support.mail}")
    private String supportMail;

    @Value("${support.name}")
    private String supportName;
    
    @Value("${frontend.url}/")
    private String url;
	
	public void enviaEmail(String assunto, String email, String conteudo) throws MessagingException {
        MimeMessage mail = mailSender.createMimeMessage();

        MimeMessageHelper message = new MimeMessageHelper(mail);
        message.setSubject(assunto);
        message.setText(conteudo, true);
        message.setTo(email);

        try {
            message.setFrom(new InternetAddress(supportMail, supportName));
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
            message.setFrom(supportMail);
        }

        mailSender.send(mail);
    }
	
	@Async
	public void enviaEmailDeVerificacao(Usuario usuario, VerificacaoEmailToken verificacaoToken) 
			 throws MessagingException {
		String urlVerificacao = url + "subscribe/verification/" + verificacaoToken.getToken().toString();
        String nome = usuario.getNome().split(" ")[0];
		
        String conteudo = emailContentService.getConteudoEmail(nome, urlVerificacao, EmailContentType.VERIFICACAO_EMAIL);
        
        enviaEmail("Confirmação de E-mail - CTP Acolhe", usuario.getEmail(), conteudo);
	}

	@Async
	public void enviaEmailDeRedefinicao(Usuario usuario, RedefinicaoSenhaToken redefinicaoSenhaToken)
			throws MessagingException {
		String urlRedefinicao = url + "reset-my-password/" + redefinicaoSenhaToken.getToken().toString();
        String nome = usuario.getNome().split(" ")[0];
        
        String conteudo = emailContentService.getConteudoEmail(nome, urlRedefinicao, EmailContentType.REDEFINICAO_SENHA);
        
        enviaEmail("Redefinição de senha - CTP Acolhe", usuario.getEmail(), conteudo);
	}
}
