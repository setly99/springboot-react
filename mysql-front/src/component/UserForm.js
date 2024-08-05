import React, {useState} from "react";

const UserForm = ({addUser}) => {
    const [name, setName] = useState('');
    const [email, setEmail] = useState('');

    const handleSubmit = (e) => {
        e.preventDefault();//잠시 제출 방지
        addUser({name, email});

        //input에 작성한 내용 제출 후 빈칸으로 초기화
        setName('');
        setEmail('');
    }

    return(
        <div>
            <form onSubmit={handleSubmit}>
                <div>
                    <label>이름 : </label>
                    <input type="text" 
                    value={name} 
                    onChange={(e) => setName(e.target.value)} 
                    required/>
                </div>
                <div>
                    <label>이메일 : </label>
                    <input type="email" 
                    value={email} 
                    onChange={(e) => setEmail(e.target.value)} 
                    required/>
                </div>
                <button type="submit">유저 추가하기</button><br/>
                <button type="submit">네이버로 로그인</button>

            </form>
        </div>

    )
}
export default UserForm;