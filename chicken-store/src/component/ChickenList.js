import React, {useState, useEffect} from 'react';
import axios from 'axios';
import '../css/ChickenList.css';
import { useNavigate } from 'react-router-dom';

const ChickenList = () => {

    const [chickens, setChickens] = useState([]);
    const navigate = useNavigate();

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
                <li key={chicken.id} className='chicken-item'>
                    <div className='chicken-name'>{chicken.chickenName}</div>
                    <div className='chicken-description'>{chicken.description}</div>
                    <div className='chicken-price'>₩{chicken.price}원</div>
                    <button className='detail-button'
                    onClick={() => navigate(`/chicken-detail/${chicken.id}`)}>
                    상세보기</button>

                    {/* navigate 와 Link

                    <button className='detail-button'
                    onClick={() => navigate(`/chicken-detail/${chicken.id}`)}>
                    상세보기</button>

                    <button className='detail-button'>
                        <Link to={`/chicken-detail/${chicken.id}`}/>
                    상세보기</button>
                    */}
                </li>
            ))}
            </ul>
        </div>
    )
}
export default ChickenList;