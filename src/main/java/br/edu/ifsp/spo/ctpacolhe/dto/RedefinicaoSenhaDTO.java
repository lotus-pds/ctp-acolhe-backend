package br.edu.ifsp.spo.ctpacolhe.dto;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RedefinicaoSenhaDTO {
	@NotNull
	private String token;
	@NotNull
	private String senha;
}
