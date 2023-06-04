package br.edu.ifsp.spo.ctpacolhe.common.email;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import br.edu.ifsp.spo.ctpacolhe.common.constant.EmailContentType;
import br.edu.ifsp.spo.ctpacolhe.common.util.CtpAcolheUtils;

@Component
public class EmailContentService {
	
	@Value("${account.verification-token-expires-in}")
	private Integer VERIFICACAO_TOKEN_EXPIRA_EM;
	
	@Value("${account.password-reset-token-expires-in}")
	private Integer REDEFINICAO_TOKEN_EXPIRA_EM;
	
	private static final String TITULO = "{{TITULO}}";
	private static final String NOME = "{{NOME}}";
	private static final String PARAGRAFO = "{{PARAGRAFO}}";
	private static final String ACAO = "{{ACAO}}";
	private static final String URL_LINK = "{{URL_LINK}}";
	private static final String TITULO_BOTAO = "{{TITULO_BOTAO}}";
	
	public String getConteudoEmail(String nome, String url, EmailContentType tipoEmail) {		
		String htmlContent = TemplateEmailDefault.getContent();
		
		htmlContent = htmlContent.replace(NOME, nome)
				.replace(URL_LINK, url);
		
		if (EmailContentType.VERIFICACAO_EMAIL.equals(tipoEmail)) {
			htmlContent = htmlContent.replace(TITULO, "Confirmação de E-mail")
					.replace(PARAGRAFO,
							"Você realizou seu cadastro com sucesso na plataforma do CTP Acolhe em "
									+ CtpAcolheUtils.formatDateTime(CtpAcolheUtils.getDateTimeBrazil())
									+ ". Este link de confirmação de e-mail é válido apenas pelos próximos "
									+ REDEFINICAO_TOKEN_EXPIRA_EM / 60 + " minutos.")
					.replace(ACAO, "Utilize o botão abaixo para verificar seu e-mail:")
					.replace(TITULO_BOTAO, "Confirmar E-mail");
		}
		if (EmailContentType.REDEFINICAO_SENHA.equals(tipoEmail)) {
			htmlContent = htmlContent.replace(TITULO, "Redefinição de Senha").replace(PARAGRAFO,
					"Recebemos uma solicitação de alteração da sua senha de acesso à plataforma do CTP Acolhe em "
							+ CtpAcolheUtils.formatDateTime(CtpAcolheUtils.getDateTimeBrazil())
							+ ". Este link de redefinição de senha é válido apenas pelos próximos "
							+ REDEFINICAO_TOKEN_EXPIRA_EM / 60 + " minutos.")
					.replace(ACAO, "Se você reconhece essa ação, clique no botão abaixo para prosseguir:")
					.replace(TITULO_BOTAO, "Redefinir Senha");
		}
		
		return htmlContent;
	}
}
