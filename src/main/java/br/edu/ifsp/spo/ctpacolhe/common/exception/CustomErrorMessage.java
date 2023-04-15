package br.edu.ifsp.spo.ctpacolhe.common.exception;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomErrorMessage {
	private List<String> errors;
	
}
