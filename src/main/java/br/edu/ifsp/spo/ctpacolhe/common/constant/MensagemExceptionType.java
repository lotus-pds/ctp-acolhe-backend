package br.edu.ifsp.spo.ctpacolhe.common.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MensagemExceptionType {
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

    TOKEN_NAO_ENCONTRADO("token.nao.encontrado"),
    TOKEN_EXPIROU("token.expirou"),
    TOKEN_RENOVACAO_EXPIROU("token.renovacao.expirou"),
    TOKEN_AGUARDE_UM_MINUTO("token.aguarde.um.minuto"),

    EMAIL_REDEFINICAO_SENHA_JA_ENVIADO("email.redefinicao.senha.ja.enviado"),

    PROBLEMA_COM_ENVIO_EMAIL("problema.com.envio.email"),

    HUMOR_REGISTRADO_HOJE("humor.registrado.hoje"),

    PERFIL_NAO_ENCONTRADO("perfil.nao.encontrado"),
    EMAIL_USUARIO_NAO_CONFIRMADO("email.usuario.nao.confirmado"),

    USUARIO_INATIVO("usuario.inativo"),

    PERGUNTA_NAO_ENCONTRADA("pergunta.nao.encontrada"),
    PERGUNTA_NAO_PERMITE_MULTIPLAS_RESPOSTAS("pergunta.nao.permite.multiplas.respostas"),
    
    INCIDENTE_NAO_ENCONTRADO("incidente.nao.encontrado"),
    INCIDENTE_NAO_POSSUI_PERGUNTAS("incidente.nao.possui.perguntas"),

    RESPOSTA_PARA_PERGUNTA_NAO_ENCONTRADA("resposta.para.pergunta.nao.encontrada");

    String message;
}