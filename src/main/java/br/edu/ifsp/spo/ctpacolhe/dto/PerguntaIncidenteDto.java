package br.edu.ifsp.spo.ctpacolhe.dto;
import java.util.List;

import br.edu.ifsp.spo.ctpacolhe.common.constant.TipoResposta;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PerguntaIncidenteDto {
	private String descricao;
	private Integer ordem;
	private TipoResposta tipoResposta;
	private List<RespostaIncidenteDto> respostas;
}
