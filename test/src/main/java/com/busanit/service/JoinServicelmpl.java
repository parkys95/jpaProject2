package com.busanit.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import com.busanit.domain.JoinVO;
import com.busanit.mapper.JoinMapper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
@AllArgsConstructor
public class JoinServicelmpl implements JoinService {

	
	private JoinMapper mapper;
	
	

	@Override
	public void register(JoinVO join) {
		log.info("register: " + join);
		mapper.insert(join);

	}

	
	@Override
	public List<JoinVO> getList() {
		log.info("getList");
		
		return mapper.getList();
	}

	
	@Override
	public boolean modify(JoinVO join) {
		log.info("modify");
		
		return mapper.update(join) == 1;
	}

	
	@Override
	public boolean remove(String id) {
		log.info("remove");
		
		return mapper.delete(id) == 1;
	}


	@Override
	public JoinVO get(String id) {
		
		return mapper.read(id);
		
	}


	@Override
	public JoinVO get(String id, String passwd) {
		// TODO Auto-generated method stub
		return null;
	}




}
