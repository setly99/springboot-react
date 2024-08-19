package com.kh.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{
	
	//이미지폴더경로를 react가 가져갈 수 있도록
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry r) {
		r.addResourceHandler("/images/**")
		.addResourceLocations("file:C:/Users/user1/Desktop/saveImage/");
	}
	
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
		.allowedOrigins("http://localhost:3000")
		.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
		.allowedHeaders("*");
	}
	/*
	allowedOrigins("3000")이 주소로
	addMapping("/**") 3000번 뒤 오는 모든 url api 주소 모두허용
	allowedMethods(~) 3000/** 들어오는 모든 요청 허용
	allowedHeaders("*") <html> <head> 정보에 들어갈 모든 요청허용
	 */

}
