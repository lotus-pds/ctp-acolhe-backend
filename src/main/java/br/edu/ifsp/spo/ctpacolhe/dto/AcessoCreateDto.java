package br.edu.ifsp.spo.ctpacolhe.dto;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AcessoCreateDto {
	@NotEmpty(message = "É obrigatório")
	private String email;
	@NotEmpty(message = "É obrigatório")
	private String senha;
}
