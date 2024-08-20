import logo from './logo.svg';
import './App.css';
import ChickenList from './component/ChickenList';
import ChickenForm from './component/ChickenForm';
import Modal from './component/Modal';
import { useState } from 'react';

function App() {

  const [isModalOpen, setIsModalOpen] = useState(false);

  const openModal = () => setIsModalOpen(true);

  const closeModal = () => setIsModalOpen(false);

  return (
    <div className="app-container">
      <h1>치킨가게메뉴관리</h1>
      <ChickenList/>
      <button onClick={openModal}>메뉴등록하기</button>
      <Modal isOpen={isModalOpen} onClose={closeModal}>
        <ChickenForm/>
      </Modal>
    </div>
  );
}

export default App;
