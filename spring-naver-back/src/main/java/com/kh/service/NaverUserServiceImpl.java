package com.kh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.dto.NaverUser;
import com.kh.mapper.NaverUserMapper;

@Service//서비스기능을 상세하게 작성해주는 공간
public class NaverUserServiceImpl implements NaverUserService {
		
	@Autowired
	NaverUserMapper userMapper;
	
	@Override
	public void insertNaverUser(NaverUser user) {
		userMapper.insertNaverUser(user);
	}
	

}
