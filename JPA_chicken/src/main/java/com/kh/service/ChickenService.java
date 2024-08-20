package com.kh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.kh.dto.Chicken;
import com.kh.repository.ChickenRepository;

@Service
public class ChickenService {
	
	@Autowired
	private ChickenRepository chickenRepository;
	
	//치킨테이블 모두보기 List
	//List<Chicken>
	public List<Chicken> getAllChickens(){
		return chickenRepository.findAll();
	}
	
	//치킨 메뉴 추가하기
	public Chicken createChicken(Chicken chicken) {
		return chickenRepository.save(chicken);//치킨에대해 dto작성된 컬럼들에 모두 삽입
	}

}
