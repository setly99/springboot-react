import './App.css';
import NaverApi from './component/NaverApi';
import {BrowserRouter as Router, Route, Routes} from 'react-router-dom';
import NaverSignup from './component/NaverSignup';
import Header from './component/layout/Header';
import { useEffect, useState } from 'react';
import AuthContext from './component/layout/AuthContext';
import Login from './component/Login';
//html파일이 1개밖에 없는 React에서는
//Router를 이용해서 각 js파일의 경로를 설정
//BrowserRouter = Router 웹에 전체적인 경로
//Switch -> Routes 경로들
//Route 경로

function App() {

  const [loginMember, setLoginMember] = useState(null);

  //로그인정보 localStorage저장
  useEffect(() => {
    if(loginMember){
      localStorage.setItem('loginMember', JSON.stringify(loginMember));
    }
  }, [loginMember]);

  //로그인이 저장된 정보 전달
  useEffect(() => {
    const savedMember = localStorage.getItem("loginMember");
    //만약에 loginMember 변수에 저장된 회원정보가 존재한다면 setLoginMember에 넣음
    if(savedMember){
      setLoginMember(JSON.parse(savedMember));//json형태로 변형
    }
  },[])

  return (
    <AuthContext.Provider value={{loginMember, setLoginMember}}>
    <Router>
      <Header/>
      <Routes>
        <Route path='/login' element={<Login/>}/>
        <Route path='/api/naver' element={<NaverApi/>}/>
        <Route path='/signup/naver' element={<NaverSignup/>}/>
      </Routes>
    </Router>
    </AuthContext.Provider>
  );
}

export default App;
