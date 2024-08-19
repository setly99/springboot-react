package com.kh.config;
//React 3000 에서 접속할 수 있도록 설정

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")//3000뒤에붙는 모든 url 허용
		.allowedOrigins("http://localhost:3000")
		.allowedMethods("GET","POST","PUT","DELETE","OPTIONS")
		.allowCredentials(true);//쿠키나 세션과 같은 자격 허용
	}
}
