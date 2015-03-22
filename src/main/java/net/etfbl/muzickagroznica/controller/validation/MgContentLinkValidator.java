package net.etfbl.muzickagroznica.controller.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import net.etfbl.muzickagroznica.controller.validation.constraint.MgContentLink;

public class MgContentLinkValidator implements ConstraintValidator<MgContentLink, String>{

	public MgContentLinkValidator() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initialize(MgContentLink arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isValid(String link, ConstraintValidatorContext ctx) {
		if(link == null || link.trim().isEmpty()){
			return false;
		}
		
		return true;
	}

}
