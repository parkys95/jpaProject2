package com.busanit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.busanit.domain.JoinVO;
import com.busanit.domain.frontend_VO;

@Controller
public class fronted_controller {
	
	@GetMapping("/frontend" )
	public String frontend() {
		return "frontend";
	}
	
	
	@PostMapping("/result")
	public String frontEndResult(@ModelAttribute("front")  frontend_VO front) {
		
		return "result";
		
	}
}
