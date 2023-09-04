package com.busanit.Sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.Setter;

/**
 * 	Component 어노테이션은 스프링에게 해당 클래스가 스프링에서 관리해야 하는 대상임을 표시함
 *  Date 어노테이션은 ToString / EqulalsAndHashCode/ Getter,Setter /
 *  			  RequiredArgsConstructor 어노테이션을 모두 결합한 형태
 */

@Component
@Data
public class Restaurant {
//스프링 의존서 주입 방식은 크게 1.생성자 주입 /  2.Setter 주입 방식으로 나누어짐
// Setter 주입 방식 : Setter 어노테이션 이용
// @Setter(onMethod_ = @Autowired) 
//------------------------------------------or
//	@Autowired 
//	private Chef chef;

	
	
//생성자 주입 방식: private 이용 (권장 방법)

	private Chef chef;
	

	public Restaurant(Chef chef) {
		this.chef = chef;
		chef.show();
	}
	
	
}
