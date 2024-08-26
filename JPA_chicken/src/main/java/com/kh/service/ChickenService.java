package com.kh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

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

	//치킨 메뉴 상세보기
	public Chicken findById(Integer id) {
		return chickenRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("일치정보없음"));
	}
	//findById를 작성할 때는 아이디를 찾지 못할 예외상황을 필수로 작성해줘야함
	//-> .orElseThrow() 예외상황 작성

	public Chicken updateChicken(Integer id, Chicken uc) {
		Chicken chicken = chickenRepository.findById(id)
				.orElseThrow(()->new RuntimeException("수정할치킨id찾을수없음"));
		//치킨객체에 수정된 치킨이름 가져와서 넣어주기
		chicken.setChickenName(uc.getChickenName());
		chicken.setDescription(uc.getDescription());
		chicken.setPrice(uc.getPrice());
		
		return chickenRepository.save(chicken);
	}
	
	//치킨 메뉴 삭제하기
	public void deleteChicken(Integer id) {
		Chicken c = chickenRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("삭제할치킨id찾을수없음"));
		chickenRepository.delete(c);
	}
	
	
	//치킨검색기능
	public List<Chicken> searchChickens(String query){
		return chickenRepository.findByChickenNameContainingIgnoreCase(query);
	}

}
