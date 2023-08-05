package br.edu.ifsp.spo.ctpacolhe.common.exception;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ProblemException {
	
	@Schema(example = "a43ccbd5-e1e5-40cb-bc6c-ffb5d4898d6e")
	private String logRef;
	
	@Schema(example = "Ocorreu um erro na solicitação")
	private String message;

}
