package todo.common.config;

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


@Configuration//기능설정이라는 어노테이션
@PropertySource("classpath:/config.properties")
/*github에 올리지 않고 이메일 이름 비밀번호와 같이 암호화해서 사용해야 하는
 * 설정을 가지고 오는 것
 * Property : 개발자가 사용자한테 인증번호를 보낼 이메일이나 비밀번호
 * 또는 데이터베이스 아이디 비밀번호 주소와 같이 회사에서 비공개적으로 보호해야하는 자산 */
public class DBConfig {
	
	@Autowired
	private ApplicationContext applicationContext;
	//현재 만든 TodoList-backend 폴더 흐름
	//TodoList-backend 폴더 : Application : 나중에 폴더 안에 작성한 파일이 하나의 어플이나 웹에서 작동하는 파일
	//추후 자바나 자바스크립트 코드로 작성한 파일을 exe와 같은 확장자로 만들어 소비자들이 다운받고 실행할 수 있는
	//프로그램을 만들 수 있음
	
	@Bean
	@ConfigurationProperties(prefix = "spring.datasource.hikari")
	public HikariConfig hikariConfig() {//hikari = DB연결하기 위해 이용하는 기능
		return new HikariConfig();//hikari와 같은 외부 기능을 사용하지 않으면 코드가 최소20줄
	}
	
	//연결된 DB를 스프링에서 인지하고 관리할 것
	@Bean
	public DataSource dataSource(HikariConfig config){
		DataSource dataSource = new HikariDataSource(config);
		return dataSource;
		
	}
	//////////*Mybatis 설정 추가*///////////////
	@Bean
	public SqlSessionFactory sessionFactory(DataSource dataSource) throws Exception {
		SqlSessionFactoryBean sfb = new SqlSessionFactoryBean();
		sfb.setDataSource(dataSource);
		
		//Select Insert Update Delete가 작성된 매퍼 파일이 모여있는 폴더 경로 설정
		// src/main/resources / mappers 폴더 안에 작성된
		//xml로 끝나는 모든 파일을 바라보겠다는 ** 표시 작성
		//classpath : src/main/resources 줄임말
		sfb.setMapperLocations(applicationContext.getResources("classpath:/mappers/**.xml"));
		
		//DTO모델이 모여있는 패키지 설정
		//Aliase : 별칭
		//DB에 작성한 컬럼명과 DTO에 작성한 컬럼명이 다를 때 별칭-컬럼명 일치한다는 것 명시하기 위해
		//DTO위치 폴더를 작성
		sfb.setTypeAliasesPackage("todo.dto");//******추후 본인의 dto 패키지명으로 변경*****//
		
		//Mybatis에서 DB와 컬럼에 어떤 설정을 해주고 설정에 대한 정보를 어디에 작성했는지
		//Mybatis 설정 경로와 파일명 작성
		sfb.setConfigLocation(applicationContext.getResource("classpath:mybatis-config.xml"));//***추후파일명이나 경로변경에 따라 수정***//
		return sfb.getObject();
		
	}
	
	//기본 SQL 실행한 다음 insert update delete 같은 경우 commit이나 rollback처리
	@Bean
	public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sf) {
		return new SqlSessionTemplate(sf);
	}
	
	//전반적인 commit과 rollback 같은 관리를 해주는 트랜잭션 매니저
	@Bean
	public DataSourceTransactionManager dataSourceTransactionManager(DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

}















