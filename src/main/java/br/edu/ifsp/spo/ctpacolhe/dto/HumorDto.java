package br.edu.ifsp.spo.ctpacolhe.dto;

import java.time.LocalDate;
import java.util.UUID;

import br.edu.ifsp.spo.ctpacolhe.common.constant.SentimentoHumor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HumorDto {
	private UUID idHumor;
	private LocalDate dataHumor;
	private UUID idUsuario;
	private SentimentoHumor idSentimento;
}
