package com.kh.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity//mysql에 테이블이 존재하지 않으면 테이블 생성
@Setter
@Getter
public class Chicken {

	@Id//primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String chickenName;
	private String description;
	private double price;
}
