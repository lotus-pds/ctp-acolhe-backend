package br.edu.ifsp.spo.ctpacolhe.dto;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.edu.ifsp.spo.ctpacolhe.common.constant.PeriodoCurso;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(Include.NON_NULL)
public class UsuarioCopiaDto {
	
	@Schema(example = "6f20af9c-b60c-4cf4-b060-9b58e105ff80")
	private UUID idUsuarioCopia;
	
	@Schema(example = "a43ccbd5-e1e5-40cb-bc6c-ffb5d4898d6e")
	private UUID idUsuarioOrigem;
	
	@Schema(example = "Renato Silva")
	private String nome;
	
	@Schema(example = "renato.s@aluno.ifsp.edu.br")
	private String email;
	
	@Schema(example = "11912345678")
	private String telefone;
	
	@Schema(example = "Técnico em Informática Integrado ao Ensino Médio")
	private String nomeCurso;
	
	@Schema(example = "TECNICO_INTEGRADO")
	private String tipoCurso;
	
	@Schema(example = "MATUTINO")
	private PeriodoCurso periodo;
	
	@Schema(example = "213")
	private String turma;
	
	@Schema(example = "SP3047766")
	private String prontuario;
	
	@Schema(example = "https://media.discordapp.net/attachments/1077345452694970438/1097572563443531856/subscribe-img.png?width=480&height=480")
	private String urlAvatar;
}
