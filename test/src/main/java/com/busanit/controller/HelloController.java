package com.busanit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//@RestController: hello 값을 그대로 url 에게 전달하는 역할(jsp 파일을 찾지않음)
@RequestMapping("/hello/*")
	//void일땐 hello 폴더를 찾음   
	//String: 리턴이 있는 jsp 파일을 찾아 가게된다.
public class HelloController {
	
	
	//url: /hellow/helloprint
	@GetMapping("/helloPrint")
	public void helloPrint(Model model) {
	//String: url 경로 jsp파일의 이름과 경로를 나타날때 사용 
		
		model.addAttribute("message", "hello~!!!");
		
//		return "hello";
// String : return "/hello/helloprint"; 
		
	}
	
	
	//url: /hellow/helloprint2
		@GetMapping("/helloPrint2")
		public void hello2() {
		//void: 리턴값이 없고 자동적으로 이름이 같은 jsp 파일로 이동하게 된다.
			
			System.out.println("hello2");
			
		}
	
}
