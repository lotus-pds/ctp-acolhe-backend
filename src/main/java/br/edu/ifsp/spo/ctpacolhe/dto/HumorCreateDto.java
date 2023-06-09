package br.edu.ifsp.spo.ctpacolhe.dto;

import javax.validation.constraints.NotNull;

import br.edu.ifsp.spo.ctpacolhe.common.constant.SentimentoHumor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HumorCreateDto {
	@NotNull
	private SentimentoHumor idSentimento;
}
