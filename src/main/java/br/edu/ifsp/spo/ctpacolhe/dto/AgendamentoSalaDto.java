package br.edu.ifsp.spo.ctpacolhe.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@JsonInclude(Include.NON_NULL)
public class AgendamentoSalaDto {
	
	@Schema(example = "e3114512-ccc9-4bac-abb3-ff7005e10ae2")
	private UUID idAgendamento;
	
	@Schema(example = "Fernanda, Tatiana")
	private String nomeAlunos;
	
	@Schema(example = "Sara")
	private String nomeTecnico;
	
	@Schema(example = "2023-09-09T10:30:00")
	private LocalDateTime dataAtendimentoInicial;
	
	@Schema(example = "2023-09-09T11:30:00")
	private LocalDateTime dataAtendimentoFinal;
	
	private UsuarioDto criadoPor;
	
	@Schema(example = "2023-09-07T11:30:00")
	private LocalDateTime dataCriacao;
}
