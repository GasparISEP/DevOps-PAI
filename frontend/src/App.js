import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Home from './pages/Home';
import RegisterStudentPage from './pages/studentPage/RegisterStudentPage'
import ProgrammeEnrolmentPage from './pages/studentPage/ProgrammeEnrolmentPage';
import './styles/RegisterStudentPage.css'
import RegisterProgrammePage from "./pages/RegisterProgrammePage";
import './styles/RegisterProgrammePage.css'
import RegisterTeacherPage from "./pages/teacherPage/RegisterTeacherPage";
import './styles/RegisterTeacherPage.css'
import DisplayTeacherPage from "./pages/teacherPage/DisplayTeacherPage";
import './styles/DisplayTeacherPage.css'
import RegisterCourseInStudyPlan from "./pages/RegisterCourseInStudyPlanPage";
import './styles/RegisterCourseInStudyPlanPage.css'
import AppRoutes from "./AppRoutes";

function App() {
  return (
      <Router>
          <AppRoutes />
      </Router>
  );
}

export default App;
