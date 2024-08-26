package com.kh.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kh.dto.Chicken;

//mybatis - mapper 설정
//@Repository, @Mapper : interface
@Repository
public interface ChickenRepository extends JpaRepository<Chicken, Integer>{
	//전체보기 전체넣기 전체수정 전체삭제 - JpaRepository안에 모두 들어있음
	
	//select * from
	
	//특정 값을 찾을 때 쓰는 기능
	//findById(Integer id); -> where 대신 find
	
	
	
	//검색은 sql문이 예외적이기 때문에 작성필요
	/*
	Containing 부분일치 허용 Like %
	IgnoreCase 대소문자 구분없이 검색
	*/
	List<Chicken> findByChickenNameContainingIgnoreCase(String query);
	
}
