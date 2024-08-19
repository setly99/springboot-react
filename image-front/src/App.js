import {BrowserRouter as Router, Routes, Route} from 'react-router-dom'
import Board from './component/Board';
import Profile from './component/Profile';
import Main from './component/Main';
import Header from './component/layout/Header';
import Footer from './component/layout/Footer';
import Banner from './component/layout/Banner';
//frontend api url 설정
//- react router dom Router

//fontend api url
//Board path = "/board"
//Profile path = "/profile"
function App(){

    return(

    <Router>
        <Banner/>
        <Header/>
        <Routes>
            <Route path='/' element={<Main/>}>게시판</Route>
            <Route path='/board' element={<Board/>}>게시판</Route>
            <Route path='/profile' element={<Profile/>}>게시판</Route>
        </Routes>
        <Footer/>
    </Router>

    );
}
export default App;