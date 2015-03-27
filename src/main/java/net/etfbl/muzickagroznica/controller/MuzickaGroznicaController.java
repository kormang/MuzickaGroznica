package net.etfbl.muzickagroznica.controller;

import java.beans.PropertyEditorSupport;
import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;

import net.etfbl.muzickagroznica.util.StandardUtil;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

public class MuzickaGroznicaController {

	public MuzickaGroznicaController() {
		// TODO Auto-generated constructor stub
	}
	
	@InitBinder
	public void dateBinder(WebDataBinder binder){
		
		binder.registerCustomEditor(java.util.Date.class, new PropertyEditorSupport(){
			@Override
			public String getAsText() {
				return super.getAsText();
			}
			
			@Override
			public  void setAsText(String value){
				try{
					long l = Long.parseLong(value);
					setValue(new java.util.Date(l));
				}catch (Exception ex){
					setValue(null);
				}
			}
		});
	}

}
