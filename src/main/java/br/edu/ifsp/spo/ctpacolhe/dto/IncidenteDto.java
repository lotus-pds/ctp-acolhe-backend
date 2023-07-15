package br.edu.ifsp.spo.ctpacolhe.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IncidenteDto {
	private UUID idIncidente;
	private String assunto;
	private LocalDateTime dataIncidente;
	private StatusDto status;
	private UsuarioCopiaDto usuarioCopia;
	private List<PerguntaIncidenteDto> perguntas;
}
