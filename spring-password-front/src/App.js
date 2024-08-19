import {useState} from 'react';
import logo from './logo.svg';
import './App.css';


function App() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [email, setEmail] = useState('');

  const handle가입하기 = () => {
    //fetch, formData 활용해서 회원가입 작성
    const formData = new FormData();
    formData.append('username',username);
    formData.append('email',email);
    formData.append('password',password);

    const user = {
      username:username,
      email:email,
      password:password
    };

    fetch('http://localhost:9011/api/register',{
      method:'POST',
      headers:{
        'Content-Type':'application/json',
      },
      body:JSON.stringify(user)
      //body: formData -> multipart
      /*
      body:{
        'username':username,
        'email':email,
        'password':password
      }
        */
    }).then(response => response.text())
    //데이터 무사히 저장됐다고 알려줌
    .then(data => {
      alert("회원가입 완료");
    })
    .catch(error => {
      alert("회원가입 실패");
    })
  }

  return (
    <div className="App">
      <h2>회원가입</h2>

      <label>닉네임 : </label>
      <input type='text' placeholder='닉네임작성'
      value={username}
      onChange={(e) => setUsername(e.target.value)}
      />

      <label>이메일 : </label>
      <input type='email' placeholder='이메일작성'
      value={email}
      onChange={(e) => setEmail(e.target.value)}
      />

      <label>비밀번호 : </label>
      <input type='password' placeholder='비밀번호작성'
      value={password}
      onChange={(e) => setPassword(e.target.value)}
      />

      <button onClick={handle가입하기}>가입하기</button>
    </div>
  );
}

export default App;
