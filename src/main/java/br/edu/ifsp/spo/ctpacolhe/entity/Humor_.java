package br.edu.ifsp.spo.ctpacolhe.entity;

import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Humor.class)
public abstract class Humor_ {
	public static volatile SingularAttribute<Humor, LocalDate> dataHumor;
	public static volatile SingularAttribute<Humor, UUID> idUsuario;
}
