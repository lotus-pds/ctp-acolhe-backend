package br.edu.ifsp.spo.ctpacolhe.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "tipo_resposta")
@Builder
@Data
public class TipoResposta {
	@Column(name = "id_tipo_resposta")
	private String idTipoResposta;

	@Column(name = "descricao")
	private String descricao;
}
