package com.kh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kh.dto.NaverUser;
import com.kh.service.NaverUserService;

//네이버로 회원가입 후 DB에 회원가입 정보를 등록하는 컨트롤러

@RestController
public class NaverRegistController {
	
	@Autowired
	private NaverUserService naverUserService;
	
	//회원가입을 위한 Post Mapping 작성
	@PostMapping("/signup/naver")
	public String insertNaverUser(@RequestBody NaverUser naverUser) {
		
		//DB에 React로 가져온 naverUser정보를 수정없이 전체 넣겠다
		naverUserService.insertNaverUser(naverUser);
		
		//naverUserService.insertNaverUser(null);
		// null이 들어갈 자리에는 React에서 받아온 값을 넣어주는 공간
		//처음에는 Java에서 어떤 값을 넣어줘야 할지 모르기 때문에 null로 설정
		//null 자리에는 @RequestBody나 @RequestParam으로 가져온 값 작성
		//@RequestBody : 전체를 한번에 집어넣음 -  부분수정,커스텀필요하지 않을경우
		//@RequestParam : 부분수정, 부분추가할때 사용
		return "Naver API를 활용한 회원가입 성공";
	}

}
