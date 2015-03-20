package net.etfbl.muzickagroznica.controller.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.web.multipart.MultipartFile;

import net.etfbl.muzickagroznica.controller.validation.constraint.NotEmptyFile;



public class NotEmptyFileValidator implements ConstraintValidator<NotEmptyFile, MultipartFile>{

	public NotEmptyFileValidator() {
		// TODO Auto-generated constructor stub
		
	}

	@Override
	public void initialize(NotEmptyFile arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isValid(MultipartFile file, ConstraintValidatorContext ctx) {
		return ! file.isEmpty();
	}


}
