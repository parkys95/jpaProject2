package com.busanit.service;

import com.busanit.domain.register_VO;

public interface registerService {
	//로그인
	public register_VO get(String USERID, String USERPW);
	
	public register_VO loguin(String USERID, String USERPW);
}
