package br.edu.ifsp.spo.ctpacolhe.dto;

import java.util.UUID;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(Include.NON_NULL)
public class TipoIncidenteDto {
	
	@NotEmpty
	@Schema(example = "8aa3785e-4f60-4c08-a18e-b5710d1e8c9b")
	private UUID idTipoIncidente;
	
	@Schema(example = "Acolhimento psicológico voltado ao meio educacional/institucional")
	private String tipo;
	
	@Schema(example = "Ajuda voltada ao psicológico voltado ao ambiente, pressão e relações sociais do meio escolar/institucional")
	private String descricao;
	
	@Schema(example = "true")
	private Boolean ativo;
}
