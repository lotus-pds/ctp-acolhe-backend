package br.edu.ifsp.spo.ctpacolhe.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

import br.edu.ifsp.spo.ctpacolhe.common.validator.PasswordValidator;
import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ FIELD, METHOD, PARAMETER, ANNOTATION_TYPE, TYPE_USE })
@Retention(RUNTIME)
@Constraint(validatedBy = PasswordValidator.class)
@Documented
public @interface Password {
	String message() default "deve ter no mínimo uma letra maiúscula, uma minúscula, um número e um caractere especial";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
