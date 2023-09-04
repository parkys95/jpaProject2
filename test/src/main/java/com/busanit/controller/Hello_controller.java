package com.busanit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.busanit.domain.JoinVO;

@Controller
public class Hello_controller {
	
	//해당 controller에서 예외가 발생한 경우 catcher()가 예외처리를 해줌
	@ExceptionHandler(Exception.class)
	public String catcher(Exception ex) {
		return "error";
	}
	
	@RequestMapping("/callHello")
	public String callHello() {
		return "callHello";
	}
	
	@RequestMapping("/sayHello")
	public String sayHello(String id, String passwd, @ModelAttribute("join") JoinVO join) {
		return "sayHello";
	}
	
	@RequestMapping("/sayHello2")
	public String sayHello2(int id, int passwd) {
		return "sayHello";
	}
}
