package br.edu.ifsp.spo.ctpacolhe.dto;

import java.util.UUID;

import javax.validation.constraints.Max;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RespostaCreateDto {
	private UUID idResposta;
	@Max(value = 1000)
	private String textoResposta;
}
