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
    const [courseEditions, setCourseEditions] = useState([]);
    const [schoolYears, setSchoolYears] = useState([]);
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
                const [programmeRes, editionRes, schoolYearRes] = await Promise.all([
                    fetch(`${process.env.REACT_APP_API_URL}/programmes`),
                    fetch(`${process.env.REACT_APP_API_URL}/course-editions`),
                    fetch(`${process.env.REACT_APP_API_URL}/school-years`)
                ]);

                const programmesData = await programmeRes.json();
                const courseEditionsData = await editionRes.json();
                const schoolYearsDataRaw = await schoolYearRes.json();
                const schoolYearsData = schoolYearsDataRaw._embedded?.currentSchoolYearDTOList || [];

                setProgrammes(programmesData);
                setCourseEditions(courseEditionsData);
                setSchoolYears(schoolYearsData);
            } catch (err) {
                console.error("Failed to fetch initial data", err);
                setProgrammes([]);
                setCourseEditions([]);
                setSchoolYears([]);
            } finally {
                setIsLoading(false);
            }
        }
        fetchData();
    }, []);

    const handleClear = () => {
        setSelectedProgramme('');
        setSelectedCourse('');
        setSelectedSchoolYear('');
        setAverageGrade(null);
        setError('');
    };

    const schoolYearMap = Object.fromEntries(
        schoolYears.map(y => [y.id, y.description])
    );

    const filteredByProgramme = courseEditions.filter(
        ce => ce.programmeAcronym?.trim().toLowerCase() === selectedProgramme?.trim().toLowerCase()
    );

    const uniqueCourses = Array.from(
        new Map(
            filteredByProgramme.map(ce => [
                `${ce.courseAcronym}|${ce.courseName}`,
                { acronym: ce.courseAcronym, name: ce.courseName }
            ])
        ).values()
    );

    const filteredByCourse = filteredByProgramme.filter(
        ce => ce.courseAcronym === selectedCourse
    );

    const uniqueSchoolYears = Array.from(
        new Set(filteredByCourse.map(ce => ce.schoolYearID))
    );

    const findCourseEditionLink = () => {
        const match = courseEditions.find(ce =>
            ce.programmeAcronym === selectedProgramme &&
            ce.courseAcronym === selectedCourse &&
            ce.schoolYearID === selectedSchoolYear
        );

        if (!match) return null;

        // ✅ Corrigir para formato dd-MM-yyyy
        const dateObject = new Date(match.studyPlanImplementationDate);
        const localeDate = dateObject.toLocaleDateString('pt-PT'); // "01/09/2025"
        const [day, month, year] = localeDate.split('/');
        const formattedForBackend = `${day}-${month}-${year}`;

        const url = new URL(`${process.env.REACT_APP_API_URL}/course-editions/average-grade`);
        url.searchParams.set("programmeAcronym", match.programmeAcronym);
        url.searchParams.set("courseAcronym", match.courseAcronym);
        url.searchParams.set("schoolYearId", match.schoolYearID);
        url.searchParams.set("studyPlanDate", formattedForBackend);

        return url.toString();
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
            setError(`Failed to fetch average grade: ${err.message || err}`);
            setShowErrorModal(true);
        } finally {
            setLoading(false);
        }
    };

    const renderOptions = (items, getLabel, getValue) =>
        items.map(item => (
            <option key={getValue(item)} value={getValue(item)}>
                {getLabel(item)}
            </option>
        ));

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
                                    onChange={e => {
                                        setSelectedProgramme(e.target.value);
                                        setSelectedCourse('');
                                        setSelectedSchoolYear('');
                                    }}
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
                                    onChange={e => {
                                        setSelectedCourse(e.target.value);
                                        setSelectedSchoolYear('');
                                    }}
                                    required
                                    disabled={!selectedProgramme}
                                    className="course-edition-average-grade-form-input"
                                >
                                    <option value="" disabled hidden>Select Course</option>
                                    {renderOptions(uniqueCourses, c => `${c.acronym} - ${c.name}`, c => c.acronym)}
                                </select>
                            </div>

                            <div className="course-edition-average-grade-form-group">
                                <label htmlFor="schoolYear" className="form-label">School Year</label>
                                <select
                                    id="schoolYear"
                                    value={selectedSchoolYear}
                                    onChange={e => setSelectedSchoolYear(e.target.value)}
                                    required
                                    disabled={!selectedCourse}
                                    className="course-edition-average-grade-form-input"
                                >
                                    <option value="" disabled hidden>Select School Year</option>
                                    {renderOptions(
                                        uniqueSchoolYears,
                                        id => schoolYearMap[id] || id,
                                        id => id
                                    )}
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