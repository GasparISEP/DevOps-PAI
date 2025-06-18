import { Routes, Route } from 'react-router-dom';
import Home from './pages/Home';
import RegisterStudentPage from './pages/studentPage/RegisterStudentPage';
import ProgrammeEnrolmentPage from './pages/studentPage/ProgrammeEnrolmentPage';
import EnrollStudentInCoursesPage from './pages/studentPage/EnrollStudentInCoursesPage';
import StudentEnrolledCoursesPage from './pages/studentPage/StudentEnrolledCoursesPage';
import StudentProgrammeEditionsPage from './pages/studentPage/StudentProgrammeEditionsPage';
import RegisterProgrammePage from './pages/RegisterProgrammePage';
import RegisterTeacherPage from './pages/teacherPage/RegisterTeacherPage';
import DisplayTeacherPage from './pages/teacherPage/DisplayTeacherPage';
import RegisterCourseInStudyPlanPage from './pages/RegisterCourseInStudyPlanPage';
import DisplayStudentPage from "./pages/studentPage/DisplayStudentPage";
import DisplayCourseEditionPage from "./pages/CourseEditionPage/DisplayCourseEditionPage";
import RegisterGradeStudentPage from "./pages/gradeStudentPage/RegisterGradeStudentPage";
import CourseEditionAverageGradePage from "./pages/CourseEditionAverageGradePage";
import TeacherCareerProgressionComponent from './components/teacherCareerProgressionComponent/TeacherCareerProgressionComponent';
import DefineRucPage from "./pages/CourseEditionPage/DefineRucPage";
import UpdateTeacherCategoryPage from './pages/teacherCategoryPage/UpdateTeacherCategoryPage';
import DisplayTeacherCareerProgressionPage from "./pages/DisplayTeacherCareerProgression";
import RemoveStudentPage from "./pages/studentPage/RemoveStudentPage";
import StudentDetailsComponent from './components/studentComponent/StudentDetailsComponent';
import DefineRucDetailsComponent from "./components/defineRucInCourseEditionComponent/DefineRucDetailsComponent";
import ProgrammeEnrolmentDetails from './components/studentComponent/ProgrammeEnrolmentDetails';


function AppRoutes() {
        return (
            <Routes>
                    <Route path="/" element={<Home />} />
                    <Route path="/students/register" element={<RegisterStudentPage />} />
                    <Route path="/students/display" element={<DisplayStudentPage />} />
                    <Route path="/students/enrol-programme" element={<ProgrammeEnrolmentPage />} />
                    <Route path="/students/enroll" element={<EnrollStudentInCoursesPage />} />
                    <Route path="/students/enrolled-courses" element={<StudentEnrolledCoursesPage />} />
                    <Route path="/students/programme-editions" element={<StudentProgrammeEditionsPage />} />
                    <Route path="/students/removal" element={<RemoveStudentPage />} />
                    <Route path="/teachers/register" element={<RegisterTeacherPage />} />
                    <Route path="/teachers/display" element={<DisplayTeacherPage />} />
                    <Route path="/teachers/update-category" element={<UpdateTeacherCategoryPage />} />
                    <Route path="/teachers/DisplayTeacherCareerProgression" element={<DisplayTeacherCareerProgressionPage />} />
                    <Route path="/teacher-career-progressions" element={<DisplayTeacherCareerProgressionPage />} />
                    <Route path="/programmes" element={<RegisterProgrammePage />} />
                    <Route path="/courses" element={<RegisterCourseInStudyPlanPage />} />
                    <Route path="/courseeditions/display" element={<DisplayCourseEditionPage />} />
                    <Route path="/courseeditions/register-grade-student" element={<RegisterGradeStudentPage />} />
                    <Route path="/course-editions/average-grade" element={<CourseEditionAverageGradePage />} />
                    <Route path="/teacher-career-progressions/:id" element={<TeacherCareerProgressionComponent />} />
                    <Route path="/course-editions/Define-ruc" element={<DefineRucPage />} />
                    <Route path="/students/:id" element={<StudentDetailsComponent />} />
                    <Route path="/course-editions/by-id/:id" element={<DefineRucDetailsComponent/>} />
                    <Route path="/students/programme-enrolment/:id" element={<ProgrammeEnrolmentDetails />} />

            </Routes>
        );
}

export default AppRoutes;