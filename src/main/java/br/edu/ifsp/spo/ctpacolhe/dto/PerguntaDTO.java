package br.edu.ifsp.spo.ctpacolhe.dto;
import java.util.List;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PerguntaDTO {
	private UUID idPergunta;
	private String descricao;
	private Integer ordem;
	private String idTipoResposta;
	private List<RespostaDTO> respostas;
}
