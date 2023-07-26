package br.edu.ifsp.spo.ctpacolhe.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import br.edu.ifsp.spo.ctpacolhe.common.constant.TipoResposta;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "incidente_detalhe")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
	
	@Column(name = "tipo_resposta")
	@Enumerated(EnumType.STRING)
	private TipoResposta tipoResposta;

	@Column(name = "pergunta_obrigatoria")
	private Boolean perguntaObrigatoria;
	
}
