package br.edu.ifsp.spo.ctpacolhe.dto;

import javax.validation.constraints.NotEmpty;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AcessoCreateDto {
	
	@NotEmpty(message = "É obrigatório")
	@Schema(example = "renato.s@aluno.ifsp.edu.br")
	private String email;
	
	
	@NotEmpty(message = "É obrigatório")
	@Schema(example = "1357!Renatao")
	private String senha;
}
