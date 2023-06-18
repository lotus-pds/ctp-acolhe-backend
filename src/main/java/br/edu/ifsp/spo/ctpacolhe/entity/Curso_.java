package br.edu.ifsp.spo.ctpacolhe.entity;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import br.edu.ifsp.spo.ctpacolhe.common.constant.TipoCurso;

@StaticMetamodel(Curso.class)
public abstract class Curso_ {
	public static volatile SingularAttribute<Curso, String> nome;
	public static volatile SingularAttribute<Curso, TipoCurso> tipo;
	public static volatile SingularAttribute<Curso, Boolean> ativo;
}
