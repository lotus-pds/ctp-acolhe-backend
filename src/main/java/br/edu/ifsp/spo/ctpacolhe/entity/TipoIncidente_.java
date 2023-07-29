package br.edu.ifsp.spo.ctpacolhe.entity;

import java.util.UUID;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(TipoIncidente.class)
public abstract class TipoIncidente_ {
	public static volatile SingularAttribute<TipoIncidente, UUID> idTipoIncidente;
}
