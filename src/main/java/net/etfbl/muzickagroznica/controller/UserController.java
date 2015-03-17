package net.etfbl.muzickagroznica.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import net.etfbl.muzickagroznica.form.bean.UserForm;
import net.etfbl.muzickagroznica.model.entities.User;
import net.etfbl.muzickagroznica.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class UserController {
	
	@Autowired
	UserService userService;
	
	public UserController() {
		// TODO Auto-generated constructor stub
		System.err.println("------>>> KONSTRUISAN KONTROLER!!!");
	}
	
	@RequestMapping(value = "/reg_success", method=RequestMethod.GET)
	public String viewRegSuccess(HttpSession session) {

		if(session.getAttribute("regSuccess") != null){
			return "reg_success";
		}
		
		return "404";
		
	}
	
	@RequestMapping(value = "/register", method=RequestMethod.GET)
	public String viewRegistration(Map<String, Object> model) {
		UserForm userForm = new UserForm();
		model.put("userForm", userForm);
		return "registration";
	}
	
	@RequestMapping(value = "/register", method=RequestMethod.POST)
	public String doRegistration(
			HttpSession session,
			@Valid @ModelAttribute("userForm") UserForm userForm,
			BindingResult result, Map<String, Object> model
			){
		
		if(result.hasErrors()){
			return "registration";
		}
		
		
		User user = new User();
		
		user.setUsername(userForm.getUsername());
		user.setEmail(userForm.getEmail());
		user.setFirstName(userForm.getFirstName());
		user.setLastName(userForm.getLastName());
		user.setJmb(userForm.getJmb());

		userService.registerNewUser(user, userForm.getRawPassword());
		
		session.setAttribute("regSuccess", true);
		return "redirect:/reg_success";
	}
	
	
	

}
