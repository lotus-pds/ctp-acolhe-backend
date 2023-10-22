package br.edu.ifsp.spo.ctpacolhe.entity;

import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import br.edu.ifsp.spo.ctpacolhe.common.constant.SentimentoHumor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="humor")
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Getter
@Setter
public class Humor {
	
	@Id
	@Column(name = "id_humor")
	private UUID idHumor;
	
	@Column(name = "data_humor")
	private LocalDate dataHumor;
	
	@Column(name = "id_usuario")
	private UUID idUsuario;
	
	@Column(name = "id_sentimento")
	@Enumerated(EnumType.STRING)
	private SentimentoHumor idSentimento;

	@Column(name = "descricao")
	private String descricao;
}
