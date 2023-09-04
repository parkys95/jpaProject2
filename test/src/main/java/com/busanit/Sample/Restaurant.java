package com.busanit.Sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.Setter;

/**
 * 	Component ������̼��� ���������� �ش� Ŭ������ ���������� �����ؾ� �ϴ� ������� ǥ����
 *  Date ������̼��� ToString / EqulalsAndHashCode/ Getter,Setter /
 *  			  RequiredArgsConstructor ������̼��� ��� ������ ����
 */

@Component
@Data
public class Restaurant {
//������ ������ ���� ����� ũ�� 1.������ ���� /  2.Setter ���� ������� ��������
// Setter ���� ��� : Setter ������̼� �̿�
// @Setter(onMethod_ = @Autowired) 
//------------------------------------------or
//	@Autowired 
//	private Chef chef;

	
	
//������ ���� ���: private �̿� (���� ���)

	private Chef chef;
	

	public Restaurant(Chef chef) {
		this.chef = chef;
		chef.show();
	}
	
	
}
