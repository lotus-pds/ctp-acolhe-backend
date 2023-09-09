package br.edu.ifsp.spo.ctpacolhe.dto;

import java.util.List;
import java.util.UUID;

import javax.validation.constraints.NotEmpty;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PerguntaIncidenteCreateDto {
	
	@NotEmpty
	@Schema(example = "205cd2b1-3f76-4f96-90ae-201c1060ebad")
	private UUID idPergunta;
	
	private List<RespostaIncidenteCreateDto> respostas;
	
	public boolean hasRespostas() {
		return respostas != null && !respostas.isEmpty();
	}
}
