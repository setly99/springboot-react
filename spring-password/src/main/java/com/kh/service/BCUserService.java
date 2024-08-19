package com.kh.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kh.dto.BCUser;
import com.kh.repository.BCUserRepository;

@Service
public class BCUserService {
	
	@Autowired
	private BCUserRepository bcUserRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	//패스워드 인코드 저장
	public void saveUser(BCUser bcUser) {
		bcUser.setPassword(passwordEncoder.encode(bcUser.getPassword()));
		//JpaRepository 안에 save이미 저장되어 있어서 interface작성안해도 불러와짐. 
		bcUserRepository.save(bcUser);
	}

}
