import React, {useState, useEffect} from 'react';
import axios from 'axios';

const ChickenList = () => {

    const [chickens, setChickens] = useState([]);

    //최초1회 실행 useEffect 이용해서 시작하자마자 db에서 치킨메뉴 가져오기
    useEffect(()=>{
        axios.get("http://localhost:9090/api/chicken")
        .then(response => setChickens(response.data))
        .catch(e => alert("문제 발생"));
    },[])
    return(
        <div className='chicken-container'>
            <h1>치킨메뉴</h1>
            <ul>
            {chickens.map(chicken => (
                <li key={chicken.id}>
                    {chicken.chickenName}  {chicken.description}  ₩{chicken.price}원
                    <button>삭제하기</button>
                </li>

            ))}
            </ul>
        </div>
    )
}
export default ChickenList;