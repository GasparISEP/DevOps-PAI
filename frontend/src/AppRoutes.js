// AppRoutes.js
import { Routes, Route } from 'react-router-dom';
import Home from './pages/Home';
import RegisterStudentPage from './pages/RegisterStudentPage';
import RegisterProgrammePage from './pages/RegisterProgrammePage';
import RegisterTeacherPage from './pages/teacherPage/RegisterTeacherPage';
import DisplayTeacherPage from './pages/teacherPage/DisplayTeacherPage';

function AppRoutes() {
    return (
        <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/teachers/register" element={<RegisterTeacherPage />} />
            <Route path="/teachers/display" element={<DisplayTeacherPage />} />
            <Route path="/students" element={<RegisterStudentPage />} />
            <Route path="/programmes" element={<RegisterProgrammePage />} />
        </Routes>
    );
}

export default AppRoutes;
