package br.edu.ifsp.spo.ctpacolhe.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AgendamentoSalaCreateDto {
	
	@NotEmpty
	@Length(max = 500)
	@Schema(example = "Fernanda, Tatiana")
	private String nomeAlunos;
	
	@NotEmpty
	@Length(max = 100)
	@Schema(example = "Sara")
	private String nomeTecnico;
	
	@Schema(example = "2023-09-09T10:30:00")
	private LocalDateTime dataAtendimentoInicial;
	
	@Schema(example = "2023-09-09T11:30:00")
	private LocalDateTime dataAtendimentoFinal;
	
}
