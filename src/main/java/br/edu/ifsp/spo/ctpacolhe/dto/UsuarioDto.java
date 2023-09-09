package br.edu.ifsp.spo.ctpacolhe.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.edu.ifsp.spo.ctpacolhe.common.constant.PeriodoCurso;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(Include.NON_NULL)
public class UsuarioDto {
	
	@Schema(example = "a43ccbd5-e1e5-40cb-bc6c-ffb5d4898d6e")
	private UUID idUsuario;
	
	@Schema(example = "Renato Silva")
	private String nome;
	
	@Schema(example = "renato.s@aluno.ifsp.edu.br")
	private String email;
	
	@Schema(example = "11912345678")
	private String telefone;
	
	private CursoDto curso;
	
	@Schema(example = "MATUTINO")
	private PeriodoCurso periodo;
	
	@Schema(example = "213")
	private String turma;
	
	@Schema(example = "SP3047766")
	private String prontuario;
	
	@Schema(example = "true")
	private Boolean emailConfirmado;
	
	@Schema(example = "true")
	private Boolean ativo;
	
	@Schema(example = "2023-07-18T17:07:34")
	private LocalDateTime dataCadastro;
	
	@Schema(example = "https://media.discordapp.net/attachments/1077345452694970438/1097572563443531856/subscribe-img.png?width=480&height=480")
	private String urlAvatar;
	
	private List<PerfilDto> perfis;

}
