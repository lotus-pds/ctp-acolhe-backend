package br.edu.ifsp.spo.ctpacolhe.dto;

import javax.validation.constraints.NotEmpty;

import br.edu.ifsp.spo.ctpacolhe.common.annotation.Password;
import io.swagger.v3.oas.annotations.media.Schema;
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
	@Schema(example = "1357!Renatao")
	private String senhaAtual;
	
	@NotEmpty
	@Password
	@Schema(example = "2468@Renatinho")
	private String senhaNova;
	
}
