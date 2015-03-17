package net.etfbl.muzickagroznica.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

	public LoginController() {
		// TODO Auto-generated constructor stub
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(Model model,
			@RequestParam(value = "failure-cause", required = false)String failure){
		
		ModelAndView ret = new ModelAndView("login");
		
		if(failure != null){
			model.addAttribute(failure, true);
		}
		
		return ret;
	}

}
