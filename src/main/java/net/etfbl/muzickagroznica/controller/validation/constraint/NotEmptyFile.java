package net.etfbl.muzickagroznica.controller.validation.constraint;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;

import javax.validation.Constraint;
import javax.validation.Payload;

import net.etfbl.muzickagroznica.controller.validation.NotEmptyFileValidator;

@Documented
@Constraint(validatedBy = NotEmptyFileValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface NotEmptyFile {
	  	String message() default "{NotEmptyFile}";
      
	    Class<?>[] groups() default {};
	      
	    Class<? extends Payload>[] payload() default {};
}
