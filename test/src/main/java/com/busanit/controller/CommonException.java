package com.busanit.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice("com.busanit.domain") //특정 패키지 예외처리
public class CommonException {
	//해당 controller에서 예외가 발생한 경우 catcher()가 예외처리를 해줌
		@ExceptionHandler(Exception.class)
		public String catcher(Exception ex, Model model ) {
			model.addAttribute("ex", ex);
			return "error";
		}
}
