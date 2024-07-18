package todo.dto;

import lombok.*;

//VO : database까지는 가지 않고 읽기 전용 (email 인증번호)
//DTO : database에 값을 연동해서 사용
//Entity : JPA DataBase Oracle에 테이블을 만들지 않아도 알아서 테이블 만들어주고
//컬럼지정해주고 컬럼값 설정

@ToString
@Setter
@Getter
public class Todo {
	private int todoNo;
	private String title;
	private String isDone;
	private int todoMemberNo;//어떤고객의 할일인가 -고객번호연동

}
