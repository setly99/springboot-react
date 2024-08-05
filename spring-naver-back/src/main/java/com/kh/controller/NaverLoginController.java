package com.kh.controller;

import org.springframework.beans.factory.annotation.Value;
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

@RestController
@RequestMapping("/api")
public class NaverLoginController {
	/*
	@Value application.properties, config.properties에 작성한
	key이름을 가져오고 키에 담긴 값을 가져오는 어노테이션
	 */
	@Value("${naver.client-id}")
	private String clientId;
	
	@Value("${naver.client-secret}")
	private String clientSecret;
	
	//http://localhost:9010/naverLogin
	@Value("${naver.redirect-uri}")
	private String redirectUri;
	
	@Value("${naver.state}")
	private String state;
	
	@GetMapping("/naverLogin")//localhost:9010/api/naverLogin
	public String naverLogin() {
		String api_url = "https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=" + clientId + "&redirect_uri=" + redirectUri + "&state=" + state;
		return "<a href='"+ api_url + "'><img height='50' src='http://static.nid.naver.com/oauth/small_g_in.PNG'/></a>";
	}

	/*
	url에 {}= 변수명 표시가 없으면 @RequestParam 이나 @RequestBody
	url에 {}= 변수명 표시가 있으면 @PathVariable - {}안에있는 변수명에 값을 넣음
	 */
	@GetMapping("/callback")
	public String callback(@RequestParam String code, @RequestParam String state) {
		//네이버로그인 성공 후 받는 콜백
		//1. client-id 2. 비밀번호 3. uri(완료후위치) 4. code(들어옴 인증코드) 5. state(CSRF공격방지)
		String api_url = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code&client_id="
			     + clientId + "&client_secret=" + clientSecret + "&redirect_uri=" + redirectUri + "&code=" + code + "&state=" + state;
		
		//RestTemplate (http메서드)
		RestTemplate restTemplate = new RestTemplate();
		//api_url 주소를 응답받은 결과를 String으로 가져와서 사용
		String 응답결과 = restTemplate.getForObject(api_url, String.class);
		return 응답결과;
	}
	
}
