package br.edu.ifsp.spo.ctpacolhe.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MensagemExceptionType {
	EMAIL_NAO_CONFIRMADO("E-mail ainda não confirmado"),
	CREDENCIAIS_INCORRETAS("Usuário e/ou senha incorreto(s)"),
	
	USUARIO_NAO_ENCONTRADO("Usuário não encontrado"),
	EMAIL_NAO_ENCONTRADO("E-mail não encontrado"),
	EMAIL_JA_CADASTRADO("E-mail já cadastrado"),
	PRONTUARIO_JA_CADASTRADO("Prontuário já cadastrado"),
	
	TOKEN_NAO_ENCONTRADO("Token de %s não encontrado"),
	TOKEN_EXPIROU("Token de %s para o e-mail=%s expirou"),
	TOKEN_RENOVACAO_EXPIROU("Token de renovação expirou, acesse sua conta novamente"),
	TOKEN_AGUARDE_UM_MINUTO("Aguarde 1 minuto para o reenvio do e-mail de %s"),
	
	EMAIL_REDEFINICAO_SENHA_JA_ENVIADO("E-mail para redefinição de senha já enviado, solicite o reenvio ou tente novamente mais tarde"),
	
	PROBLEMA_COM_ENVIO_EMAIL("Problema com o envio do e-mail de %s, tente novamente mais tarde"),
	
	HUMOR_REGISTRADO_HOJE("Humor já registrado hoje");
	
	String message;
}
