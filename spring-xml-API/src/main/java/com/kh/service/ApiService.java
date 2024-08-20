package com.kh.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ApiService {
	
	//Value 어노테이션을 이용해서 application.properties에 작성된
	//url apikey content-type 변수명으로 가져오기
	
	@Value("${api.base-url}")
	private String baseUrl;
	
	@Value("${api.key}")
	private String apiKey;
	
	@Value("${api.content-type}")
	private String contentType;
	
	public String getAirData() throws Exception {
		//주소값 설정
		String url = baseUrl;
		url += "?serviceKey=" + URLEncoder.encode(apiKey, "UTF-8");
		url += "&sidoName=" + URLEncoder.encode("서울", "UTF-8");
		url += "&returnType=xml";//서비스키와 서울지역을 데이터를 가져올 때 xml파일로 가져옴
		// xml구버전 json구&신버전
		System.out.println("(1)url:"+url);
		
		//세팅된 주소를 가지고 데이터 가져오기
		URL requestUrl = new URL(url);//requestUrl : URL 주소값형식
		System.out.println("(2)requestUrl:"+requestUrl);
		//HttpURLConnection : 자바에서 특정 주소에 연결과 동시에 HttpMethod 요청을 보낼 수 있음
		HttpURLConnection uc = (HttpURLConnection) requestUrl.openConnection();
		uc.setRequestMethod("GET");
		uc.setRequestProperty("content-Type", contentType);
		System.out.println("(3)uc:"+uc);
		
		//남의 주소에서 남이 지정한 형식 가져와야 하기 때문에 한줄씩 읽어서 모두 실시간 가져오기
		BufferedReader br = new BufferedReader(new InputStreamReader(uc.getInputStream()));
		StringBuilder response = new StringBuilder();
		System.out.println("(4)response:"+response);
		String line;
		//데이터 한줄씩 가져오기
		while( ( line=br.readLine() ) != null) {
			response.append(line);
		}
		System.out.println("(5)response:"+response);
		
		br.close();//데이터 다 가져오면 닫기
		uc.disconnect();//url연결끊기
		//가져온 데이터 글자값으로 보여주기 위해 전달
		
		return response.toString();
	}

}
