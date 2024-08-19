package com.kh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.service.ApiService;

@RestController
@RequestMapping("/api")
public class ApiController {
	
	@Autowired
	private ApiService apiService;
	
	@GetMapping("/air-pollution")
	public String getAirData() throws Exception {
		return apiService.getAirData();
	}
}
