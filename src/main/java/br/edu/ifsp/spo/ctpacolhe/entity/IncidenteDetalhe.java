package br.edu.ifsp.spo.ctpacolhe.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "incidente_detalhe")
@Builder
@Data
public class IncidenteDetalhe {
	@Id
	@Column(name = "id_incidente_detalhe")
	private UUID idIncidenteDetalhe;
	
	@Column(name = "id_incidente")
	private UUID idIncidente;
	
	@Column(name = "pergunta")
	private String pergunta;
	
	@Column(name = "resposta")
	private String resposta;
	
	@Column(name = "ordem_pergunta")
	private Integer ordemPergunta;
	
	@Column(name = "ordem_resposta")
	private Integer ordemResposta;
		
}
