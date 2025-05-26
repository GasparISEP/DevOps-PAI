import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Home from './pages/Home';
import './styles/Main.css'
import RegisterStudentPage from './pages/RegisterStudentPage'
import './styles/RegisterStudentPage.css'
import RegisterProgrammePage from "./pages/RegisterProgrammePage";
import './styles/RegisterProgrammePage.css'
import RegisterTeacherPage from "./pages/RegisterTeacherPage";



function App() {
  return (
      <Router>
          <>
              <Routes>
                  {/* Página inicial */}
                  <Route path="/" element={<Home />} />

                  {/* Teacher Registration Form */}
                  <Route path="/teachers" element={<RegisterTeacherPage />} />

                  {/* Formulário de estudantes */}
                  <Route
                      path="/students"
                      element={<RegisterStudentPage />}
                  />

                  {/* Formulário de programas */}
                  <Route path="/programmes" element={<RegisterProgrammePage />} />
              </Routes>
          </>
      </Router>
  );
}

export default App;
