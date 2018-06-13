package com.collegeportal.controller;


import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
	private static final Logger logger = LogManager.getLogger(LoginController.class);
	

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
