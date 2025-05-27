import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Home from './pages/Home';
import './styles/Main.css'
import RegisterStudentPage from './pages/RegisterStudentPage'
import './styles/RegisterStudentPage.css'
import RegisterProgrammePage from "./pages/RegisterProgrammePage";
import './styles/RegisterProgrammePage.css'
import RegisterTeacherPage from "./pages/teacherPage/RegisterTeacherPage";
import './styles/RegisterTeacherPage.css'
import DisplayTeacherPage from "./pages/teacherPage/DisplayTeacherPage";
import './styles/DisplayTeacherPage.css'

function App() {
  return (
      <Router>
              <Routes>
                  {/* Página inicial */}
                  <Route path="/" element={<Home />} />

                  {/* Teacher Registration Form */}
                  <Route path="/teachers/register" element={<RegisterTeacherPage />} />
                  {/* Teacher Display */}
                  <Route path="/teachers/display" element={<DisplayTeacherPage />} />

                  {/* Formulário de estudantes */}
                  <Route
                      path="/students"
                      element={<RegisterStudentPage />}
                  />

                  {/* Formulário de programas */}
                  <Route path="/programmes" element={<RegisterProgrammePage />} />
              </Routes>
      </Router>
  );
}

export default App;
