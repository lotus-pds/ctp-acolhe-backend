package br.edu.ifsp.spo.ctpacolhe.dto;

import java.time.LocalDateTime;
import java.util.List;
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
public class IncidenteDto {
	private UUID idIncidente;
	private String assunto;
	private LocalDateTime dataIncidente;
	private StatusDto status;
	private List<TipoIncidenteDto> tipos;
	private UsuarioCopiaDto usuarioCopia;
	private List<PerguntaIncidenteDto> perguntas;
}
