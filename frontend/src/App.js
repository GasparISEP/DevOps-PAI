import logo from './logo.svg';
import './App.css';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Home from './pages/Home';
import RegisterStudentPage from './pages/RegisterStudentPage'
import './styles/RegisterStudentPage.css'
import RegisterProgrammePage from "./pages/RegisterProgrammePage";
import './styles/RegisterProgrammePage.css'



function App() {
  return (
      <Router>
          <div className="App">
              <Routes>
                  {/* Página inicial */}
                  <Route path="/" element={<Home />} />

                  {/* Formulário de estudantes */}
                  <Route
                      path="/students"
                      element={<RegisterStudentPage />}
                  />

                  {/* Formulário de programas */}
                  <Route path="/programme" element={<RegisterProgrammePage />} />
              </Routes>
          </div>
      </Router>
  );
}

export default App;
