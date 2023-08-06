package br.edu.ifsp.spo.ctpacolhe.entity;

import java.time.LocalDateTime;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(AgendamentoSala.class)
public abstract class AgendamentoSala_ {
	public static volatile SingularAttribute<AgendamentoSala, LocalDateTime> dataAtendimentoInicial;
	public static volatile SingularAttribute<AgendamentoSala, LocalDateTime> dataAtendimentoFinal;
	public static volatile SingularAttribute<AgendamentoSala, Usuario> criadoPor;
}
