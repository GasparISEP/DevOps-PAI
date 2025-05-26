import logo from './logo.svg';
import './App.css';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Home from './pages/Home';
import './styles/Main.css'
import RegisterStudentPage from './pages/RegisterStudentPage'
import './styles/RegisterStudentPage.css'
import RegisterProgrammePage from "./pages/RegisterProgrammePage";
import './styles/RegisterProgrammePage.css'
import RegisterTeacherPage from "./pages/RegisterTeacherPage";
import TeacherDisplay from './components/teacherComponent/TeacherDisplay';

function App() {
  return (
      <Router>
          <div className="App">
              <Routes>
                  {/* Página inicial */}
                  <Route path="/" element={<Home />} />

                  {/* Teacher Registration Form */}
                  <Route path="/teachers/register" element={<RegisterTeacherPage />} />
                  {/* Teacher Display */}
                  <Route path="/teachers/all" element={<TeacherDisplay />} />

                  {/* Formulário de estudantes */}
                  <Route
                      path="/students"
                      element={<RegisterStudentPage />}
                  />

                  {/* Formulário de programas */}
                  <Route path="/programmes" element={<RegisterProgrammePage />} />
              </Routes>
          </div>
      </Router>
  );
}

export default App;
