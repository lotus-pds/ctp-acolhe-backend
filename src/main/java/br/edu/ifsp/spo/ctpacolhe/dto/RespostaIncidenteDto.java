package br.edu.ifsp.spo.ctpacolhe.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@JsonInclude(Include.NON_NULL)
public class RespostaIncidenteDto {
	private Integer ordem;
	private String descricao;
}
