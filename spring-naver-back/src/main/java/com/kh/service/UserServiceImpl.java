package com.kh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.dto.User;
import com.kh.mapper.UserMapper;

@Service//서비스기능을 상세하게 작성해주는 공간
public class UserServiceImpl implements UserService {
	/*
	implements로 상속받는 인터페이스 서비스는
	기능에 대한 목록이 작성되어있을뿐
	상세 기능에 대해 작성된 것이 아님
	상속을 받은 클래스는 interface에 적어놓은 각각의 목록들의 기능을 설정해줘야 함
	설정 안해주면 에러
	*/
	
	@Autowired
	UserMapper userMapper;
	
	@Override
	public List<User> findAll(){
		return userMapper.findAll();
	}
	
	@Override
	public void insertUser(User user) {
		userMapper.insertUser(user);
	}
	
	@Override
	public void deleteUser(int id) {
		userMapper.deleteUser(id);
	}
	
	@Override
	public void updateUser(User user) {
		userMapper.updateUser(user);
	}

}
