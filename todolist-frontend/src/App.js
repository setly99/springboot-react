import React, {useState} from 'react';
import TodoListContext from './components/TodoListContext';
import Login from './components/Login';
import TodoList from './components/TodoList';
import './App.css';

function App() {

  //로그인한 회원정보 저장
  const [loginMember, setLoginMember] = useState(null);

  const [todoList, setTodoList] = useState([]);

  return (
    <TodoListContext.Provider value={ {loginMember, setLoginMember, todoList, setTodoList}}>
      <Login/>
      <hr/>
      { loginMember && ( <TodoList/> ) }
    </TodoListContext.Provider>
  );
}

export default App;
