package br.edu.ifsp.spo.ctpacolhe.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MensagemExceptionType {
	AUT_EMAIL_NAO_CONFIRMADO("E-mail ainda não confirmado"),
	AUT_CREDENCIAIS_INCORRETAS("Usuário e/ou senha incorreto(s)"),
	
	CAD_EMAIL_NAO_ENCONTRADO("E-mail não encontrado"),
	CAD_EMAIL_JA_CADASTRADO("E-mail já cadastrado"),
	CAD_PRONTUARIO_JA_CADASTRADO("Prontuário já cadastrado"),
	
	TOKEN_NAO_ENCONTRADO("Token de verificação não encontrado"),
	TOKEN_EXPIROU("Token de verificação para o e-mail=%s expirou"),
	TOKEN_AGUARDE_UM_MINUTO("Aguarde 1 minuto para o reenvio do e-mail de verificação"),
	
	EMAIL_REDEFINICAO_SENHA_JA_ENVIADO("E-mail para redefinição de senha já enviado, solicite o reenvio ou tente novamente mais tarde"),
	
	PROBLEMA_COM_ENVIO_EMAIL_VERIFICACAO("Problema com o envio do e-mail de verificação, tente novamente mais tarde"),
	PROBLEMA_COM_ENVIO_EMAIL_REDEFINICAO_SENHA("Problema com o envio do e-mail de redefinição de senha, tente novamente mais tarde"),
	
	HUMOR_REGISTRADO_HOJE("Humor já registrado hoje");
	
	String message;
}
