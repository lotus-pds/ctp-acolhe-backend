package br.edu.ifsp.spo.ctpacolhe.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AgendamentoSalaCreateDto {
	@NotEmpty
	@Length(max = 500)
	private String nomeAlunos;
	@NotEmpty
	@Length(max = 100)
	private String nomeTecnico;
	private LocalDateTime dataAtendimentoInicial;
	private LocalDateTime dataAtendimentoFinal;
	
}
