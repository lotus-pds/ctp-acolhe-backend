package br.edu.ifsp.spo.ctpacolhe.dto;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@JsonInclude(Include.NON_NULL)
public class RespostaDto {
	
	@Schema(example = "7dfae4a0-45b1-4a8d-935a-50d8c14ee55d")
	private UUID idResposta;
	
	@Schema(example = "Não")
	private String descricao;
	
	@Schema(example = "2")
	private Integer ordem;
	
	@Schema(example = "Null")
	private String mensagemEncaminhamento;
}
