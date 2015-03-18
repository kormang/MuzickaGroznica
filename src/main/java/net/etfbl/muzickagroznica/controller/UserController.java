package net.etfbl.muzickagroznica.controller;

import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import net.etfbl.muzickagroznica.form.bean.UserForm;
import net.etfbl.muzickagroznica.form.bean.UserPasswordForm;
import net.etfbl.muzickagroznica.form.bean.UserSettingsForm;
import net.etfbl.muzickagroznica.model.entities.User;
import net.etfbl.muzickagroznica.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	MessageSource messageSource;
	
	
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
	
	@RequestMapping(value = "/user/settings", method=RequestMethod.GET)
	public ModelAndView userSettings(
			HttpSession session,
			Map<String, Object> model
			){
		User user = (User) session.getAttribute("user");
		
		ModelAndView ret = new ModelAndView("user_settings");
		
		UserSettingsForm userSettingsForm = new UserSettingsForm();
		userSettingsForm.setEmail(user.getEmail());
		userSettingsForm.setJmb(user.getJmb());
		userSettingsForm.setLastName(user.getLastName());
		userSettingsForm.setFirstName(user.getFirstName());
		userSettingsForm.setUsername(user.getUsername());
		
		model.put("userSettingsForm", userSettingsForm);
				
		return ret;
	}
	
	@RequestMapping(value = "/user/settings", method=RequestMethod.POST)
	public String changeUserSettings(
			@Valid @ModelAttribute("userSettingsForm") UserSettingsForm userSettingsForm,
			BindingResult result,
			Locale local,
			HttpSession session
			){
		
		User user = (User) session.getAttribute("user");
		
		if(result.hasErrors()){
			return "user_settings";
		}
				
		user.setJmb(userSettingsForm.getJmb());
		user.setEmail(userSettingsForm.getEmail());
		user.setFirstName(userSettingsForm.getFirstName());
		user.setLastName(userSettingsForm.getLastName());
		
		User merged = userService.updateUserData(user, userSettingsForm.getOldPassword());
		if(merged == null){
			//update user in session
			user = userService.findUser(user.getId());
			session.setAttribute("user", user);
			
			result.addError(new FieldError("userSettingsForm", "oldPassword", messageSource.getMessage("PasswordMatch.userSettingsForm.oldPassword", null, local)));
			
			//
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				//e.printStackTrace();
			}
			
			return "user_settings";
		}
		
		return "redirect:/user/settings";
	}
	
	@RequestMapping(value = "/user/settings/password", method=RequestMethod.GET)
	public ModelAndView userPassword(){
		
		ModelAndView ret = new ModelAndView("change_password");
		
		//wrong name to choose, but made one day easier
		UserPasswordForm userForm = new UserPasswordForm();
		
		ret.addObject("userForm", userForm);
		
		return ret;
		
	}
	
	@RequestMapping(value = "/user/settings/password", method=RequestMethod.POST)
	public String changeUserPassword(
			@Valid @ModelAttribute("userForm") UserPasswordForm userForm,
			BindingResult result,
			HttpSession session,
			Locale local
			){
		
		if(result.hasErrors()){
			return "change_password";
		}
		
		User user = (User) session.getAttribute("user");
		
		User merged = userService.changeUserPassword(
				user,
				userForm.getRawPassword(),
				userForm.getOldPassword()
		);
		
		if(merged == null){
			//update user in session
			user = userService.findUser(user.getId());
			session.setAttribute("user", user);
			
			result.addError(new FieldError("userForm", "oldPassword", messageSource.getMessage("PasswordMatch.userSettingsForm.oldPassword", null, local)));
			
			//
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				//e.printStackTrace();
			}
			
			return "change_password";
		}
		
		
		return "redirect:/user/settings/password";
	}
	

}
