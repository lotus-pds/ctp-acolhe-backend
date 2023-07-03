package br.edu.ifsp.spo.ctpacolhe.dto;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.edu.ifsp.spo.ctpacolhe.common.annotation.Password;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RedefinicaoSenhaDto {
	
	@NotNull
	private String token;
	@NotNull
	@Size(min = 8, max = 64)
	@Password
	private String senha;
	
}
