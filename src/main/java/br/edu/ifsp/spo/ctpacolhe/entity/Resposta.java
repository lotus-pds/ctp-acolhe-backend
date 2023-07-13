package br.edu.ifsp.spo.ctpacolhe.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "resposta")
@Builder
@Data
public class Resposta {
	@Id
	@Column(name = "id_resposta")
	private UUID idResposta;
	
	@Column(name = "descricao")
	private String descricao;
	
	@Transient
	private Integer ordem;
}

