package com.kh.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kh.dto.NaverUser;

//mybatis에 id값으로 작성한 sql 기능 목록을 작성
//기능에 대한 목록만 보기 때문에 interface
@Mapper
public interface NaverUserMapper {
	List<NaverUser> findAll();
	
	//네이버 sns 연동해서 회원가입하는 insert
	void insertNaverUser(NaverUser user);//service, serviceimpl, controller작성하기
	

}
