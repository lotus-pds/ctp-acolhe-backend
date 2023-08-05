package br.edu.ifsp.spo.ctpacolhe.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(Include.NON_NULL)
public class StatusDto {
	
	@Schema(example = "e3114512-ccc9-4bac-abb3-ff7005e10ae2")
	private String idStatus;
	
	@Schema(example = "CANCELADO")
	private String descricao;
}
