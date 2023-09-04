package com.busanit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.busanit.domain.JoinVO;

@Controller
public class Hello_controller2 {
	
	
	
	@RequestMapping("/callHello2")
	public String callHello() {
		return "callHello2";
	}
	
	@RequestMapping("/sayHello4")
	public String sayHello(String id, String passwd, @ModelAttribute("join") JoinVO join) {
		return "sayHello4";
	}
	
	@RequestMapping("/sayHello5")
	public String sayHello2(int id, int passwd) {
		try {
			throw new Exception();			//강제적으로 예외를 발생
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "sayHello";
	}
}
