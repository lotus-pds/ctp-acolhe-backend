package br.edu.ifsp.spo.ctpacolhe.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.NoArgsConstructor;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
@NoArgsConstructor
public class ValidationException extends RuntimeException {

	private static final long serialVersionUID = -1385014778384799345L;
	
	public ValidationException(String message) {
		super(message);
	}
}
