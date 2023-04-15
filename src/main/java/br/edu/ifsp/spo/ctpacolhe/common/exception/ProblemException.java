package br.edu.ifsp.spo.ctpacolhe.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ProblemException {
	private String logRef;
	private String message;

}
