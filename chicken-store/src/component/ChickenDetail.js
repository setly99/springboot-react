import { useEffect, useState } from "react";
//axios useEffect 활용해서 데이터 불러오기
import axios from "axios";
import { useNavigate, useParams } from "react-router-dom";
import '../css/ChickenDetail.css';

const ChickenDetail = () => {

    const navigate = useNavigate();//페이지 이동 위한 navigate
    /*navigate : 기능작성에서 이동할 때 주로 사용 const 함수 / useEffect안에
    이동하는 동작이 소비자 눈에 직접 보이지 않음

    Link : 태그에서 직접적으로 주소 이동을 작성해줄 때 사용
    */

    // {}:특정값 받아오는것, []:변수명설정
    const {id} = useParams();
    //console.log("id : ", id);
    const [chicken, setChicken] = useState(null);

    //수정한 데이터 저장하는공간
    const [editData, setEditData] = useState(
        {
            chickenName:"",
            description:"",
            price:""
        }
    );

    //수정하기 버튼 눌림 체크 boolean
    const [isEditing, setIsEditing] = useState(false);

    useEffect(() => {
        axios.get(`http://localhost:9090/api/chicken/${id}`)
        .then(response => {
            setChicken(response.data);
            setEditData(
                {
                    chickenName: response.data.chickenName,
                    description: response.data.description,
                    price: response.data.price
                }
            );
        })
        .catch(e => alert('불러오는데 문제가 발생했습니다.'));
    },[])


    //만약 치킨 데이터가 없으면 로딩중
    if(!chicken){
        return(
            <div>
                로딩중...
            </div>
        )
    }

    const handle수정한내용저장 = () => {
        //axios이용해서 주소 불러오기
        axios.put(`http://localhost:9090/api/chicken/${id}`, editData)
        .then(response => {
            setChicken(response.data);//기존db에 저장된 내용가져오기
            setIsEditing(false);
            //내용수정하기 setEditData
        })
        .catch(error => {
            console.error('수정에서 문제발생');
        })
    }

    const handle수정하기 = () => {
        setIsEditing(true);
    }

    const handle돌아가기 = () => {

    }

    const handle삭제하기 = () => {
        axios.delete(`http://localhost:9090/api/chicken/${id}`)
        .then(() => {
            alert('삭제되었습니다.');
            navigate("/");//삭제하고 메인으로 이동
        })
        .catch(err => {
            console.log("삭제하는데 문제가 발생했습니다.");
        })
    }

    return(
        <div className="chicken-detail-container">
            {/**수정하기 버튼 누르면 ? o기능나오기 : x작성내용보여주기 */
                isEditing ? (
                <div>
                    <input type="text" name="chickenName" value={editData.chickenName}
                    onChange={(e) => setEditData({...editData, chickenName:e.target.value})} />
                    <textarea name="desecription" value={editData.description}
                    onChange={(e) => setEditData({...editData, description:e.target.value})} />
                    <input type="number" name="price" value={editData.price}
                    onChange={(e) => setEditData({...editData, price:e.target.value})} />
                    <button onClick={handle수정한내용저장}>수정완료</button>
                    <button onClick={handle돌아가기}>돌아가기</button>
                </div>):(
                <div>
                    <h1>{chicken.chickenName}</h1>
                    <p>{chicken.description}</p>
                    <p>{chicken.price}원</p>
                    <button onClick={handle수정하기}>수정하기</button>
                    <button onClick={handle삭제하기}>삭제하기</button>
                </div>)
            }

        </div>
    )
}
export default ChickenDetail;