package com.kh.entity;

/*
model = dto, entity, vo
기존 db에 테이블이 존재하는 것 연결 : dto
기존 db에 테이블이 없어서 생성해줘야 할 때 : entity
db와 관계 없음 : vo
*/
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity//만약db에 pizzas 로 테이블 저장하길 원하면 @Table에 이름 명시를 해주면 됨
public class Kh_pizza {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	private String description;
	private double price;

}
