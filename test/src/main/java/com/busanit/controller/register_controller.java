package com.busanit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.busanit.domain.register_VO;
import com.busanit.service.JoinService;

@Controller
public class register_controller {
	private Object register;

	@GetMapping("/register")
	public String register() {
		return "register";
	}
	
	@GetMapping("/register_result")	//Form의 메소드가 post면 PostMapping
	public String register_result(@ModelAttribute("r")register_VO register ) {
		return "register_result";
	}
	
	//db넣기
	@GetMapping("/register")
	public String register(Object service) {
		((register_controller) service).register(register);
		return "redirect:/registForm";
	}
	
	@GetMapping("/loginForm")
	public String loginForm() {
		return "loginForm_register";
	}
	
//	@PostMapping("/loginAction")
//	public String loginActio(String userid, String userpwd, Model model) {
//		register_VO registet = JoinService.login(userid);
//		
//		if(register == null) {
//			model.addAttribute("message", "해당 회원은 존재하지않습니다.");
//		}else if(userid.equals(registet.getUSERID()) &&
//				(userpw.equals(registet.getUSERPW()){
//			return "register_login_result";
//		}else {
//			model.addAttribute("messsage", "아이디나 비밀번호가 틀렸습니다.");
//		}
//		
//		return "redirect:/register_login_result";
//	}
}


