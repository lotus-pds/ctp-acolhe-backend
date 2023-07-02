package br.edu.ifsp.spo.ctpacolhe.dto;

import java.util.UUID;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import br.edu.ifsp.spo.ctpacolhe.common.annotation.Name;
import br.edu.ifsp.spo.ctpacolhe.common.annotation.Password;
import br.edu.ifsp.spo.ctpacolhe.common.constant.PeriodoCurso;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioCreateDto {
	
	@NotEmpty
	@Size(min = 5, max = 256)
	@Name
	private String nome;
	@NotEmpty
	@Email
	@Size(max = 350)
	private String email;
	@Size(max = 11)
	private String telefone;
	private UUID idCurso;
	private PeriodoCurso periodo;
	@Size(max = 5)
	private String turma;
	@NotEmpty
	@Size(max = 9)
	private String prontuario;
	@NotEmpty
	@Password
	private String senha;
	@NotEmpty
	private String urlAvatar;

}
