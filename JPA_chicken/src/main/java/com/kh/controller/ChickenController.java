package com.kh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kh.dto.Chicken;
import com.kh.service.ChickenService;

@RestController
@RequestMapping("/api/chicken")
public class ChickenController {

	@Autowired//참조
	private ChickenService chickenService;
	
	@GetMapping
	public List<Chicken> getAllChickens(){
		return chickenService.getAllChickens();
	}
	
	@GetMapping("/{id}")
	public Chicken getChickenById(@PathVariable("id") Integer id) {
		return chickenService.findById(id);
	}
	
	@PostMapping
	public Chicken saveChicken(@RequestBody Chicken chicken) {
		return chickenService.createChicken(chicken);
	}
	
	/*
	1. 수정할 컬럼 아이디 가져오기
	2. 수정된 내용 저장하기
	*/
	
	@PutMapping("/{id}")
	public Chicken updateChicken(@PathVariable("id") Integer id, @RequestBody Chicken chicken) {
		return chickenService.updateChicken(id, chicken);
	}
	
	@DeleteMapping("{id}")
	public void deleteChicken(@PathVariable("id") Integer id) {
		chickenService.deleteChicken(id);
	}
	
	@GetMapping("/search")
	public List<Chicken> searchChickens(@RequestParam("query") String query){
		return chickenService.searchChickens(query);
	}
}
