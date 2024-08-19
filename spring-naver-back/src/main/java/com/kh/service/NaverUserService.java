package com.kh.service;

import java.util.List;

import com.kh.dto.NaverUser;

//서비스 목록 리스트 여기는 목록만 작성 후 imple 에 오버라이드해서 각 환경에 맞게 재사용
public interface NaverUserService {

	//네이버 sns 연동해서 회원가입하는 insert
	void insertNaverUser(NaverUser user);

}
