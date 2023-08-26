package br.edu.ifsp.spo.ctpacolhe.dto;

import java.util.UUID;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RespostaIncidenteCreateDto {
	private UUID idResposta;
	@Length(max = 1000)
	private String textoResposta;
}
