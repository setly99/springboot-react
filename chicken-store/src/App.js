import {BrowserRouter as Router, Route, Routes} from 'react-router-dom';
import './App.css';
import ChickenList from './component/ChickenList';
import ChickenForm from './component/ChickenForm';
import ChickenDetail from './component/ChickenDetail';
import Modal from './component/Modal';
import { useState } from 'react';
import MainRouter from './MainRouter';
import SearchResult from './component/SearchResult';

function App() {

  const [isModalOpen, setIsModalOpen] = useState(false);

  const openModal = () => setIsModalOpen(true);

  const closeModal = () => setIsModalOpen(false);

  return (
    <Router>
      
      <Routes>
        <Route path='/' element={<MainRouter/>} />
        {/*<Route path='/' element={<ChickenList/>} />*/}
        {/*Routes 안에는 Route로 설정된 태그만 들어올 수 있음 <MainRouter/> */}
        <Route path='/chicken-detail/:id' element={<ChickenDetail/>} />
        <Route path='/search' element={<SearchResult/>} />
      </Routes>
    </Router>
  );
}

export default App;
