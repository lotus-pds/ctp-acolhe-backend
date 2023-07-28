package br.edu.ifsp.spo.ctpacolhe.entity;

import java.time.LocalDateTime;

import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Incidente.class)
public abstract class Incidente_ {
	public static volatile SingularAttribute<Incidente, String> assunto;
	public static volatile SingularAttribute<Incidente, String> idStatus;
	public static volatile SingularAttribute<Incidente, LocalDateTime> dataIncidente;
	public static volatile SingularAttribute<Incidente, UsuarioCopia> usuarioCopia;
	public static volatile SetAttribute<Incidente, TipoIncidente> tipos;
	public static volatile SingularAttribute<Incidente, Status> status;
	public static volatile SetAttribute<Incidente, IncidenteDetalhe> detalhes;
}
