package br.edu.ifsp.spo.ctpacolhe.dto;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.edu.ifsp.spo.ctpacolhe.common.constant.PeriodoCurso;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(Include.NON_NULL)
public class UsuarioCopiaDto {
	private UUID idUsuarioCopia;
	private UUID idUsuarioOrigem;
	private String nome;
	private String email;
	private String telefone;
	private String nomeCurso;
	private String tipoCurso;
	private PeriodoCurso periodo;
	private String turma;
	private String prontuario;
	private String urlAvatar;
}
