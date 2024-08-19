package com.kh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.JpaRepositoryConfigExtension;

import com.kh.dto.BCUser;

/*
JpaRepository
mybatis mapper 생략해서 작성하는 방법
sql 알아서 작성
*/
public interface BCUserRepository extends JpaRepository<BCUser, Integer>{

	//save : select 특정 검색하지 않는 한 기본 sql 작성 x
	//BCUser saveUser();
	
	//이메일찾기기능
	BCUser findByEmail(String email);
	//->sql select * from BCUser where email = ? ;

}
