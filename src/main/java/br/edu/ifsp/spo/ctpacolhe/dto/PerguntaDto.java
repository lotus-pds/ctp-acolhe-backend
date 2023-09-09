package br.edu.ifsp.spo.ctpacolhe.dto;

import java.util.List;
import java.util.UUID;

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
public class PerguntaDto {
	
	@Schema(example = "205cd2b1-3f76-4f96-90ae-201c1060ebad")
	private UUID idPergunta;
	
	@Schema(example = "Possui alguma necessidade especial?")
	private String descricao;
	
	@Schema(example = "1")
	private Integer ordem;
	
	@Schema(example = "true")
	private Boolean obrigatoria;
	
	private TipoResposta tipoResposta;
	private List<RespostaDto> respostas;
}
