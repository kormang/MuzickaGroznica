package net.etfbl.muzickagroznica.controller;

import java.beans.PropertyEditorSupport;
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
