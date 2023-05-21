package br.edu.ifsp.spo.ctpacolhe.common.email;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import br.edu.ifsp.spo.ctpacolhe.entity.Usuario;
import br.edu.ifsp.spo.ctpacolhe.entity.VerificacaoEmailToken;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailService {
	private final JavaMailSender mailSender;
	
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

	public void enviaEmailDeVerificacao(Usuario usuario, VerificacaoEmailToken verificacaoToken) 
			 throws MessagingException {
		String urlVerificacao = url + "cadastro/verificacao/" + verificacaoToken.getToken().toString();
        String nome = usuario.getNome().split(" ")[0];
		
        String conteudo = "<div style=\"text-align: center;\"><div style=\"padding: 10px; text-align: left\"><h1>Verifique seu e-mail</h1>\n" +
                "<p>Ol&aacute;, "+ nome + ".</p>\n" +
                "<p>Voc&ecirc; realizou o cadastro na plataforma do CTP Acolhe.</p>\n" +
                "<p>Utilize o bot&atilde;o abaixo para verificar seu e-mail.</p>\n" +
                "<a href=\"" + urlVerificacao +"\" target=\"_blank\" style=\"max-width: 280px; text-decoration: none; display: inline-block; background-color: #4caf50; color: #ffffff; height: 36px; border-radius: 5px; font-weight: bold; font-size: 18px; margin: 20px 0; width: 100%; text-align: center; padding-top: 10px; \">" +
                "  Verificar E-mail" +
                "</a>" +
                "<p>Caso n&atilde;o consiga utilizar o bot&atilde;o, copie e cole o seguinte link no seu navegador:</p>\n" +
                "<p>"+ urlVerificacao + "</p>\n" +
                "<p>Atenciosamente,</p>\n" +
                "<p>Organiza&ccedil;&atilde;o CTP Acolhe &#124; Lotus.</p></div></div>";
        
        enviaEmail("Verificação de E-mail do CTP Acolhe", usuario.getEmail(), conteudo);
	}
}
