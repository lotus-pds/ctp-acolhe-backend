package br.edu.ifsp.spo.ctpacolhe.dto;

import java.util.List;
import java.util.UUID;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PerguntaCreateDto {
	@NotEmpty
	private UUID idPergunta;
	private List<RespostaCreateDto> respostas;
}
