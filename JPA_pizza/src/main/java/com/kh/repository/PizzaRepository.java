package com.kh.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kh.entity.Kh_pizza;

//mapper + mybatis 합친 기능 : 기본으로 select insert 특정단어찾기
//JpaRepository 안에 작성이 되어있기 때문에
//검색과 같이 특수한 작업만 작성
//mapper 처럼 interface임
//mapper와 다른 점 : mybatis와 같은 기능을 포함한 interface
@Repository
public interface PizzaRepository extends JpaRepository<Kh_pizza, Integer>{
	//검색해서 여러개가 보이면 리스트
	//find : where : 어떤 컬럼 기준으로 검색? 피자명으로 검색
	//Containing : Like %word% : 특정 단어word 포함
	//Like %name : 문자열이 name으로 끝나는 모든 값
	//Like %name% : 문자열 중간에 name이 들어가는 모든 값
	//Like name% : 문자열 시작이 name인 모든 값
	//IgnoreCase : 대소문자 구분x
	List<Kh_pizza> findByNameContainingIgnoreCase(String query);//PizzaName -> Name으로 변경 (오류남)-service도마찬가지

}
