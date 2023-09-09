package br.edu.ifsp.spo.ctpacolhe.dto;

import java.util.UUID;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import br.edu.ifsp.spo.ctpacolhe.common.annotation.Name;
import br.edu.ifsp.spo.ctpacolhe.common.annotation.Password;
import br.edu.ifsp.spo.ctpacolhe.common.constant.PeriodoCurso;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioCreateDto {
	
	@NotEmpty
	@Size(min = 5, max = 256)
	@Name
	@Schema(example = "Renato Silva")
	private String nome;
	
	@NotEmpty
	@Email
	@Size(max = 350)
	@Schema(example = "renato.s@aluno.ifsp.edu.br")
	private String email;
	
	@Size(max = 11)
	@Schema(example = "11912345678")
	private String telefone;
	
	private UUID idCurso;
	private PeriodoCurso periodo;
	
	@Size(max = 5)
	@Schema(example = "213")
	private String turma;
	
	@NotEmpty
	@Size(max = 9)
	@Schema(example = "SP3047766")
	private String prontuario;
	
	@NotEmpty
	@Password
	@Schema(example = "1357!Renatao")
	private String senha;
	
	@NotEmpty
	@Schema(example = "https://media.discordapp.net/attachments/1077345452694970438/1097572563443531856/subscribe-img.png?width=480&height=480")
	private String urlAvatar;

}
