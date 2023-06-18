package br.edu.ifsp.spo.ctpacolhe.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import br.edu.ifsp.spo.ctpacolhe.common.constant.TipoCurso;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "curso")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Curso {
	
	@Id
	@Column(name = "id_curso")
	private UUID idCurso;
	@Column(name = "nome")
	private String nome;
	@Column(name = "tipo")
	@Enumerated(EnumType.STRING)
	private TipoCurso tipo;
	@Column(name = "ativo")
	@Builder.Default
	private Boolean ativo = true;
}
