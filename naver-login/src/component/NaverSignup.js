import React, { useContext, useEffect, useState} from "react";
import axios from 'axios';
import "../UserInfo.css"
import { useLocation } from "react-router-dom";//버튼클릭없이 위치설정
import AuthContext from "./layout/AuthContext";
/*
useLocation : URL의 정보를 포함한 객체
경호, 해시, 문자열값 등 가지고 온 객체
*/

function NaverSignup(){
    const [userInfo, setUserInfo] = useState(null);


    /**** 2024-08-12 비밀번호 값 설정 추가 ****/
    const[password, setPassword] = useState("");//비밀번호 상태 추가


    const location = useLocation();
    const [loading, setLoading] = useState(true);

    const {loginMember} = useContext(AuthContext);

    //어떤클릭이없어도 UserInfo 페이지 들어오면 자동으로 실행되는 효과
    useEffect(() => {
        //URLSearchParams : URL ? 뒤에 붙은 키 - 밸류 값 가져옴
        //String redirectUrl  "" + accessToken
        //userinfo? 뒤에 붙는 access_token에 있는 데이터포함
        const a = new URLSearchParams(location.search);
        const accessToken = a.get('access_token');
        console.log("토큰확인 : " + accessToken);
        //URLSearchParams 가져온 수많은 값 중에서 키이름이 access_token인 값만 가져오겠다

        //get 이용해서 userinfo정보가져오기
        //String redirectUrl = "" + accessToken;
        //자바에서는 userinfo?access_token = 뒤에 + 붙여서 변수 사용했지만
        //자바스크립트에서는 ``를 사용해서 가져옴

        //만약에 accessToken값이 존재하면 axios발동해야함
        if(accessToken){
        axios.get(`/signup/naver?access_token=${accessToken}`)
        .then(response => {//(response) 괄호로 막으면 지역변수명이되어 res찾을수없게됨
            setUserInfo(response.data);
            setLoading(false);
        })
        .catch((err) => {
            alert("정보를 가져오지 못했습니다.");
        })
        }
    }, [location.search]);//location.search로 검색된 키-값중 access_token=abc123
    //access_token 값 가져오면 useEffect사용하겠다

    if(loading){
        return <div>데이터 정보 가져오는중 ...</div>
    }

    //회원가입기능 만들기 React에서 Java로 데이터를 보낼 것
    //데이터를 /NaverAPI/register 위치에서 주고받음
    const 회원가입기능 = () => {
        if(!password){//비밀번호 비어있다면
            alert("비밀번호를 입력해주세요.");
            return;
        }

        //axios.post(만나는위치설정, {주고받을데이터설정})
        axios.post('http://localhost:9010/signup/naver', {
            id : userInfo.response.id,
            email : userInfo.response.email,
            name : userInfo.response.name,
            nickname : userInfo.response.nickname,
            gender : userInfo.response.gender,
            profileImage : userInfo.response.profile_image,
            password : password
        })
        .then(response => {
            console.log(response.data);//db에들어갔는지확인
            alert("회원가입이 완료되었습니다.");
        })
        .catch(e => {
            console.error('개발자가 에러 확인하는 공간 : ', e);
            alert("회원가입에 실패했습니다.");
        })
    }


    return(
        <>
        <h1>유저정보</h1>
        {userInfo ? (
            <div>
                <input type="text" value={userInfo.response.id} disabled/>
                <input type="email" value={userInfo.response.email} disabled/>
                <input type="text" value={userInfo.response.name} disabled/>
                <input type="text" value={userInfo.response.nickname} disabled/>
                <input type="text" value={userInfo.response.gender} disabled/>
                <img src={userInfo.response.profile_image}/>
                {/**네이버에서 가져온 id값을 input에 넣어주고 수정하지 못하게 막음처리 */}
            </div>
            ) : (
        <p>유저를 찾을 수 없습니다.</p>
        )
        }
        <div>
            <h2>회원가입에 필요한 아이디 및 비밀번호 작성하기</h2>
            <input 
            type="password" 
            value={password} 
            onChange={(e) => setPassword(e.target.value)}/>
            {/*
            <input 
            type="password" 
            value={password} 
            onChange={비밀번호변경하기}/>

            const 비밀번호변경하기 = (e) => {setPassword(e.target.value)}
            */}

            <button onClick={회원가입기능}>회원가입하기</button>
        </div>
        </>
    )

}
export default NaverSignup;