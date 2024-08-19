package com.kh.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ApiService {
	//@Value 활용해서 application.properties에 작성한 값 가져오기
	
	//Value 활용해서 api.base-url과 api.key api.content-type 가져오기
	@Value("${api.base-url}")
	private String baseUrl;
	
	@Value("${api.key}")
	private String apiKey;
	
	@Value("${api.content-type}")
	private String contentType;
	
	@Autowired
	private RestTemplate restTemplate;
	
	//공공데이터를 가져오기 위한 환경설정 서비스
	public String getApiData(String endpoint) {//상세주소
		// url = 시작주소 + 상세주소
		String url = baseUrl + endpoint; // 시작주소 + api끝나는지점
		
		HttpHeaders header = new HttpHeaders();
		header.set("Content-Type", contentType);
		header.set("Authorization", "Bearer " + apiKey);
		
		HttpEntity<String> entity = new HttpEntity<>(header);
		
		ResponseEntity<String> response = restTemplate.exchange(
				url, HttpMethod.GET, entity, String.class);
		
		return response.getBody();
	}
}
