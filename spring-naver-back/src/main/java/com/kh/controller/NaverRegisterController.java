package com.kh.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
/*24-07-29 react - spring 프레임워크 연동을 위한 컨트롤러
 * OAuthController와 api url주소가 동일해서 나타나는 충돌을 막기위해
 * @RequestMapping("/api")주석 풀어서
 * 모든 url앞에
 * api붙도록
 * 
 */
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class NaverRegisterController {
	
	@Value("${naver.client-id}")
	private String clientId;
	
	@Value("${naver.client-secret}")
	private String clientSecret;
	
	
	@Value("${naver.redirect-uri}")
	private String redirectUri;
	
	@Value("${naver.state}")
	private String state;
	
	@GetMapping("/naverLogin")
	public String naverLogin() {
		String api_url = "https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=" + clientId + "&redirect_uri=" + redirectUri + "&state=" + state;
		return "<a href='"+ api_url + "'><img height='50' src='http://static.nid.naver.com/oauth/small_g_in.PNG'/></a>";
	}

	
	@GetMapping("/callback")
	public ResponseEntity<String> callback(@RequestParam("code") String code, @RequestParam("state") String state) {
		
		String api_url = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code&client_id="
			     + clientId + "&client_secret=" + clientSecret + "&redirect_uri=" + redirectUri + "&code=" + code + "&state=" + state;
		RestTemplate restTemplate = new RestTemplate();
		String response = restTemplate.getForObject(api_url, String.class);
		System.out.println("응답결과 : " + response);
		
		String accessToken = getToken(response);
		
		//여기서 응답에 대한 결과를 전달 -> 나중에 프로젝트 합칠 때는 지울 주소
		String redirectUrl = "http://localhost:3000/userinfo?access_token="+accessToken;
		HttpHeaders headers = new HttpHeaders();
		headers.add("Location", redirectUrl);
		return new ResponseEntity<>(headers,HttpStatus.FOUND);//front에 제대로 전달했는지체크
	}
	
	
	//보호 개인적으로 인증받은 토큰 가지고 전달하는 기능
	private String getToken(String res) {//extractAccessToken 기능이름 설정해주는 것이 좋음
		ObjectMapper om = new ObjectMapper();
		JsonNode jsonNode;
		try {
			jsonNode = om.readTree(res);
			return jsonNode.get("access_token").asText();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}//Node Tree
		
	}
	
	//나중에 callback에서 가져온 유저정보가 보이는 주소
	@GetMapping("/userinfo")
	public ResponseEntity<String> getUserInfo(@RequestParam("access_token") String accessToken){
		String apiURL = "https://openapi.naver.com/v1/nid/me"; //유저정보가담긴url -빈칸이어서오류났음
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer " + accessToken);
		headers.set("Accept", "application/json");//가져온 데이터를 json글자데이터로전달
		
		HttpEntity<String> entity = new HttpEntity<>(headers);
		//entity까지 무사히넘어오는지 확인
		System.out.println("Entity : " + entity);
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> res = restTemplate.exchange(apiURL, HttpMethod.GET, entity, String.class);
		
		System.out.println("Response Status Code : " + res.getStatusCode());//네이버로그인 성공실패여부
		System.out.println("Response Headers : " + res.getHeaders());//자격인증이 어떻게들어갔는지
		System.out.println("Response Body : " + res.getBody());//가져온로그인정보출력
		
		//만약에 성공적으로 넘어갔다면 성공적으로 넘어갔다는 표시가 있지만
		//성공적으로 넘어가지 않았다면 넘어가지 않은 이유 출력
		if(!res.getStatusCode().is2xxSuccessful()) {
			System.err.println("fail Status code : " + res.getStatusCode());
		}
		return res;
	}
	
}
