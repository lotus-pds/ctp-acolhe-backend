package br.edu.ifsp.spo.ctpacolhe.dto;

import javax.validation.constraints.NotNull;

import br.edu.ifsp.spo.ctpacolhe.common.constant.SentimentoHumor;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HumorCreateDto {
	
	@NotNull
	@Schema(example = "ALE")
	private SentimentoHumor idSentimento;
}
