package br.edu.ifsp.spo.ctpacolhe.dto;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.edu.ifsp.spo.ctpacolhe.common.constant.TipoResposta;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@JsonInclude(Include.NON_NULL)
public class PerguntaDto {
	private UUID idPergunta;
	private String descricao;
	private Integer ordem;
	private Boolean obrigatoria;
	private TipoResposta tipoResposta;
	private List<RespostaDto> respostas;
}
