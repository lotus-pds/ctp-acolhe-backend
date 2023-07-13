package br.edu.ifsp.spo.ctpacolhe.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "pergunta")
@Builder
@Data
public class Pergunta {
	@Column(name = "id_pergunta")
	private String idPergunta;
	@Column(name = "descricao")
	private String descricao;
	@Column(name = "ordem")
	private Integer ordem;
	@Column(name = "id_tipo_resposta")
	private String idTipoResposta;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_tipo_resposta", referencedColumnName = "id_tipo_resposta", updatable = false, insertable = false)
	private TipoResposta tipoResposta;
}
