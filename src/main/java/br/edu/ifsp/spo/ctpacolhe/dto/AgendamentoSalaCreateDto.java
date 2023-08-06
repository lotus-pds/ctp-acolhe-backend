package br.edu.ifsp.spo.ctpacolhe.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AgendamentoSalaCreateDto {
	@NotEmpty
	@Max(value = 500)
	private String nomeAlunos;
	@NotEmpty
	@Max(value = 100)
	private String nomeTecnico;
	private LocalDateTime dataAtendimentoInicial;
	private LocalDateTime dataAtendimentoFinal;
	
}
