package com.busanit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.busanit.domain.JoinVO;
import com.busanit.service.JoinService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/join/*")
@AllArgsConstructor
public class JoinController {
	
	private JoinService service;
	
	//로그인
	@GetMapping("/login")
	public String Login()//@RequestParam String id, @RequestParam String passwd 
							//@ModelAttribute JoinVO, Model model) {	
	{
						
		return "/login/loginForm";
	}
	
	//
	
	//리스트 조회
	@GetMapping("/list")
	public String list(Model model) {
		model.addAttribute("list" , service.getList());	//인터페이스 호출 - get list를 찾아가 내용실행
		
		return "/joinTest/list";
	}
	
	// 등록 화면
	@GetMapping("/register")
	public String joinForm() {
		return "/joinTest/joinForm";
	}
	
	//등록 처리
	@PostMapping("/register")
	public String join(JoinVO join) {
		log.info("register(post) : " + join);
		
		service.register(join);
		
		return "redirect:/join/list";
	}
	
	//상세보기
	@GetMapping("/get")
	public String get(@RequestParam String id, Model model) {
		log.info("/get");
		
		//상세 정보 조회
		model.addAttribute("join", service.get(id));
		
		return "/joinTest/get";
	}
	
	//수정화면
	@GetMapping("/modify")
	public String modifyForm(@RequestParam String id, Model model) {
		log.info("/modify");
		
		//상세정보 조회
		model.addAttribute("join", service.get(id));
		return "/joinTest/modify";
	}
	
	//수정 처리
	@PostMapping("/modify")
	public String modify(JoinVO join) {
		log.info("modify: " + join);
		
		service.modify(join);
		
		return "redirect:/join/list";
	}
	
	//삭제처리
	@PostMapping("/remove")
	public String remove(@RequestParam String id) {
		log.info("remove: " + id);
		
		service.remove(id);
		
		return "redirect:/join/list";
	}
}


