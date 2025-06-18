import React, { useEffect, useState } from 'react';
import ISEPLogoBranco from '../../assets/images/ISEP_logo-branco.png';
import '../../styles/Form.css';
import '../../styles/CourseEditionAverageGradePage.css';
import { Link } from 'react-router-dom';
import CourseEditionAverageGradeSuccessModal from './CourseEditionAverageGradeSuccessModal';
import CourseEditionAverageGradeErrorModal from './CourseEditionAverageGradeErrorModal';

export default function CourseEditionAverageGradeForm() {
    const [programmes, setProgrammes] = useState([]);
    const [courses, setCourses] = useState([]);
    const [schoolYears, setSchoolYears] = useState([]);

    const [selectedProgramme, setSelectedProgramme] = useState('');
    const [selectedCourse, setSelectedCourse] = useState('');
    const [selectedSchoolYear, setSelectedSchoolYear] = useState('');
    const [courseObj, setCourseObj] = useState(null);

    const [showSuccessModal, setShowSuccessModal] = useState(false);
    const [showErrorModal, setShowErrorModal] = useState(false);
    const [averageGrade, setAverageGrade] = useState(null);
    const [errorStatusCode, setErrorStatusCode] = useState(null);

    const [isLoading, setIsLoading] = useState(true);
    const [loading, setLoading] = useState(false);

    useEffect(() => {
        fetch(`${process.env.REACT_APP_API_URL}/programmes`)
            .then(response => response.json())
            .then(data => {
                setProgrammes(Array.isArray(data) ? data : []);
                setIsLoading(false);
            })
            .catch(error => {
                console.error('Error fetching Programmes:', error);
                setIsLoading(false);
            });
    }, []);

    useEffect(() => {
        if (selectedProgramme) {
            fetch(`${process.env.REACT_APP_API_URL}/courses-in-study-plan/programmeID/${selectedProgramme}`)
                .then(response => response.json())
                .then(data => {
                    setCourses(data);
                    setSelectedCourse('');
                    setSchoolYears([]);
                    setSelectedSchoolYear('');
                    setCourseObj(null);
                })
                .catch(error => console.error('Error fetching Courses:', error));
        }
    }, [selectedProgramme]);

    useEffect(() => {
        if (selectedCourse && courseObj) {
            const url = `${process.env.REACT_APP_API_URL}/course-editions/school-years?programmeAcronym=${selectedProgramme}&courseAcronym=${selectedCourse}&courseName=${encodeURIComponent(courseObj.courseName)}`;
            fetch(url)
                .then(response => response.json())
                .then(data => {
                    setSchoolYears(data);
                    setSelectedSchoolYear('');
                })
                .catch(error => console.error('Error fetching SchoolYears:', error));
        }
    }, [selectedCourse, courseObj]);

    const handleProgrammeChange = (e) => {
        setSelectedProgramme(e.target.value);
    };

    const handleCourseChange = (e) => {
        const acr = e.target.value;
        const found = courses.find(c => c.courseAcronym === acr);
        setSelectedCourse(acr);
        setCourseObj(found || null);
    };

    const handleSchoolYearChange = (e) => {
        setSelectedSchoolYear(e.target.value);
    };

    const handleClear = () => {
        setSelectedProgramme('');
        setSelectedCourse('');
        setSelectedSchoolYear('');
        setCourseObj(null);
        setAverageGrade(null);
        setShowSuccessModal(false);
        setShowErrorModal(false);
        setErrorStatusCode(null);
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        setLoading(true);
        setShowErrorModal(false);
        setShowSuccessModal(false);
        setErrorStatusCode(null);

        if (!selectedProgramme || !selectedCourse || !selectedSchoolYear || !courseObj) {
            alert('Please select all fields.');
            setLoading(false);
            return;
        }

        const url = `${process.env.REACT_APP_API_URL}/course-editions/averagegrade?programmeAcronym=${selectedProgramme}&schoolYearId=${selectedSchoolYear}&courseAcronym=${selectedCourse}&courseName=${encodeURIComponent(courseObj.courseName)}&localDate=${encodeURIComponent(courseObj.studyPlanDate)}`;

        fetch(url)
            .then(async response => {
                if (!response.ok) {
                    const statusCode = response.status;
                    throw { statusCode };
                }

                const data = await response.json();
                setAverageGrade(data.averageGrade);
                setShowSuccessModal(true);
            })
            .catch(error => {
                const status = error.statusCode || 500;
                setErrorStatusCode(status);
                setShowErrorModal(true);
                console.error('Erro ao buscar média:', error);
            })
            .finally(() => setLoading(false));
    };

    return (
        <div className="form-main-component-div">
            <div className="form-main-grid">
                <div className="form-img-main-div course-edition-img-background">
                    <div className="form-logo-img-div">
                        <img src={ISEPLogoBranco} alt="ISEP Logo" />
                    </div>
                </div>

                <form className="form" onSubmit={handleSubmit} autoComplete="off">
                    <div className="course-edition-average-grade-header-row">
                        <h1>Average Grade</h1>
                        <Link to="/" className="pagination-btn2 pagination-btn-secondary">
                            Back to Home Page
                        </Link>
                    </div>

                    <div className="course-edition-average-grade-form-group">
                        <label htmlFor="programme" className="form-label">Programme</label>
                        <select
                            id="programme"
                            value={selectedProgramme}
                            onChange={handleProgrammeChange}
                            required
                            className="course-edition-average-grade-form-input"
                        >
                            <option value="" disabled hidden>Select Programme</option>
                            {programmes.map(p => (
                                <option key={p.acronym} value={p.acronym}>
                                    {p.acronym} - {p.name}
                                </option>
                            ))}
                        </select>
                    </div>

                    <div className="course-edition-average-grade-form-group">
                        <label htmlFor="course" className="form-label">Course</label>
                        <select
                            id="course"
                            value={selectedCourse}
                            onChange={handleCourseChange}
                            required
                            disabled={!selectedProgramme}
                            className="course-edition-average-grade-form-input"
                        >
                            <option value="" disabled hidden>Select Course</option>
                            {courses.map(c => (
                                <option key={c.courseAcronym} value={c.courseAcronym}>
                                    {c.courseAcronym} - {c.courseName}
                                </option>
                            ))}
                        </select>
                    </div>

                    <div className="course-edition-average-grade-form-group">
                        <label htmlFor="schoolYear" className="form-label">School Year</label>
                        <select
                            id="schoolYear"
                            value={selectedSchoolYear}
                            onChange={handleSchoolYearChange}
                            required
                            disabled={!selectedCourse}
                            className="course-edition-average-grade-form-input"
                        >
                            <option value="" disabled hidden>Select School Year</option>
                            {schoolYears.map(sy => (
                                <option key={sy.id} value={sy.id}>
                                    {sy.description}
                                </option>
                            ))}
                        </select>
                    </div>

                    <div className="form-actions">
                        <button
                            type="button"
                            className="btn btn-secondary"
                            onClick={handleClear}
                            disabled={loading}
                        >
                            CLEAR
                        </button>
                        <button
                            type="submit"
                            className="btn btn-primary btn-fixed-width"
                            disabled={loading || !selectedSchoolYear}
                        >
                            {loading ? "Loading..." : "GET AVERAGE"}
                        </button>
                    </div>
                </form>
            </div>

            {showSuccessModal && (
                <CourseEditionAverageGradeSuccessModal
                    averageGrade={averageGrade}
                    onClose={() => setShowSuccessModal(false)}
                />
            )}

            {showErrorModal && (
                <CourseEditionAverageGradeErrorModal
                    statusCode={errorStatusCode}
                    onClose={() => setShowErrorModal(false)}
                />
            )}
        </div>
    );
}