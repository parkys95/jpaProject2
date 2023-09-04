package com.busanit.mapper;

import java.util.List;

import com.busanit.domain.JoinVO;

public interface JoinMapper {
	
	public void insert(JoinVO join);
	
	public void insertSelectKey(JoinVO join);
	
	public String Login(String id, String passwd);
	
	public List<JoinVO> getList();
	
	public JoinVO read(String id);
	
	public int update(JoinVO join);
	
	public int delete(String id);
	
}
