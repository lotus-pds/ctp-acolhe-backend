package br.edu.ifsp.spo.ctpacolhe.dto;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.edu.ifsp.spo.ctpacolhe.common.annotation.Password;
import lombok.Value;

@Value
public class RedefinicaoSenhaDTO {
	
	@NotNull
	String token;
	
	@NotNull
	@Size(min = 8, max = 64)
	@Password
	String senha;
	
}
