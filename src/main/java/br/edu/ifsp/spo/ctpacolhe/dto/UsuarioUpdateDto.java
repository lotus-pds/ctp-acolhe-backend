package br.edu.ifsp.spo.ctpacolhe.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.edu.ifsp.spo.ctpacolhe.common.annotation.Name;
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
public class UsuarioUpdateDto {
	
	@NotEmpty
	@Size(min = 5, max = 256)
	@Name
	@Schema(example = "Renato Silva")
	private String nome;
	
	@Size(max = 11)
	@Schema(example = "11967480321")
	private String telefone;
	
	private CursoDto curso;
	
	@Schema(example = "MATUTINO")
	private PeriodoCurso periodo;
	
	@Size(max = 5)
	@Schema(example = "231")
	private String turma;
	
	@Schema(example = "SP3047766")
	@NotEmpty
	@Size(max = 9)
	private String prontuario;
	
}
