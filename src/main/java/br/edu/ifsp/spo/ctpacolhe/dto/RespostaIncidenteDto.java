package br.edu.ifsp.spo.ctpacolhe.dto;

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
public class RespostaIncidenteDto {
	
	@Schema(example = "2")
	private Integer ordem;
	
	@Schema(example = "Não")
	private String descricao;
}
