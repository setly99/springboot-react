import { useEffect, useState } from "react";
import { useLocation } from "react-router-dom";
import axios from "axios";

const PizzaResult = () => {

    //검색된 피자들을 담을 변수명 설정
    const [pizzas, setPizzas] = useState([]);

    //useLocation : 현재 페이지의 정보를 가지고 있음 경로나 검색된 문자들
    //useLocation 안에 pathname, search, hash, state, key 존재함
    const location = useLocation();//컴퓨터 상에서 내 위치정보를 변수에 담음
    const query = new URLSearchParams(location.search).get("query");
    //location에서 특정 키의 값을 가지고 오는 것

    //검색에 대한 정보가 바로 보여야 하고, 검색어 수정되면 재검색 해야함
    //query : 검색어 : keyword : searchTerm
    useEffect( () => {
        //만약에 쿼리가 존재하면
        if(query){
            axios.get(`http://localhost:9090/api/pizza/search?query=${query}`)
            .then((response) => setPizzas(response.data))
            .catch(e => console.error('문제가 발생하여 검색가져오지못함',e));
        }
    },[query]);

    return(
        <div className="pizza-search-list">
            <h1>검색결과 : </h1>
            {/*검색된 피자들을 map 이용해서 모두 보기 검색은 length가 0 초과일 때만가능 */}
            {pizzas.length > 0 ? 
            (
                pizzas.map((p) => (
                    <div key={p.id}>
                        <h3>{p.name}</h3>
                        <p>{p.descripton}</p>
                        <p>{p.price}</p>
                    </div>
                ))
            
            ) : (
            <div>
                검색결과가 존재하지 않습니다.
            </div>)}
        </div>
    )

}
export default PizzaResult;