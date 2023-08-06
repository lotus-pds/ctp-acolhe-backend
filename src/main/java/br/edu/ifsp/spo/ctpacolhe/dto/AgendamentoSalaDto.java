package br.edu.ifsp.spo.ctpacolhe.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@JsonInclude(Include.NON_NULL)
public class AgendamentoSalaDto {
	private UUID idAgendamento;
	private String nomeAlunos;
	private String nomeTecnico;
	private LocalDateTime dataAtendimentoInicial;
	private LocalDateTime dataAtendimentoFinal;
	private UsuarioDto criadoPor;
	private String dataCriacao;
}
