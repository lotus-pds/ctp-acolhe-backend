package br.edu.ifsp.spo.ctpacolhe.dto;

import java.util.UUID;

import org.hibernate.validator.constraints.Length;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RespostaIncidenteCreateDto {
	
	@Schema(example = "bc1ac8ab-2f89-4f42-8b7d-23d8f6a9a39e")
	private UUID idResposta;
	
	@Schema(example = "Abaixo de 25%")
	@Length(max = 1000)
	private String textoResposta;
}
