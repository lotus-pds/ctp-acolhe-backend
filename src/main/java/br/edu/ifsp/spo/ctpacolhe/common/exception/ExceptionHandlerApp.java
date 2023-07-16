package br.edu.ifsp.spo.ctpacolhe.common.exception;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class ExceptionHandlerApp {
	
	@Autowired
	private MessageSource messageSource;	

	@ExceptionHandler(Throwable.class)
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<ProblemException> problem(final Throwable e) {
		String message = e.getMessage();

		message = "Ocorreu um erro na solicitação";
		UUID uuid = UUID.randomUUID();
		String logRef = uuid.toString();
		
		log.error("logRef=" + logRef, message, e);
		return new ResponseEntity<ProblemException>(new ProblemException(logRef, message),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ResponseEntity<CustomErrorMessage> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
		List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
		List<ObjectError> globalErrors = ex.getBindingResult().getGlobalErrors();
		List<String> errors = new ArrayList<>(fieldErrors.size() + globalErrors.size());

		String error;

		for (FieldError fieldError : fieldErrors) {
			error = fieldError.getField() + ": " + fieldError.getDefaultMessage();
			errors.add(error);
		}
		for (ObjectError objectError : globalErrors) {
			error = objectError.getObjectName() + ": " + objectError.getDefaultMessage();
			errors.add(error);
		}

		CustomErrorMessage errorMessage = new CustomErrorMessage(errors);
		return new ResponseEntity<CustomErrorMessage>(errorMessage, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ValidationException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ResponseEntity<CustomErrorMessage> handleValidacaoException(ValidationException ex, Locale locale) {
		List<String> errors = new ArrayList<>();
		
		String error = messageSource.getMessage(ex.getMessage(), null, locale);
		errors.add(String.format(error, ex.getParametros()));
		
		CustomErrorMessage errorMessage = new CustomErrorMessage(errors);
		
		return new ResponseEntity<CustomErrorMessage>(errorMessage, HttpStatus.BAD_REQUEST);
	}
}
