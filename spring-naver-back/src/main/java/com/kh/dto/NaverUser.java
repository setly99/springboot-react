package com.kh.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class NaverUser {
	//id ~ profileImage : 네이버에 저장된 값 가져와서 db에 저장
	private String id;
	private String email;
	private String nickname;
	private String name;
	private String gender;
	private String profileImage;
	//password : 사용자가 작성한 비밀번호를 가져와서 db에 저장
	private String password;

}
