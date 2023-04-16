package br.edu.ifsp.spo.ctpacolhe.common.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.edu.ifsp.spo.ctpacolhe.common.annotation.Name;

public class NameValidator implements ConstraintValidator<Name, String> {
	@Override
    public void initialize(Name constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(value.isBlank()) {
            return false;
        }
        return value.matches("^[a-zA-ZáàâãéèêíóôõúçñÁÀÂÃÉÈÍÓÔÕÚÇ'\\-`\\s]+ [a-zA-ZáàâãéèêíóôõúçñÁÀÂÃÉÈÍÓÔÕÚÇ'\\-`\\s]+$");
	}

}
