package com.busanit.service;

import org.springframework.stereotype.Service;

import com.busanit.domain.register_VO;
import com.busanit.mapper.JoinMapper;
import com.busanit.mapper.registerMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
@AllArgsConstructor
public class registerServicempl implements registerService {
	
	private registerMapper mapper;

	@Override
	public register_VO get(String USERID, String USERPW) {
		return null;
	}

	@Override
	public register_VO loguin(String USERID, String USERPW) {
		// TODO Auto-generated method stub
		return null;
	}

}
