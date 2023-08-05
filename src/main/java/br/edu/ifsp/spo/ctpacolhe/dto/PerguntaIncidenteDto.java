package br.edu.ifsp.spo.ctpacolhe.dto;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.edu.ifsp.spo.ctpacolhe.common.constant.TipoResposta;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@JsonInclude(Include.NON_NULL)
public class PerguntaIncidenteDto {
	
	@Schema(example = "Possui alguma necessidade especial?")
	private String descricao;
	
	@Schema(example = "3")
	private Integer ordem;
	
	@Schema(example = "ALTERNATIVA")
	private TipoResposta tipoResposta;
	
	@Schema(example = "true")
	private Boolean obrigatoria;
	
	private List<RespostaIncidenteDto> respostas;
}
