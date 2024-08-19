package com.kh.common.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;



@Configuration//Springboot설정
@PropertySource("classpath:/config.properties")
public class DBConfig {

	@Autowired
	private ApplicationContext applicationContext;
	
	@Bean//스프링부트에서 객체로 존재한다는 의미
	@ConfigurationProperties(prefix="spring.datasource.hikari")
	public HikariConfig hikariConfig() {
		return new HikariConfig();
	}
	/*
	application.properties 나 config.properties에 작성해야할 hikariConfig를
	자바에서 작성
	
	prefix = 시작
	spring.datasource.hikari로 시작하는 모든 정보 가져오기
	
	suffix = 끝
	*/
	
	@Bean
	public DataSource dataSource(HikariConfig config) {
		DataSource dataSource = new HikariDataSource(config);
		return dataSource;
	}
	
	//db와 java관련된 추가옵션 sql코드 폴더는 어디있는지
	//db와 java컬럼명 변수명이 일치하지 않을 때 서로 어떻게 바라보게 하는지
	@Bean//DTO테이블 컬럼명 설정
	public SqlSessionFactory sessionFactory(DataSource dataSource) throws Exception {
		SqlSessionFactoryBean sfb = new SqlSessionFactoryBean();
		sfb.setDataSource(dataSource);
		sfb.setMapperLocations(applicationContext.getResources("classpath:/mappers/**.xml"));
		sfb.setTypeAliasesPackage("com.kh.dto");
		sfb.setConfigLocation(applicationContext.getResource("classpath:mybatis-config.xml"));
		
		return sfb.getObject();
	}
	
	@Bean
	public SqlSessionTemplate st(SqlSessionFactory sf) {
		return new SqlSessionTemplate(sf);
	}
	
	@Bean
	public DataSourceTransactionManager dtm(DataSource ds) {
		return new  DataSourceTransactionManager(ds);
	}
	
}

