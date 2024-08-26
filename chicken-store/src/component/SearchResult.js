import React, {useEffect, useState} from "react";

import axios from "axios";

import { useLocation } from "react-router-dom";
import '../css/SearchResult.css';

const SearchResult = () => {

    //치킨들을 담을 변수명
    const [chickens, setChickens] = useState([]);
    //주소값에서 가져온 쿼리 추출
    const location = useLocation();

    //주소값 api URL 쿼리 파라미터 추출
    const query = new URLSearchParams(location.search).get("query");

    //주소에서 쿼리안에 클라이언트 사용자가 원하는 특정 값이 존재하는지
    useEffect(()=>{
        if(query){
            axios.get(`http://localhost:9090/api/chicken/search?query=${query}`)
            .then((response) => setChickens(response.data))
            .catch((err) => console.error("검색 가져오는데 문제 발생 : ", err))
        }
    },[query]);

    return(
        <div className="chicken-list">
            <h2>검색 결과 : "{query}"</h2>
            {chickens.length > 0 ? 
            (
                chickens.map((chicken) => (

                    <div key={chicken.id} className="chicken-item">
                    <h3>{chicken.chickenName}</h3>
                    <p>{chicken.description}</p>
                    <p>{chicken.price}원</p>
                    </div>) )
            ) : (
            <div>
                <p>검색 결과가 없습니다.</p>
            </div>)}

        </div>
    )
}
export default SearchResult;