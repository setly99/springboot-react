import React, { useState, useEffect } from "react";

const APILogin = () => {
  const [userInfo, setUserInfo] = useState(null);

  // useEffect 활용해서 데이터 가져오기
  //get 이용해서 특정 유저 정보 가져오는 주소 설정
  useEffect(() => {
    const 유저정보 = () => {
        fetch('http://localhost:9010/userinfo')//http method (get post put delete)
        .then(가져온응답결과 => {//then fetch가 java controller에서 값 가져왔을 때
            return 가져온응답결과.json();
        })
        .then(data => {//위 then 실행 구문을 바탕으로 userInfo에 값 넣어주기
            setUserInfo(data);
        })
        .catch(err => {//위에서 문제가 생겼을 때 문제 catch할 구문
            console.error("에러Error userInfo : ", err);
        })
    };

    //유저정보 기능 실행
    유저정보();
    
  }, [])// ,[] 최초실행인지 주기적으로 계속 실행하는 효과인지 설정

  return (
    <>
      {/**만약 userInfo 정보가 있으면 로그인 완료, 없으면 로그인하기화면 보여줌*/}
      {/**삼항연산자 이용하기 */}

      {userInfo ? (
        /**userinfo 정보가 있을 때 */
        <div>
          <h1>로그인 완료</h1>
          <div>{JSON.stringify(userInfo, null, 2)}</div>
        </div>
      ) : (
        /**userinfo 정보가 없을 때 */
        <a href="http://localhost:9010/api/naverLogin">
          <img
            height="50"
            src="http://static.nid.naver.com/oauth/small_g_in.PNG"
          />
        </a>
      )}

    </>
  );
};
export default APILogin;

/**
{ JSON.stringify(userInfo, null, 2) }
JSON.stringify : 자바 백엔드에서 가져온 값을 문자열로 변환
자바에서 가져오는 데이터가 숫자인지 문자인지 알 수 없는 상태기 때문에
안전하게 문자열로 가져오겠다 설정

userInfo : 가져온 값에서 특정 값을 변경하거나 필터링할 것인가 - 변경 x - null
, 2 ) : 두칸 들여쓰기

{JSON.stringify(userInfo, 가운데사용, 2)}

const 유저정보 = {
    key : value
    id : "123456",
    name : "홍길동",
    phone : "010-9876-5432"//변경전 원본
}
DB에 유저가 작성한 핸드폰 번호 중 - 제거하고 01098765432 저장하길 원함

const 번호변경하기 = (key, value) => {
    if(key==='phone'){
        return value.replace("-","");
    }
}

{JSON.stringify(유저정보, 번호변경하기, 2)}

번호변경하기 기능 거친 유저정보:
const 유저정보 = {
    key : value
    id : "123456",
    name : "홍길동",
    phone : "01098765432"//변경후
}



*/
