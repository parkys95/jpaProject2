package com.busanit.domain;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Hello_controller3 {
	
	
	
	@RequestMapping("/sayHello3")
	public String sayHello3(int id, int passwd) {
		try {
			throw new Exception();			//강제적으로 예외를 발생
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "sayHello";
	}
}
