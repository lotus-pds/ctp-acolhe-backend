package br.edu.ifsp.spo.ctpacolhe.dto;

import java.time.LocalDateTime;
import java.util.List;
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
public class IncidenteDto {
	
	@Schema(example = "e3114512-ccc9-4bac-abb3-ff7005e10ae2")
	private UUID idIncidente;
	
	@Schema(example = "Dificuldade com os estudos")
	private String assunto;
	
	@Schema(example = "2023-07-16T17:07:34")
	private LocalDateTime dataIncidente;
	
	private StatusDto status;
	
	private List<TipoIncidenteDto> tipos;
	
	private UsuarioCopiaDto usuarioCopia;
	
	private List<PerguntaIncidenteDto> perguntas;
}
