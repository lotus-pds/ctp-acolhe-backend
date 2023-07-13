package br.edu.ifsp.spo.ctpacolhe.dto;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RespostaDTO {
	private UUID idResposta;
	private Integer ordem;
	private String descricao;
}
