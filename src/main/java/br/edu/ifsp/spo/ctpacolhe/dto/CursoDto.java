package br.edu.ifsp.spo.ctpacolhe.dto;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.edu.ifsp.spo.ctpacolhe.common.constant.TipoCurso;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(Include.NON_NULL)
public class CursoDto {
	
	@Schema(example = "efd3f96f-b860-43a8-b61b-64e98227b4ff")
	private UUID idCurso;
	
	@Schema(example = "Técnico em Informática Integrado ao Ensino Médio")
	private String nome;
	
	@Schema(example = "TECNICO_INTEGRADO")
	private TipoCurso tipo;
	
	@Schema(example = "true")
	private Boolean ativo;
}
