package br.edu.ifsp.spo.ctpacolhe.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.edu.ifsp.spo.ctpacolhe.common.constant.PeriodoCurso;
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
	
	private UUID idUsuario;
	private String nome;
	private String email;
	private String telefone;
	private CursoDto curso;
	private PeriodoCurso periodo;
	private String turma;
	private String prontuario;
	private Boolean emailConfirmado;
	private Boolean ativo;
	private LocalDateTime dataCadastro;
	private String urlAvatar;
	private List<PerfilDto> perfis;

}
