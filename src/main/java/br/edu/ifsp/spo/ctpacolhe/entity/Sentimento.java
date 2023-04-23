package br.edu.ifsp.spo.ctpacolhe.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import br.edu.ifsp.spo.ctpacolhe.common.constant.SentimentoHumor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="sentimento")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sentimento {
	
	@Id
	@Column(name = "id_sentimento")
	private String idSentimento;
	
	@Column(name = "descricao", nullable = false, unique = true)
	private String descricao;
	
	public Sentimento(SentimentoHumor sentimento) {
		this.idSentimento = sentimento.toString();
	}
}