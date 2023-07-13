package br.edu.ifsp.spo.ctpacolhe.entity.id;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class PerguntaRespostaID implements Serializable {

	private static final long serialVersionUID = -7399834246164756654L;

	@Column(name = "id_pergunta", insertable = false, updatable = false)
	private UUID idPergunta;
	
	@Column(name = "id_resposta", insertable = false, updatable = false)
	private UUID idResposta;
}

