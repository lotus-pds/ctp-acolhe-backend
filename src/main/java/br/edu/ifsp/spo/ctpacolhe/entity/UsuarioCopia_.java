package br.edu.ifsp.spo.ctpacolhe.entity;

import java.util.UUID;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import br.edu.ifsp.spo.ctpacolhe.common.constant.PeriodoCurso;

@StaticMetamodel(UsuarioCopia.class)
public abstract class UsuarioCopia_ {
	public static volatile SingularAttribute<UsuarioCopia, UUID> idUsuarioOrigem;
	public static volatile SingularAttribute<UsuarioCopia, String> nome;
	public static volatile SingularAttribute<UsuarioCopia, String> email;
	public static volatile SingularAttribute<UsuarioCopia, String> nomeCurso;
	public static volatile SingularAttribute<UsuarioCopia, String> tipoCurso;
	public static volatile SingularAttribute<UsuarioCopia, PeriodoCurso> periodo;
	public static volatile SingularAttribute<UsuarioCopia, String> turma;
	public static volatile SingularAttribute<UsuarioCopia, String> prontuario;
}
