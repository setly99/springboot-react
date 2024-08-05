import './App.css';
import axios from 'axios';
import { useState, useEffect } from 'react';
import UserTable from './component/UserTable';
import UserForm from './component/UserForm';
import EditUserForm from './component/EditUserForm';
//select insert component 추가 작성

function App() {
  const [users, setUsers] = useState([]);

  //수정한 유저정보를 잠시 담고 있을 변수 생성
  const [userToEdit, setUserToEdit] = useState(null);

  //useEffect는 버튼이나 특정값을 클릭하지 않아도 자동 실행
  //App.js가 실행되면 적용할 효과 만약 특정변수명이 없다면 기능이 최초1회만실행됨
  //특정변수명이 존재한다면 특정변수명에 변화가 있을 때마다 기능이 실행
  //useEffect( () =>{기능}, [특정변수명] );

  /*******최초 1회 실행****** */
  // useEffect(() => {
  //   모든유저보기();//홈페이지 들어오면 최초1회로 유저들이 보이고
  // }, []);//[] 비어있기 때문에 홈페이지가 보일 때 한번만 실행됨

  /*******useEffect users 넣어서 유저목록에 변화가 발생하면 모두 불러오기기능 다시실행****** */
  useEffect(() => {
    모든유저보기();
  }, [/*users*/]);//[] 안에 users가 들어있기 유저목록에 유저가 추가되거나 삭제될 경우 유저목록 새로고침


  //1. axios 성공과 실패에 대한 결과를 처리하는 버전
  /*
  const 모든유저보기 = () => {
    //axios이용해서 모든유저보겠다
    axios.get("/users")//controller GetMapping에서 /users라는 주소 바라보기 때문
    .then(응답 => {//Java에서 DB값에 있는 내용가져와 고객에게가져온 내용에대한응답알려줌
      setUsers(응답.data);//응답결과 데이터로 users변경하겠다
    })
    //.응답을 가져오지 못했을 때 문제 생겼을 때
    .catch(err=>{
      alert("가져오지못했습니다.");//주로 alert보다 console.log로 작성해서 개발자가에러보도록.
    })
  }
  */

  //2.axios 성공에 대한 결과만 보여주는 버전 async await
  //async : 기능 실행 await : 기다리기
  /*
  const 모든유저보기 = async() =>{
    const 응답 = await axios.get("/user");
    //가져오기 성공하면
    setUsers(응답.data);//성공시 가져온 데이터를 유저목록으로만듬

  }
    */

  /***********모든 유저 보는 기능************ */
  //async await버전사용
  const 모든유저보기 = async() => {
    const res = await axios.get('/users');
    setUsers(res.data);
  };

  /*************유저 추가 기능******************* */
  //async await 사용해서 유저 추가하기 addUser에서 가져온 user한명 넣어주기
  const addUser = async(user) => {
    const res = await axios.post('/users', user);//controller PostMapping으로 전달하는 유저정보
    //...users 기존에 작성한 유저목록에 유저데이터 하나를 추가
    setUsers([...users], res.data);
  }

  /**************유저 삭제 버튼**************** */
  const deleteUser = async(id) => {
    /**
    "" '' : 모두 글자 취급
    ``    : 글자 안에 특정 값을 변수명으로 취급해야 할 때
    ``안에서 ${변수명}
    */
    await axios.delete(`/users?id=${id}`);
    /**
    자바 컨트롤러에서 @DeleteMapping("/{id}") 파라미터에( @PathVariable int id) 작성
    리액트 axios에서 id=${id}
    주소값에 id대신 삭제할 번호가 들어갈 수 있도록 설정

    자바컨트롤러에서 @DeleteMapping()에 특정 id값을 설정하지 않을 경우
    파라미터에 (@RequestParam int id)
    params: {id}
    await axios.delete(`/users`, { params: {id} } );
    */

    setUsers(users.filter(user => user.id != id));

    /*
    users : 현재 저장되어 있는 유저들 리스트
    users.id != id : user.id / id(삭제하고자하는유저아이디)가 일치하지 않으면
    setUsers에 포함시키고
    id와 user.id가 일치하는 아이디는 삭제한 다음
    setUsers 완성시킴

    filter 유저목록 걸러내기
    */
    
  }

  /**********유저 수정 버튼*********** */
  const updateUser = async(user) => {
    await axios.put('/users', user);//PutMapping /users로 주소값이 설정된 수정하는 주소 연결
    setUsers(users.map(u => (u.id === user.id ? user : u)));
    //수정한 유저의 id값이 일치하지 않으면 기존 유저 정보로 전달
  }

  /**********유저 수정 완료하면 유저 목록에 수정된 유저 전달********* */
  const editUser = (user) => {
    setUserToEdit(user);
  }


  /**수정 취소하기 */
  const cancelEdit = () => {
    setUserToEdit(null);//수정취소할 때 null로 변경하는 트릭
  }



  return (
    <div className="App">
      <h1>유저관리하기</h1>
      <UserForm addUser={addUser}/>
      <UserTable users={users} 
      deleteUser={deleteUser} 
            
      editUser={editUser}/>
      {userToEdit && (
        <EditUserForm
        userToEdit={userToEdit}
        updateUser={updateUser}
        cancelEdit={cancelEdit}
        />
      )}
    </div>
  );
}

export default App;
