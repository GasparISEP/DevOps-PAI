// AppRoutes.js
import { Routes, Route } from 'react-router-dom';
import Home from './pages/Home';
import RegisterStudentPage from './pages/studentPage/RegisterStudentPage';
import RegisterProgrammePage from './pages/RegisterProgrammePage';
import RegisterTeacherPage from './pages/teacherPage/RegisterTeacherPage';
import DisplayTeacherPage from './pages/teacherPage/DisplayTeacherPage';
import RegisterCourseInStudyPlanPage from './pages/RegisterCourseInStudyPlanPage';
import DisplayStudentPage from "./pages/studentPage/DisplayStudentPage";
import DisplayCourseEditionPage from "./pages/CourseEditionPage/DisplayCourseEditionPage";
import RegisterGradeStudentPage from "./pages/gradeStudentPage/RegisterGradeStudentPage";


function AppRoutes() {
    return (
        <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/teachers/register" element={<RegisterTeacherPage />} />
            <Route path="/teachers/display" element={<DisplayTeacherPage />} />
            <Route path="/students/register" element={<RegisterStudentPage />} />
            <Route path="/students/display" element={<DisplayStudentPage />} />
            <Route path="/programmes" element={<RegisterProgrammePage />} />
            <Route path="/courses" element={<RegisterCourseInStudyPlanPage />} />
            <Route path="/courseeditions/display" element={<DisplayCourseEditionPage />} />
            <Route path="/courseeditions/register-grade-student" element={<RegisterGradeStudentPage />} />
        </Routes>
    );
}

export default AppRoutes;
