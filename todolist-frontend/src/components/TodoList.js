import React, {useState, useContext} from "react";
import TodoListContext from "./TodoListContext";

const TodoList = () => {

    const {todoList, setTodoList, loginMember} = useContext(TodoListContext);
    const {inputTodo, setInputTodo} = useState('');

    let keyIndex = 0;

    //할 일 추가버튼 기능 설정

    return(
        <>
            <h1>{loginMember.name}의 Todo List</h1>{/**java TodoServiceImpl의 map.put("loginMember" 연결 */}
            <div className="todo-container">
                <h3>할 일(Todo) 입력</h3>
                <div>
                    <input 
                    type="text" 
                    value={inputTodo}
                    onChange={e => setInputTodo(e.target.value)}/>
                    <button onClick={할일추가버튼}>할 일 추가하기</button>
                </div>

                <ul>
                    {/**배열.map : 기존 배열을 이용해서 새로운 배열 만들기 */}
                    {TodoList.map((todo, index) => (
                        <li key={keyIndex++}>
                            <div>
                                <span className={todo.isDone === 'X' ? 'todo-complete' : ''}>
                                    {todo.title}
                                </span>
                                <span>
                                    <button onClick={ () => {toggleTodo(todo, index)} }>
                                        {todo.isDone}
                                    </button>
                                    <button onClick={() => {deleteTodo(todo.todoNo, index)}}>
                                        삭제
                                    </button>
                                </span>
                            </div>
                        </li>
                    ))}
                </ul>
            </div>
        </>
    )
}
export default TodoList;