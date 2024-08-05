package tosspay.controller;

import java.util.Base64;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController//html 파일 url 주소값으로 연동
@RequestMapping("/confirm")
public class PaymentController {
	//application.properties에 설정 키이름을 가져오기 위해 value
	@Value("${widgetSecretKey}")//특정한 키이름을 외부에서 가져와 사용할 때 ${키이름}작성
	private String widgetSecretKey;
	
	@Value("${apiSecretKey}")
	private String apiSecretKey;
	
	private final RestTemplate restTemplate = new RestTemplate();
	
	private String encodeSecretKey(String secretKey) {
		return "Basic" + new String(Base64.getEncoder().encode( (secretKey + ":").getBytes() ));
	}
	
	//widget이라는 주소로 결제정보가 들어오면 결제확인창구로 넘겨주는 것 결제정보와 결제하고자하는 사용자의 비밀번호
	@PostMapping("/widget")
	public ResponseEntity<?> confirmWidget(@RequestBody Map<String, String> requestBody){
		return confirmPayment(requestBody, encodeSecretKey(widgetSecretKey));
	}
	
	//payment라는 주소로 결제정보가 들어오면 결제확인창구로 넘겨주는 것 결제정보와 결제하고자하는 사용자의 비밀번호
	@PostMapping("/payment")
	public ResponseEntity<?> confirmPayment(@RequestBody Map<String, String> requestBody){
		return confirmPayment(requestBody, encodeSecretKey(apiSecretKey));
	}
	
	@PostMapping("/brandpay")
	public ResponseEntity<?> confirmBrandpay(@RequestBody Map<String, String> requestBody){
		return confirmPayment(requestBody, encodeSecretKey(apiSecretKey));
	}
	
	private ResponseEntity<?> confirmPayment(Map<String, String> requestBody, String encodedKey){
		String url = "https://api.tosspayments.com/v1/brandpay/payments/confirm";//fetch("",{
		HttpHeaders headers = new HttpHeaders();
		//			Authorization: encryptedApiSecretKey
		headers.set("Authorization", encodedKey);//encrypted 를 위에서 encodedKey작성해줬음
		//			Content-Type : application/json
		headers.set("Content-Type", "application/json");
		
		HttpEntity<Map<String, String>> entity = new HttpEntity<>(requestBody, headers);
		
		//성공했을 때, 실패했을 때
		
		try {
			ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.POST, entity, Map.class);
			return new ResponseEntity<>(response.getBody(), response.getStatusCode());
		}catch(Exception e) {
			// 사용자한테 보내는 응답 			실패메세지			잘못된 요청으로 안됐다. 상태코드보냄
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
}

/**
 * Entity

 * HttpEntity : HTTP 요청 또는 응답의 본문(body)과 헤더(headers)를 포함하는 객체
 사용자가 요청한 응답을 개발자가 다시 사용자한테 전달할 때 사용
 Http요청을 보낼 때 본문과 헤더를 설정하고자 할 때 사용
 본문(body) : 실제 전송될 데이터
 헤더(headers) : HTTP 헤더 정보 포함 - 어떤 파일이 들어오는지, 누가보내는지
 HttpEntity<타입> abc = new HttpEntity<>("요청본문", headers);
 
 *ResponseEntity (Response응답 / HttpEntity상속받아서 Http기능에 응답에 대한 기능을 추가로 설정한 Entity)
 				: HttpEntity를 상속받아, Http 응답에 대한 추가적인 정보를 제공, 상태코드를 포함하고 있어서
 				클라이언트(사용자)에게 응답을 보낼 때 사용
 ResponseEntity<타입-모를때:비워둠/아무거나:?> abc = new ResponseEntity<>("응답본문", headers);
 *RequestEntity  (Request요청  / HttpEntity상속받아서		   요청에 대한						)
				: HttpEntity를 상속받아, Http 요청에 대한 추가적인 정보를 제공
				URI와 HTTP메서드 (GET, POST, PUT, DELETE)를 포함하고 있어, 서버로 요청을 보낼 때 주로 사용
RequestEntity<타입> abc = new RequestEntity<>("요청본문", headers);
RequestEntity<타입> abc = new RequestEntity<>("요청본문", headers, HttpMethod.POST, url);

차이점요약
클래스			상속관계			주사용목적					추가정보
HttpEntity		기본클래스			Http요청/응답본문과 헤더포함	상태코드없음
ResponseEntity	HttpEntity상속	Http응답반환				상태코드포함(성공여부)
RequestEntity	HttpEntity상속	Http요청전송				URI, HTTP메서드포함

URI 와 URL
URI : 주소값과 식별값이 들어있음 이 안에 URL이 들어있음
URL : URI의 한 종류로 주소이름
URN : 고유한 이름
 */