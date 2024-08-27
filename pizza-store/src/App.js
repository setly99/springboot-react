import {BrowserRouter as Router, Route, Routes} from 'react-router-dom';
import PizzaList from './components/PizzaList';
import PizzaForm from './components/PizzaForm';
import PizzaRouter from './components/PizzaRouter';

function App() {
  return (
    <Router>
      <Routes>
        <Route path='/' element={<PizzaRouter/>} />
        <Route path='/pizza-detail' element={<PizzaList/>} />
        {/*<Route path='/search' element={<PizzaList/>} />*/}
        <Route path='/pizzaForm' element={<PizzaForm/>} />
      </Routes>
    </Router>
    
  );
}

export default App;
