import React, {useState} from 'react';
import LoginContext from './components/LoginContext';
import Login from './components/Login';
import './App.css';

function App() {

  //로그인한 회원정보 저장
  const [loginMember, setLoginMember] = useState(null);

  return (
    <LoginContext.Provider value={ {loginMember, setLoginMember}}>
      <Login/>
    </LoginContext.Provider>
  );
}

export default App;
