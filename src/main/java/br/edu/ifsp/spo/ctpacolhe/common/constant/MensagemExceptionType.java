package br.edu.ifsp.spo.ctpacolhe.common.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MensagemExceptionType {
	ERRO_NA_SOLICITACAO("erro.na.solicitacao"),
	
    EMAIL_NAO_CONFIRMADO("email.nao.confirmado"),
    CREDENCIAIS_INCORRETAS("credenciais.incorretas"),
    SENHA_ATUAL_INCORRETA("senha.atual.incorreta"),
    SENHA_ATUAL_E_NOVA_IGUAIS("senha.atual.e.nova.iguais"),

    USUARIO_NAO_ENCONTRADO("usuario.nao.encontrado"),
    USUARIO_JA_INATIVO("usuario.ja.inativo"),
    USUARIO_JA_ATIVO("usuario.ja.ativo"),
    EMAIL_NAO_ENCONTRADO("email.nao.encontrado"),
    EMAIL_JA_CADASTRADO("email.ja.cadastrado"),
    PRONTUARIO_JA_CADASTRADO("prontuario.ja.cadastrado"),

    TOKEN_RENOVACAO_NAO_ENCONTRADO("token.renovacao.nao.encontrado"),
    TOKEN_RENOVACAO_EXPIROU("token.renovacao.expirou"),
    
    TOKEN_VERIFICACAO_NAO_ENCONTRADO("token.verificacao.nao.encontrado"),
    TOKEN_VERIFICACAO_EXPIROU("token.verificacao.expirou"),
    TOKEN_VERIFICACAO_AGUARDE_UM_MINUTO("token.verificacao.aguarde.um.minuto"),
    PROBLEMA_COM_ENVIO_EMAIL_VERIFICACAO("problema.com.envio.email.verificacao"),
    
    TOKEN_REDEFINICAO_SENHA_NAO_ENCONTRADO("token.redefinicao.senha.nao.encontrado"),
    TOKEN_REDEFINICAO_SENHA_EXPIROU("token.redefinicao.senha.expirou"),
    TOKEN_REDEFINICAO_SENHA_AGUARDE_UM_MINUTO("token.redefinicao.senha.aguarde.um.minuto"),
    EMAIL_REDEFINICAO_SENHA_JA_ENVIADO("email.redefinicao.senha.ja.enviado"),

    HUMOR_REGISTRADO_HOJE("humor.registrado.hoje"),

    PERFIL_NAO_ENCONTRADO("perfil.nao.encontrado"),
    EMAIL_USUARIO_NAO_CONFIRMADO("email.usuario.nao.confirmado"),

    USUARIO_INATIVO("usuario.inativo"),

    PERGUNTA_NAO_ENCONTRADA("pergunta.nao.encontrada"),
    PERGUNTA_NAO_PERMITE_MULTIPLAS_RESPOSTAS("pergunta.nao.permite.multiplas.respostas"),
    PERGUNTA_OBRIGATORIA_SEM_RESPOSTAS("pergunta.obrigatoria.sem.respostas"),
    
    INCIDENTE_NAO_ENCONTRADO("incidente.nao.encontrado"),
    INCIDENTE_NAO_POSSUI_PERGUNTAS("incidente.nao.possui.perguntas"),
    INCIDENTE_NAO_POSSUI_TIPOS("incidente.nao.possui.tipos"),
    INCIDENTE_EM_PROCESSO_CANCELADO_OU_FINALIZADO("incidente.em.processo.cancelado.ou.finalizado"),
    INCIDENTE_PENDENTE_CANCELADO_OU_FINALIZADO("incidente.pendente.cancelado.ou.finalizado"),
    INCIDENTE_JA_CANCELADO_OU_FINALIZADO("incidente.ja.cancelado.ou.finalizado"),
    INCIDENTE_DE_OUTRO_ALUNO("incidente.de.outro.aluno"),
    
    TIPO_INCIDENTE_NAO_ENCONTRADO("tipo.incidente.nao.encontrado"),
    TIPO_INCIDENTE_NAO_ESTA_ATIVO("tipo.incidente.nao.esta.ativo"),

    RESPOSTA_PARA_PERGUNTA_NAO_ENCONTRADA("resposta.para.pergunta.nao.encontrada"),
    
    AGENDAMENTO_INICIAL_DEPOIS_DE_AGENDAMENTO_FINAL("agendamento.inicial.depois.de.agendamento.final"),
    PERIODO_AGENDAMENTO_NO_PASSADO("periodo.agendamento.no.passado"),
    EXISTE_AGENDAMENTO_NESTE_PERIODO("existe.agendamento.neste.periodo");

    String message;
}