package com.kh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kh.dto.NaverUser;
import com.kh.service.LoginService;

@RestController
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	@PostMapping("/login")
	//login @RequestParam -> @RequestBody Map<String,String>loginData
	public ResponseEntity<String> login(@RequestParam("id") String id, @RequestParam("password") String password) {
		NaverUser user = loginService.login(id, password);
		if(user != null) {
			return ResponseEntity.ok("로그인성공");
		}else {
			//ResponseEntity.status : db나 어떤 값에 대한 결과 상태
			//HttpStatus : Get Post와 같은 메서드 기능 잘 동작했나
			//unauthorized : 인증실패 주로 로그인실패
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					.body("로그인실패");
		}
				
	}

}
