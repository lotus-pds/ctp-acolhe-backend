package br.edu.ifsp.spo.ctpacolhe.dto;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IncidenteCreateDto {
	@NotEmpty
	@Schema(example = "Conflitos em sala")
	@Length(max = 255)
	private String assunto;
	
	@NotEmpty
	private List<TipoIncidenteDto> tipos;
	@NotEmpty
	private List<PerguntaIncidenteCreateDto> perguntas;
	
	public boolean hasTipos() {
		return this.tipos != null && !this.tipos.isEmpty();
	}
	
	public boolean hasPerguntas() {
		return this.perguntas != null && !this.perguntas.isEmpty();
	}
}
