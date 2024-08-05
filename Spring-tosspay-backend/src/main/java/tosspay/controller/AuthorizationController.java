package tosspay.controller;

import java.util.Base64;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class AuthorizationController {

	//springboot에서 application.properties에 작성한 값을 가져오기 위해
	//@Value 어노테이션 사용
	//@Value("${}")
	@Value("${apiSecretKey}")
	private String apiSecretKey;//가져온 값 담을 변수
	
	//http 요청을 보내기 위해서 요청 보내는 것을 담을 공간
	private final RestTemplate restTemplate = new RestTemplate();
	
	//주어진 비밀키를 Base64로 인코딩해서 http헤더에 비밀키를 가져갈 수 있도록 설정
	//Base64 사람이 작성한 데이터를 컴퓨터가 읽을 수 있는 텍스트 형식으로 변환하는 방법
	
	private String encodeSecretKey(String secretKey) {
		return "Basic" + new String(Base64.getEncoder().encode( (secretKey + ":").getBytes() ));
	}
	
	@GetMapping("/callback-auth")//app.get("callback-auth")
	public ResponseEntity<?> callbackAuth(
			@RequestParam String customerKey, 
			@RequestParam String code){
		String url = "https://api.tosspayments.com/v1/brandpay/authorizations/access-token";
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", encodeSecretKey(apiSecretKey));
		headers.set("Content-Type", "application/json");
		
		Map<String, String> requestBody = Map.of(
				"grantType", "AutorizationCode",
				"customerKey", customerKey,
				"code", code);//맵 객체 안에 있는 of기능
		//of는 가져온 값을 추가/제거할 수 없도록 설정해서 가져온 값 보호
		
		//HttpEntity Http 요청의 본문과 요청조건사항이 담긴 headers 가져와서 한번에 묶어서전달예정
		HttpEntity<Map<String, String>> entity = new HttpEntity<>(requestBody, headers);
		
		//url:요청보낼주소값
		//HttpMethod.POST : 값을 삽입/조회/수정/삭제 해야하는지 전달
		//entity : 우리가 코드를 작성한 목적이 담긴 내용물과 제목 요청 조건사항이 담긴 내용
		//Map.class : 응답받을 데이터타입 지정- 응답을 key-value로 받아서 가지고있겠다
		ResponseEntity<Map> response = restTemplate.exchange(
				url, HttpMethod.POST, entity, Map.class);
		return new ResponseEntity<>(response.getBody(), response.getStatusCode());
		
	}
}
