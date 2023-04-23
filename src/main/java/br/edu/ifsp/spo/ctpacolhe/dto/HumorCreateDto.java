package br.edu.ifsp.spo.ctpacolhe.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HumorCreateDto {

	@NotEmpty
	@Size(max = 20)
	private String descricao;
}
