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
@RequiredArgsConstructor // - @NonNull, finale �� ���� �ν��Ͻ� ������ ���� �����ڸ� ���� 

public class SampleHotel {
	
	private Chef chef;	// = private finale Chef chef;
	
}
