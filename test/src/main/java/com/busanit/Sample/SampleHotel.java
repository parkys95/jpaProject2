package com.busanit.Sample;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Component
@ToString
@Getter
//@AllArgsConstructor
@RequiredArgsConstructor // - @NonNull, finale 이 붙은 인스턴스 변수에 대한 생성자를 만들어냄 

public class SampleHotel {
	
	private Chef chef;	// = private finale Chef chef;
	
}
