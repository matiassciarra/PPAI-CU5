import { BrowserRouter, Route, Routes } from "react-router-dom";
import Inicio from './components/Inicio.jsx';
import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';

function App() {
  return (
    <div id="root">
      <BrowserRouter>
        <div>
          <Routes>
            <Route path="/inicio" element={<Inicio />} />
            <Route path="/" element={<Inicio />} />
          </Routes>
        </div>
      </BrowserRouter>
    </div>
  );
}

export default App;