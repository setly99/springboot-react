package com.kh.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//공공데이터 api를 이용한 api url 주소값 한번더확인
// http://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty
// 공공데이터 주소 http://apis.data.go.kr/ ->env로 80처리 한 주소
// 내 컴퓨터 주소 http://localhost:포트번호/ ->react env로 80처리 안해준 것
@RequestMapping("/B552584/ArpltnInforInqireSvc")//공공데이터에서 대기오염서비스 공통주소
//만약에 대기오염서비스가 아니라 수질오염서비스 주소 이용해야한다면
//RequestMapping에 /수질오염서비스api 작성하면됨 @RequestMapping("/수질오염서비스이동")
@RestController
public class APISController {
	
	//3번
	@GetMapping("/getCtprvnRltmMesureDnsty")//시도별 실시간 측정정보 조회 api 주소
	public String get실시간측정정보() {
		return "측정결과전달하기";
	}
	
	//1,2번 return 측정결과 전달
	//4,5번 void sysout "측정결과전달하기"
	
	// 1.GetMapping주소로 측정소별 실시간 측정정보 조회
	@GetMapping("/getMsrstnAcctoRltmMesureDnsty")
	public String get주소로측정하기() {
		return "측정결과 전달하기1";
	}
	
	// 2.통합대기환경지수 나쁨이상 측정소 목록조회
	@GetMapping("/getUnityAirEnvrnIdexSnstiveAboveMsrstnList")
	public String get나쁨측정소목록조회() {
		return "측정결과 전달하기2";
	}
	
	// 4.대기질 예보통보 조회
	@GetMapping("/getMinuDustFrcstDspth")
	public void 예보통보조회() {
		System.out.println("측정결과 전달하기4");
	}
	
	
	// 5.미세먼지 주간예보 조회
	@GetMapping("/getMinuDustWeekFrcstDspth")
	public void 주간예보조회() {
		System.out.println("측정결과 전달하기5");
	}

}
