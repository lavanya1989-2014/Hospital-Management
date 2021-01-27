package com.hospital.mgt.ctl;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hospital.mgt.dto.RoleDTO;
import com.hospital.mgt.dto.UserDTO;
import com.hospital.mgt.form.ForgetPasswordForm;
import com.hospital.mgt.form.LoginForm;
import com.hospital.mgt.form.UserRegistrationForm;
import com.hospital.mgt.service.RoleServiceInt;
import com.hospital.mgt.service.UserServiceInt;



/** LoginCtl Implementation
 * @author Navigable Set
 * @Version 1.0
 */

@Controller
@RequestMapping(value="/Login")
public class LoginCtl extends BaseCtl {

	protected static final String OP_SIGNIN = "SignIn";
	protected static final String OP_SIGNUP = "SignUp";
	protected static final String OP_LOGOUT = "Logout";

	@Autowired
	private UserServiceInt service;

	@Autowired
	private RoleServiceInt roleService;

	@Autowired
	private MessageSource messageSource;

	@RequestMapping(method = RequestMethod.GET)
	public String display( @ModelAttribute("form") LoginForm form, HttpSession session, Model model, Locale locale) {

		System.out.println("In doget LoginCtl");
		
		if (session.getAttribute("userLogin") != null) {
		

			session.invalidate();
			
			String msg = messageSource.getMessage("message.logout", null, locale);
			
			model.addAttribute("success", msg);
		}
		return "Login";

	}

	
	
	
	@RequestMapping( method = RequestMethod.POST)
	public String display(Locale locale, @RequestParam String operation, HttpSession session,
			@ModelAttribute("form") @Valid LoginForm form, BindingResult result, Model model) {

		System.out.println("In dopost  LoginCtl");

		if (result.hasErrors()) {
			return "Login";
		}

		UserDTO dto = service.authenticate((UserDTO) form.getDto());

		if(dto !=null){
		session.setAttribute("userLogin", dto);
		session.setAttribute("roleLogin", roleService.findByPK(dto.getRoleId()));

		return "redirect:/Welcome";
		
		
		}
		
		String msg = messageSource.getMessage("message.invalidLoginIdAndPassword", null, locale);
		
		model.addAttribute("error", msg);
		return "Login";
	}



	@RequestMapping(value = "/SignUp", method = RequestMethod.GET)
	public String display(@ModelAttribute("form") UserRegistrationForm form, Model model) {
		System.out.println("in signup");

		Map<String, String> genderMap = new LinkedHashMap<String, String>();
		genderMap.put("Female", "Female");
		genderMap.put("Male", "Male");
		model.addAttribute("genderMap", genderMap);

		return "UserRegistration";
	}

	@RequestMapping(value = "/SignUp", method = RequestMethod.POST)
	public String submit(Locale locale, @ModelAttribute("form") @Valid UserRegistrationForm form,
			BindingResult bindingResult, Model model) {

		System.out.println("signup post ");
		if (OP_RESET.equalsIgnoreCase(form.getOperation())) {
			return "redirect:/Login/SignUp";
		}

		if (OP_SIGNUP.equalsIgnoreCase(form.getOperation())) {

			
			Map<String, String> genderMap = new LinkedHashMap<String, String>();
			genderMap.put("Female", "Female");
			genderMap.put("Male", "Male");
			model.addAttribute("genderMap", genderMap);

			
			if (bindingResult.hasErrors()) {
				return "UserRegistration";
			}
			UserDTO dto = (UserDTO) form.getDto();
			dto.setRoleId(RoleDTO.PATIENT);
			service.registerUser(dto);
			
			model.addAttribute("success", messageSource.getMessage("message.signUp", null, locale));
			return "UserRegistration";

		}	
		return "UserRegistration";
	}

	@RequestMapping(value = "/ForgetPassword", method = RequestMethod.GET)
	public String display(@ModelAttribute("form") ForgetPasswordForm form, HttpSession session, Model model) {

		System.out.println("In doget LoginCtl forgetpassword");

		return "ForgetPassword";

	}
	
	


	@RequestMapping(value = "/ForgetPassword", method = RequestMethod.POST)
	public String display(Locale locale, @ModelAttribute("form") @Valid ForgetPasswordForm form,
			BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {
			return "ForgetPassword";
		}

		UserDTO dto = service.findByLogin(form.getUserName());

		if (dto == null) {
			String msg = messageSource.getMessage("message.forgetpassworderror", null, locale);
			model.addAttribute("error", msg);
		}

		if(dto!=null){
		service.forgetPassword(form.getUserName());
		
		
		String msg = messageSource.getMessage("message.success.forgetpassword", null, locale);
		model.addAttribute("success", msg);
		
	//	model.addAttribute("success", "Password has been sent to your registered Email ID!!");
		}
		return "ForgetPassword";
	}

}
