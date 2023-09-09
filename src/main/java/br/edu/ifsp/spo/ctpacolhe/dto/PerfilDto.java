package br.edu.ifsp.spo.ctpacolhe.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PerfilDto {
	
	@Schema(example = "ALU")
	private String idPerfil;
	
	@Schema(example = "Aluno")
	private String descricao;
}
