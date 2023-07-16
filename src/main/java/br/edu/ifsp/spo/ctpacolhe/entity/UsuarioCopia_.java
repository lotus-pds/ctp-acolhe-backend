package br.edu.ifsp.spo.ctpacolhe.entity;

import java.util.UUID;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(UsuarioCopia.class)
public abstract class UsuarioCopia_ {
	public static volatile SingularAttribute<UsuarioCopia, UUID> idUsuarioOrigem;
}
