package br.edu.ifsp.spo.ctpacolhe.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RespostaIncidenteDto {
	private Integer ordem;
	private String descricao;
}
