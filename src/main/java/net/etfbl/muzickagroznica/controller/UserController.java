package net.etfbl.muzickagroznica.controller;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import net.etfbl.muzickagroznica.controller.utils.RequestParamsFinder;
import net.etfbl.muzickagroznica.form.bean.AvatarUploadForm;
import net.etfbl.muzickagroznica.form.bean.UserForm;
import net.etfbl.muzickagroznica.form.bean.UserPasswordForm;
import net.etfbl.muzickagroznica.form.bean.UserSettingsForm;
import net.etfbl.muzickagroznica.model.entities.Role;
import net.etfbl.muzickagroznica.model.entities.User;
import net.etfbl.muzickagroznica.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class UserController extends MuzickaGroznicaController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	MessageSource messageSource;
	
	@Autowired
	RequestParamsFinder paramsFinder;
	
	private static String[] roleNamesForChange = new String[] { "ROLE_SUPER", "ROLE_ADMIN" };
	
	
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
		
		session.setAttribute("user", merged);
		
		return "redirect:/user/settings";
	}
	
	@RequestMapping(value = "/user/settings/password", method=RequestMethod.GET)
	public ModelAndView userPassword(){
		
		ModelAndView ret = new ModelAndView("change_password");
		
		//wrong name to choose, but made one day easier
		UserPasswordForm userForm = new UserPasswordForm();
		
		ret.addObject("userPasswordForm", userForm);
		
		return ret;
		
	}
	
	@RequestMapping(value = "/user/settings/password", method=RequestMethod.POST)
	public String changeUserPassword(
			@Valid @ModelAttribute("userPasswordForm") UserPasswordForm userForm,
			BindingResult result,
			HttpSession session,
			Locale local
			){
		
		if(result.hasErrors()){
			return "change_password";
		}
		
		User user = (User) session.getAttribute("user");
		
		User merged = userService.changeUserPassword(
				user.getId(),
				userForm.getRawPassword(),
				userForm.getOldPassword()
		);
		
		if(merged == null){
		
			result.addError(new FieldError("userPasswordForm", "oldPassword", messageSource.getMessage("PasswordMatch.userSettingsForm.oldPassword", null, local)));
			
			//
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				//e.printStackTrace();
			}
			
			return "change_password";
		}
		
		//update session model
		session.setAttribute("user", merged);
		
		
		return "redirect:/user/settings/password";
	}
	
	@RequestMapping(value="/user/settings/avatar", method=RequestMethod.GET)
	public ModelAndView viewAvatar(){
		
		ModelAndView ret = new ModelAndView("avatar_upload");
		
		AvatarUploadForm avatarUploadForm = new AvatarUploadForm();
		ret.addObject("avatarUploadForm",avatarUploadForm);
		
		return ret;
	}
	
	@RequestMapping(value = "/user/settings/avatar", method=RequestMethod.POST)
	public String uploadAvatar(
			@Valid @ModelAttribute("avatarUploadForm") AvatarUploadForm avatarUploadForm,
			BindingResult result,
			HttpSession session
			){
		
		if(result.hasErrors()){
			return "avatar_upload";
		}
		
		MultipartFile file = avatarUploadForm.getFile();
		User user = (User) session.getAttribute("user");
		
		byte[] binary = null;
		
		try {
			binary = file.getBytes();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		
		User merged = userService.changeAvatarForUser(user.getId(), binary);
		if(merged == null){
			return "error";
		}

		session.setAttribute("user", merged);

		return "redirect:/user/settings/avatar";
	}
	
	@RequestMapping(value="/admin/activation_requests", method=RequestMethod.GET)
	public String adminViewActivationRequests(Map<String, Object> model){
		
		List<User> dausers = userService.findUsersWithDeactivatedAccount();
		model.put("users", dausers);
		
		return "admin/activation_requests";
	}
	
	@RequestMapping(value="/admin/activate_account", method=RequestMethod.POST)
	public String adminActivateAccount(HttpServletRequest request){
		
		try{
			int aid = paramsFinder.findIntParam(request, "aid");
			userService.activateUserAccount(aid);
		}catch(Exception ex){
			ex.printStackTrace();
		}
				
		return "redirect:/admin/activation_requests";
	}
	
	@RequestMapping(value="/admin/panel")
	public String adminPanel(){
		return "admin/panel";
	}
	
	@RequestMapping(value="/admin/role_switch", produces="application/json; charset=UTF-8")
	public @ResponseBody String roleSwitch(
			@RequestParam("uid") int uid,
			@RequestParam("role") int roleidx,
			@RequestParam("add") boolean add, 
			HttpServletResponse response,
			Locale local,
			HttpSession session
			){
			
		try {
			String roleName = roleNamesForChange[roleidx];
			
			if(add){
				userService.addRoleToUser(uid, roleName);
			} else {
				userService.removeRoleFromUser(uid, roleName);
			}
			
			
		} catch(RuntimeException ex) {
			ex.printStackTrace();
			String msg = messageSource.getMessage("admin.role_switch.error", null, local);
			return "{\"result\" : false, message: \"" + msg + "\"}";
		}
		
		String msg = messageSource.getMessage("admin.role_switch.success", null, local);
		return "{\"result\" : true, \"message\" : \"" + msg + "\"}";
		
	}
	
	@RequestMapping(value="/admin/users", method=RequestMethod.GET)
	public String adminUsers(Map<String, Object> model){
		
		List<User> users = userService.findActiveUsers();
		boolean[] superFlags = new boolean[users.size()];
		boolean[] adminFlags = new boolean[users.size()];
		
		int i = 0;
		for(User u : users){
			
			for(Role role : userService.findRolesForUser(u.getId())){
				
				if(role.getId().getRoleName().equals("ROLE_SUPER")){
					superFlags[i] = true;
					System.err.println("superFlags["+i+"]="+true);
				} else if (role.getId().getRoleName().equals("ROLE_ADMIN")){
					adminFlags[i] = true;
					System.err.println("adminFlags["+i+"]="+true);
				}
			}
			
			i++;
			
		}
		
		
		model.put("users", users);
		model.put("superFlags", superFlags);
		model.put("adminFlags", adminFlags);
		
		
		return "admin/users";
	}

}
