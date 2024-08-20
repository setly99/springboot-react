package com.kh.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//외부 포트나 외부 주소에서 서로 데이터를 주고 받을 수 있도록 허용
@Configuration
public class WebConfig implements WebMvcConfigurer{
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**") //3000다음에 오는 api url 모두 허용
		.allowedOrigins("http://localhost:3000") //localhost:3000 주소 접속 허용
		.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") //httpMethod와 기타옵션 모두 허용
		.allowedHeaders("*");//데이터,이미지,파일 다중파일 등 모두 허용
	}

}
