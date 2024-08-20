package com.kh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kh.dto.Chicken;

//mybatis - mapper 설정
//@Repository, @Mapper : interface
@Repository
public interface ChickenRepository extends JpaRepository<Chicken, Integer>{
	//전체보기 전체넣기 전체수정 전체삭제 - JpaRepository안에 모두 들어있음
	
	//select * from
	
}
