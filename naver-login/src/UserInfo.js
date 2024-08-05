import React, {useEffect, useState} from "react";
import axios from 'axios';
import "./UserInfo.css"

import { useLocation } from "react-router-dom";//버튼클릭없이 위치설정
/*
useLocation : URL의 정보를 포함한 객체
경호, 해시, 문자열값 등 가지고 온 객체
*/

function UserInfo(){
    const [userInfo, setUserInfo] = useState(null);
    const location = useLocation();
    const [loading, setLoading] = useState(true);
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
        axios.get(`/userinfo?access_token=${accessToken}`)
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


    return(
        <>
        <h1>유저정보</h1>
        {userInfo ? (
            <div>
                <input type="text" value={userInfo.response.id} disabled/>
                <input type="email" value={userInfo.response.email} disabled/>
                <input type="text" value={userInfo.response.name} disabled/>
                <input type="text" value={userInfo.response.nickname} disabled/>
                <img src={userInfo.response.profile_image}/>
                <input type="text" value={userInfo.response.gender} disabled/>
                {/**네이버에서 가져온 id값을 input에 넣어주고 수정하지 못하게 막음처리 */}
            </div>
            ) : (
        <p>유저를 찾을 수 없습니다.</p>
        )
        }
        <div>
            <h2>회원가입에 필요한 아이디 및 비밀번호 작성하기</h2>
            <input type="text"/>
        </div>
        </>
    )

}
export default UserInfo;