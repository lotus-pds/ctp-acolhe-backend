package br.edu.ifsp.spo.ctpacolhe.common.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.edu.ifsp.spo.ctpacolhe.common.annotation.Password;

public class PasswordValidator implements ConstraintValidator<Password, String> {
	@Override
    public void initialize(Password constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(value.isBlank()) {
            return false;
        }
        return value.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[\\W_]).{8,64}$");
	}
}
