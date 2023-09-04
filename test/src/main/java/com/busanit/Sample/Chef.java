package com.busanit.Sample;

import org.springframework.stereotype.Component;

import lombok.Data;

// ��(Bean) - ���������� �����ϴ� ��ü

@Component
@Data

public class Chef {
	public void show() {
		System.out.println("Chef ��ü ���");
	}
}
