import './App.css';
import {BrowserRouter as Router, Route, Routes} from 'react-router-dom';
import PizzaList from './components/PizzaList';
import PizzaForm from './components/PizzaForm';

function App() {
  return (
    <Router>
      <Routes>
        <Route path='/' element={<PizzaList/>} />
        <Route path='/pizzaForm' element={<PizzaForm/>} />
      </Routes>
    </Router>
    
  );
}

export default App;
