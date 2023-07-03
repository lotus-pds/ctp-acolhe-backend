package br.edu.ifsp.spo.ctpacolhe.entity;

import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Usuario.class)
public abstract class Usuario_ {
	public static volatile SingularAttribute<Usuario, String> nome;
	public static volatile SingularAttribute<Usuario, String> email;
	public static volatile SingularAttribute<Usuario, String> prontuario;
	public static volatile SingularAttribute<Usuario, Curso> curso;
	public static volatile SingularAttribute<Usuario, Boolean> ativo;
	public static volatile SetAttribute<Usuario, Perfil> perfis;
}
