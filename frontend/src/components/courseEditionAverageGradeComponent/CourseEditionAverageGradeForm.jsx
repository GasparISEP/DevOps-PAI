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
    const [isLoading, setIsLoading] = useState(true);

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
            setIsLoading(true);
            try {
                const [programmeRes, courseRes, yearRes, editionRes] = await Promise.all([
                    fetch(`${process.env.REACT_APP_API_URL}/programmes`),
                    fetch(`${process.env.REACT_APP_API_URL}/courses/ids`),
                    fetch(`${process.env.REACT_APP_API_URL}/school-years`),
                    fetch(`${process.env.REACT_APP_API_URL}/course-editions`)
                ]);

                const parseArrayResponse = async (response) => {
                    const data = await response.json();
                    return Array.isArray(data) ? data : [];
                };

                const parseEmbeddedResponse = async (response, key) => {
                    const data = await response.json();
                    return data._embedded?.[key] || [];
                };

                setProgrammes(await parseArrayResponse(programmeRes));
                setCourses(await parseArrayResponse(courseRes));
                setSchoolYears(await parseEmbeddedResponse(yearRes, 'currentSchoolYearDTOList'));
                setCourseEditions(await parseEmbeddedResponse(editionRes, 'courseEditionDTOList'));
            } catch (err) {
                console.error("Failed to fetch initial data", err);
                setProgrammes([]);
                setCourses([]);
                setSchoolYears([]);
                setCourseEditions([]);
            } finally {
                setIsLoading(false);
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

    const renderOptions = (items, getLabel = item => item.name, getValue = item => item.id) => {
        if (!Array.isArray(items)) return null;

        return items.map(item => (
            <option key={getValue(item)} value={getValue(item)}>
                {getLabel(item)}
            </option>
        ));
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

                    {isLoading ? (
                        <div className="loading-spinner">Loading data...</div>
                    ) : (
                        <>
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
                                    {renderOptions(programmes, p => `${p.acronym} - ${p.name}`, p => p.acronym)}
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
                                    {renderOptions(courses, c => `${c.acronym} - ${c.name}`, c => c.acronym)}
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
                                    {renderOptions(schoolYears, y => y.description, y => y.id)}
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
                        </>
                    )}
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