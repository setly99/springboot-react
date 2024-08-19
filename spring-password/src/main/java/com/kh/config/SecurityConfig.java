package com.kh.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/*
 * BCryptPasswordEncoder - 이 이름으로 객체생성x
Blowfish 암호 알고리즘을 기반
Crypt 암호화 Encrypt

SecurityConfig
build.gradle 설정에 security implementation으로 보안 추가
implementation 'org.springframework.boot:spring-boot-starter-security'
*/
@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        //http method get post put delete 모든 동작
		http.authorizeHttpRequests(authorize -> authorize
                .anyRequest().permitAll()//비밀번호 없이 9011포트 들어갈 수 있도록 연결 허용
            )
            .csrf(csrf -> csrf.disable());
		
		//build-위에서작성된 http 상세설명을 바탕으로 http관련 빌드
        return http.build();
    }

	// @Bean 패스워드 관련 객체 설정
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
