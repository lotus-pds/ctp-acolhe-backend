package br.edu.ifsp.spo.ctpacolhe.dto;

import javax.validation.constraints.NotEmpty;

import br.edu.ifsp.spo.ctpacolhe.common.annotation.Password;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SenhaUpdateDto {
	
	@NotEmpty
	@Password
	private String senhaAtual;
	@NotEmpty
	@Password
	private String senhaNova;
	
}
