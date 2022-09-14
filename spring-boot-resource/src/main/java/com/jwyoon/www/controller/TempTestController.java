package com.jwyoon.www.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@Controller()
@RequestMapping("/api")
public class TempTestController {
	
	@GetMapping("/quiz")
	public String testPage() {
		ModelAndView modelAndView = new ModelAndView();
        
		
        return "quiz";
	}
	@ResponseBody
	@GetMapping("/hello")
	public String hello() {
		return "hello";
	}
}
