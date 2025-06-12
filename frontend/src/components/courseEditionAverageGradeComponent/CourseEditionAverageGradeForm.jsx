import React, { useEffect, useState } from 'react';
import { fetchAverageGradeFromLink } from '../../services/courseEditionAverageGradeService';
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
    const [courseEditions, setCourseEditions] = useState([]);

    const [selectedProgramme, setSelectedProgramme] = useState('');
    const [selectedCourse, setSelectedCourse] = useState('');
    const [selectedSchoolYear, setSelectedSchoolYear] = useState('');

    const [courseEditionLink, setCourseEditionLink] = useState(null);
    const [averageGrade, setAverageGrade] = useState(null);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState('');
    const [showSuccessModal, setShowSuccessModal] = useState(false);
    const [showErrorModal, setShowErrorModal] = useState(false);

    useEffect(() => {
        async function fetchData() {
            try {
                const [programmeRes, courseRes, yearRes, editionRes] = await Promise.all([
                    fetch(`${process.env.REACT_APP_API_URL}/programmes`),
                    fetch(`${process.env.REACT_APP_API_URL}/courses`),
                    fetch(`${process.env.REACT_APP_API_URL}/schoolyears`),
                    fetch(`${process.env.REACT_APP_API_URL}/course-editions`)
                ]);

                setProgrammes(await programmeRes.json());
                setCourses(await courseRes.json());
                setSchoolYears(await yearRes.json());
                setCourseEditions(await editionRes.json());
            } catch (err) {
                console.error("Failed to fetch initial data", err);
            }
        }
        fetchData();
    }, []);

    const findCourseEditionLink = () => {
        const match = courseEditions.find(ce =>
            ce.programmeID === selectedProgramme &&
            ce.courseID === selectedCourse &&
            ce.schoolYearID === selectedSchoolYear
        );
        return match?._links?.averageGrade?.href || null;
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        setLoading(true);
        setError('');
        setAverageGrade(null);

        try {
            const link = findCourseEditionLink();
            if (!link) throw new Error('Matching course edition not found.');
            setCourseEditionLink(link);
            const data = await fetchAverageGradeFromLink(link);
            setAverageGrade(data.averageGrade);
            setShowSuccessModal(true);
        } catch (err) {
            setError(err.message || 'An unexpected error occurred');
            setShowErrorModal(true);
        } finally {
            setLoading(false);
        }
    };

    const handleClear = () => {
        setSelectedProgramme('');
        setSelectedCourse('');
        setSelectedSchoolYear('');
        setAverageGrade(null);
        setError('');
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
                    {/* Ajuste de header com alinhamento correto */}
                    <div className="course-edition-average-grade-header-row">
                        <h1>Average Grade</h1>
                        <Link to="/" className="pagination-btn2 pagination-btn-secondary">
                            Back to Home Page
                        </Link>
                    </div>

                    {/* Adição de margem entre os campos */}
                    <div className="course-edition-average-grade-form-group">
                        <label htmlFor="programme" className="form-label">Programme</label>
                        <select
                            id="programme"
                            value={selectedProgramme}
                            onChange={e => setSelectedProgramme(e.target.value)}
                            required
                            className="course-edition-average-grade-form-input"
                        >
                            <option value="" disabled hidden>Select Programme</option>
                            {programmes.map(p => (
                                <option key={p.id} value={p.id}>{p.name}</option>
                            ))}
                        </select>
                    </div>

                    <div className="course-edition-average-grade-form-group">
                        <label htmlFor="course" className="form-label">Course</label>
                        <select
                            id="course"
                            value={selectedCourse}
                            onChange={e => setSelectedCourse(e.target.value)}
                            required
                            className="course-edition-average-grade-form-input"
                        >
                            <option value="" disabled hidden>Select Course</option>
                            {courses.map(c => (
                                <option key={c.id} value={c.id}>{c.name}</option>
                            ))}
                        </select>
                    </div>

                    <div className="course-edition-average-grade-form-group">
                        <label htmlFor="schoolYear" className="form-label">School Year</label>
                        <select
                            id="schoolYear"
                            value={selectedSchoolYear}
                            onChange={e => setSelectedSchoolYear(e.target.value)}
                            required
                            className="course-edition-average-grade-form-input"
                        >
                            <option value="" disabled hidden>Select School Year</option>
                            {schoolYears.map(sy => (
                                <option key={sy.id} value={sy.id}>{sy.designation}</option>
                            ))}
                        </select>
                    </div>

                    <div className="form-actions">
                        <button type="button" className="btn btn-secondary" onClick={handleClear} disabled={loading}>
                            CLEAR
                        </button>
                        <button
                            type="submit"
                            className="btn btn-primary btn-fixed-width"
                            disabled={loading || !selectedProgramme || !selectedCourse || !selectedSchoolYear}
                        >
                            {loading ? (
                                <>
                                    <div className="average-grade-spinner" style={{ marginRight: '0.8rem' }}></div>
                                    Loading...
                                </>
                            ) : 'GET AVERAGE'}
                        </button>
                    </div>
                </form>
            </div>

            {showSuccessModal && (
                <CourseEditionAverageGradeSuccessModal
                    courseEditionId={courseEditionLink}
                    averageGrade={averageGrade}
                    onClose={() => setShowSuccessModal(false)}
                />
            )}

            {showErrorModal && (
                <CourseEditionAverageGradeErrorModal
                    error={error}
                    onClose={() => setShowErrorModal(false)}
                />
            )}
        </div>
    );
}
