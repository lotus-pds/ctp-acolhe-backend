package br.edu.ifsp.spo.ctpacolhe.dto;

import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IncidenteCreateDto {
	@NotEmpty
	@Max(value = 255)
	private String assunto;
	private List<PerguntaCreateDto> perguntas;
}
