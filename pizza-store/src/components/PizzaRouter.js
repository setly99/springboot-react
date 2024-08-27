import { useState } from "react";
import Modal from "./Modal";
import PizzaForm from "./PizzaForm";
import { useNavigate } from "react-router-dom";
import PizzaList from "./PizzaList";
import '../css/PizzaRouter.css';


const PizzaRouter = () => {
    /*모달 관련 변수와 기능들 */
    const [isModalOpen, setIsModalOpen] = useState(false);//닫음처리
    const openModal = () => setIsModalOpen(true);
    const closeModal = () => setIsModalOpen(false);

    /*검색 관련 변수와 기능들 */
    const [search, setSearch] = useState('');

    //검색하면 검색 위한 페이지로 이동
    const navigate = useNavigate();
    const handleSearch = () => {
        navigate(`/search?query=${search}`);
    }

    return(
        <div className="app-container">
            <h1>피자 메뉴 검색하기</h1>
            <div className="search-container">
                <input type="text" placeholder="검색할메뉴입력"
                value={search}
                onChange={(e) => setSearch(e.target.value)}
                />
                <button onClick={handleSearch}>검색하기</button>
            </div>
            <button onClick={openModal}>메뉴 등록하기</button>
            <Modal isOpen={isModalOpen} onClose={closeModal}>
                <PizzaForm/>
            </Modal>
            <PizzaList/>
        </div>
    )
}
export default PizzaRouter;