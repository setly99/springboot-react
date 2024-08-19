package com.kh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.service.ApiService;

/*
공공데이터 API 활용 컨트롤러

*/

@RestController
public class APIController {
	
	@Autowired
	private ApiService apiService;
	
	@GetMapping("/api/dataService")
	public String dataApi() {
		String endpoint = "/endaddress";
		return apiService.getApiData(endpoint);
	}
	
}
