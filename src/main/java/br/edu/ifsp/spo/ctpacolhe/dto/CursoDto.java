package br.edu.ifsp.spo.ctpacolhe.dto;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.edu.ifsp.spo.ctpacolhe.common.constant.TipoCurso;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(Include.NON_NULL)
public class CursoDto {
	private UUID idCurso;
	private String nome;
	private TipoCurso tipo;
	private Boolean ativo;
}
