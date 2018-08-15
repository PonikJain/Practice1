package com.collegeportal.controller;


import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.collegeportal.dto.UserForm;
import com.collegeportal.entity.UserRole;
import com.collegeportal.enums.UserRoleType;
import com.collegeportal.security.service.SecurityUtil;
import com.collegeportal.service.UserService;

@Controller
public class LoginController {
	private static final Logger logger = LogManager.getLogger(LoginController.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ConnectionFactoryLocator connectionFactoryLocator;

	@Autowired
	private UsersConnectionRepository connectionRepository;
	
	@RequestMapping(value= {"/","/login"} , method= {RequestMethod.GET})
	public ModelAndView welcome(@RequestParam(value = "error",required = false) String error,
			@RequestParam(value = "logout",	required = false) String logout) {
		
		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Invalid Credentials provided.");
		}
		if (logout != null) {
			model.addObject("message", "Logged out successfully.");
		}
		
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context != null ? context.getAuthentication() : null;
		Object principal = authentication != null ? authentication.getPrincipal() : null;
		
		if(principal != null && principal instanceof User) {
			User user = (User)context.getAuthentication().getPrincipal();
			Collection<GrantedAuthority> authorities = user.getAuthorities();
			
			for(GrantedAuthority auth : authorities) {
				if(auth.getAuthority().equals(UserRoleType.USER.getValue())){
					return new ModelAndView("redirect:/homePage");
				}
			}
		}
		
		UserForm myForm = new UserForm();

		model.setViewName("index");
		model.addObject("myForm", myForm);
		return model;
	}
	
	@RequestMapping(value = { "/signup" }, method = RequestMethod.GET)
	   public String signupPage(WebRequest request, Model model) {
	 
	       ProviderSignInUtils providerSignInUtils = new ProviderSignInUtils(connectionFactoryLocator, connectionRepository);
	 
	  
	       Connection<?> connection = providerSignInUtils.getConnectionFromSession(request);
	 
	       UserForm myForm = null;
	       if (connection != null) {
	           myForm = new UserForm(connection);
	       } else {
	           myForm = new UserForm();
	       }
	       model.addAttribute("myForm", myForm);
	       return "index";
	   }
	
	@RequestMapping(value = { "/signup" }, method = RequestMethod.POST)
	   public String signupSave(WebRequest request,Model model,@ModelAttribute("myForm") UserForm userForm,
	           BindingResult result,
	           final RedirectAttributes redirectAttributes) throws ParseException {
	 
	       // If validation has error.
	       if (result.hasErrors()) {
	           return "signup";
	       }
	 
	       com.collegeportal.entity.User user = null;
	       
	       DateFormat s = new SimpleDateFormat("yyyy-MM-dd");
			Date birthDate = s.parse(userForm.getDob());
			
			BigInteger phoneNum = new BigInteger(userForm.getPhoneNumber());
			
			com.collegeportal.entity.User newUser = new com.collegeportal.entity.User();
			newUser.setFirstname(userForm.getFirstName());
			newUser.setLastname(userForm.getLastName());
			newUser.setEmail(userForm.getEmail());
			newUser.setState(userForm.getState());
			newUser.setDob(birthDate);
			newUser.setPhonenumber(phoneNum);
			newUser.setPassword(userForm.getPassword());
			
			//Default User Role access
			UserRole role = new UserRole(UserRoleType.USER.getValue(),newUser);
			List<UserRole> roles = new ArrayList<>();
			roles.add(role);
			newUser.setUserRoles(roles);
	 
	       try {
	           user = userService.saveAndUpdate(newUser);
	       } catch (Exception ex) {
	           ex.printStackTrace();
	           model.addAttribute("errorMessage", "Error " + ex.getMessage());
	           return "signup";
	       }
	 
	       if (userForm.getSignInProvider() != null) {
	           ProviderSignInUtils providerSignInUtils = new ProviderSignInUtils(connectionFactoryLocator, connectionRepository);
	 
	           // If the user is signing in by using a social provider, this method
	           // call stores the connection to the UserConnection table.
	           // Otherwise, this method does not do anything.
	           providerSignInUtils.doPostSignUp(String.valueOf(user.getUserId()), request);
	       }
	       // After register, Logs the user in.
	       SecurityUtil.logInUser(user);
	 
	       return "redirect:/homePage";
	   }
	
	
	@RequestMapping(value = { "/homePage"}, method = {RequestMethod.POST , RequestMethod.GET})
	public ModelAndView homePage() {
		logger.info("Home page called -----");
		ModelAndView model = new ModelAndView();
		model.setViewName("homePage");
		return model;
	}
	
	@RequestMapping(value = { "/register"}, method = {RequestMethod.POST})
	public ModelAndView register(@RequestParam String firstName,
								@RequestParam String lastName,
								@RequestParam String phoneNumber,
								@RequestParam String dob,
								@RequestParam String email,
								@RequestParam String state,
								@RequestParam String password){
		logger.info("Started Registration ---------------");
		String message = "";
		try {
			DateFormat s = new SimpleDateFormat("yyyy-MM-dd");
			Date birthDate = s.parse(dob);
			
			BigInteger phoneNum = new BigInteger(phoneNumber);
			
			com.collegeportal.entity.User newUser = new com.collegeportal.entity.User();
			newUser.setFirstname(firstName);
			newUser.setLastname(lastName);
			newUser.setEmail(email);
			newUser.setState(state);
			newUser.setDob(birthDate);
			newUser.setPhonenumber(phoneNum);
			newUser.setPassword(password);
			
			//Default User Role access
			UserRole role = new UserRole(UserRoleType.USER.getValue(),newUser);
			List<UserRole> roles = new ArrayList<>();
			roles.add(role);
			newUser.setUserRoles(roles);
			
			
			if(userService.saveAndUpdate(newUser) != null) {
				message = "You are successfully registered";
			}else {
				message = "Registration Process Failed ! Please try again";
			}
		
		}catch(ParseException e) {
			logger.error("Error while parsing the Date of Birth");
		}catch(Exception  e) {
			logger.error("Exception Occurend",e);
		}
		ModelAndView model = new ModelAndView();
		model.addObject("regMessage", message);
		model.setViewName("index");
		return model;
	}
	
	@RequestMapping(value ="/Access_Denied" , method= RequestMethod.GET)
	public ModelAndView nonAuthorised() {
		ModelAndView mv = new ModelAndView("error");
		
		Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", "301");
        map.put("reason", "You are Unauthorised to access the Page ...");
		mv.addAllObjects(map);
        return mv;
	}
	
}
