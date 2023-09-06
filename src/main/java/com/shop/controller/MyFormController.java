package com.shop.controller;

import com.shop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/members")
@Controller
@RequiredArgsConstructor
public class MyFormController {
    private final MemberService memberService;
}
