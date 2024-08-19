import React, {useContext} from "react";
import AuthContext from "./AuthContext";
import { Link } from "react-router-dom";

//로그인성공시 보여줄 헤더
// oo님 환영합니다.
//비밀번호 db저장시 암호화

//비밀번호 찾기 -> 기존 비밀번호 x 새로운비밀번호 확인 저장

const Header = () => {
    const {loginMember, setLoginMember} = useContext(AuthContext);

    //localStorage : 고객 컴퓨터 웹사이트에 데이터를 영구적으로 저장
    //localStorage 저장도니 데이터는 브라우저를 닫거나 컴퓨터를 껐다 켜도 유지
    //사용자가 타이머를 맞춰서 삭제하거나 로그아웃하거나 캐시 지우지 않는 한 유지

    const handle로그아웃기능 = () => {
        setLoginMember(null);
        localStorage.removeItem('loginMember');
    }

    /*
    [] : 변수 새로 설정할 때
    const [loginMember, setLoginMember] = useContext(AuthContext);
    {} : 외부에서 작성된 변수 가져와서 사용할 때
    const {loginMember, setLoginMember} = useContext(AuthContext);
    */
    return(
        <header>
        <h1>헤더부분</h1>
        <nav>
            {/*loginMember가 ? (존재할경우) : (존재하지않을경우)*/}
            {loginMember ? (
                <div>
                    <span>환영합니다.{loginMember.name}님</span>
                    <button onClick={handle로그아웃기능}>로그아웃</button>
                </div>) : (
                    <div>
                        <Link to="/login">로그인하기</Link>
                        <Link to="/api/naver">회원가입하기</Link>
                    </div>
                )}
        </nav>
        </header>
    )
}
export default Header;