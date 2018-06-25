package com.collegeportal.controller;


import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.collegeportal.entity.UserRole;
import com.collegeportal.service.UserService;

@Controller
public class LoginController {
	private static final Logger logger = LogManager.getLogger(LoginController.class);
	
	@Autowired
	private UserService userService;
	
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
		if(SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof User) {
			return new ModelAndView("redirect:/homePage");
		}

		model.setViewName("index");
		return model;
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
			UserRole role = new UserRole("USER",newUser);
			List<UserRole> roles = new ArrayList<>();
			roles.add(role);
			newUser.setUserRoles(roles);
			
			
			if(userService.saveAndUpdate(newUser)) {
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
