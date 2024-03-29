package br.edu.ifsp.spo.ctpacolhe.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.edu.ifsp.spo.ctpacolhe.entity.id.PerguntaRespostaID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pergunta_x_resposta")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@IdClass(PerguntaRespostaID.class)
public class PerguntaResposta {
	@Id
	@Column(name = "id_pergunta")
	private UUID idPergunta;
	
	@Id
	@Column(name = "id_resposta")
	private UUID idResposta;

	@Embedded
	private PerguntaRespostaID entityID;

	@Column(name = "ordem_resposta")
	private Integer ordemResposta;

	@Column(name = "mensagem_encaminhamento")
	private String mensagemEncaminhamento;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_resposta", referencedColumnName = "id_resposta", updatable = false, insertable = false)
	private Resposta resposta;

	public Resposta getResposta() {
		Resposta novaResposta = resposta;
		novaResposta.setOrdem(ordemResposta);
		novaResposta.setMensagemEncaminhamento(mensagemEncaminhamento);
		return novaResposta;
	}

}