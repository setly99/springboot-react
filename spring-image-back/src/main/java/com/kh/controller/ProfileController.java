package com.kh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kh.dto.UserProfile;
import com.kh.service.ProfileService;


@RestController
@RequestMapping("/profile")
public class ProfileController {
	
	@Autowired
	private ProfileService service;
	//private ProfileServiceImpl profileServiceImpl;
	
	/*
	 * Autowired 안쓰면 작성해야하는 코드
	public ProfileController(ProfileService profileService) {
		this.service = profileService;
	}
	*/
	
	@GetMapping("/watching")
	public ResponseEntity<List<UserProfile>> getProfile(){
		return ResponseEntity.ok(service.getProfile());
	}
	
	@PostMapping("/insert")
	public ResponseEntity<String> insertProfile(UserProfile userProfile){
		
		service.insertProfile(userProfile);
		return ResponseEntity.ok("insert성공");
	}
	
	
	/*
	RequestParam() 안에 React에서 값 가져올 변수명 작성해야 함
	*/
	@PostMapping("/upload")
	public ResponseEntity<String> uploadProfile(
			@RequestParam("files") MultipartFile[] files, 
			@RequestParam("username") String username){
		service.uploadProfile(files, username);
		return ResponseEntity.ok("이미지업로드성공");
	}
	
	
}
