import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import { registerCourseInStudyPlan } from '../../services/courseService';
import ISEPLogoBranco from '../../assets/images/ISEP_logo-branco.png';
import '../../styles/Form.css';

import ProgrammeSelector from './ProgrammeSelector';
import CourseSelector from './CourseSelector';
import SemesterSelector from './SemesterSelector';
import CurricularYearSelector from './CurricularYearSelector';
import DurationSelector from './DurationSelector';
import ECTSInput from './ECTSInput';

export default function CourseForm() {
    const [form, setForm] = useState({
        programme: '',
        course: '',
        curricularYear: '',
        semester: '',
        duration: '',
        qtdECTS: ''
    });

    const [courses, setCourses] = useState([]);
    const [programmes, setProgrammes] = useState([]);
    const [availableYears, setAvailableYears] = useState([]);
    const [availableSemesters, setAvailableSemesters] = useState([]);
    const [availableDurations, setAvailableDurations] = useState([]);
    const [maxECTS, setMaxECTS] = useState(30);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState('');
    const [errorMessage, setErrorMessage] = useState('');
    const [success, setSuccess] = useState(null);
    const [successData, setSuccessData] = useState(null);
    const [showModal, setShowModal] = useState(false);
    const [showErrorModal, setShowErrorModal] = useState(false);
    const [programmeSemesters, setProgrammeSemesters] = useState(null);
    const [programmeMaxECTS, setProgrammeMaxECTS] = useState(null);
    const [usedECTS, setUsedECTS] = useState(0);

    const cleanNumber = (val) => Number(String(val).replace(/[^\d.-]/g, ''));

    useEffect(() => {
        async function fetchOptions() {
            try {
                const [programmeRes, courseRes] = await Promise.all([
                    fetch(`${process.env.REACT_APP_API_URL}/programmes/ids`),
                    fetch(`${process.env.REACT_APP_API_URL}/courses/ids`)
                ]);

                const programmeData = await programmeRes.json();
                const courseData = await courseRes.json();

                setProgrammes(Array.isArray(programmeData) ? programmeData : []);
                setCourses(Array.isArray(courseData) ? courseData : []);
            } catch (err) {
                console.error("Failed to load dropdown options:", err);
                setError("Failed to load programme or course data.");
            }
        }

        fetchOptions();
    }, []);

    useEffect(() => {
        if (form.curricularYear) {
            const year = Number(form.curricularYear);
            const semesters = [(year - 1) * 2 + 1, (year - 1) * 2 + 2];
            setAvailableSemesters(semesters);
            const selectedSemester = Number(form.semester);
            if (selectedSemester && programmeSemesters) {
                updateDurationsWithSemesterAndProgramme(selectedSemester, programmeSemesters);
            }
        } else {
            setAvailableSemesters([]);
            setAvailableDurations([]);
        }
    }, [form.curricularYear, programmeSemesters, form.semester]);

    useEffect(() => {
        async function fetchAndCalculateUsedECTS() {
            if (!form.programme || !form.curricularYear || !form.semester) {
                setUsedECTS(0);
                return;
            }

            const selectedProgramme = programmes.find(p => p.acronym === form.programme);
            if (!selectedProgramme) {
                setUsedECTS(0);
                return;
            }

            try {
                const res = await fetch(`${process.env.REACT_APP_API_URL}/course-in-study-plan/${encodeURIComponent(selectedProgramme.acronym)}/${encodeURIComponent(selectedProgramme.name)}`);
                if (!res.ok) throw new Error('Failed to fetch courses in study plan');
                const courses = await res.json();

                // Filtra cursos para ano e semestre selecionados
                const filteredCourses = courses.filter(course =>
                    Number(course.curricularYear) === Number(form.curricularYear) &&
                    Number(course.semester) === Number(form.semester)
                );

                // Soma os ECTS usados nesse semestre
                const totalUsedECTS = filteredCourses.reduce((acc, curr) => acc + (Number(curr.credits) || 0), 0);

                setUsedECTS(totalUsedECTS);

            } catch (err) {
                console.error(err);
                setUsedECTS(0);
            }
        }

        fetchAndCalculateUsedECTS();
    }, [form.programme, form.curricularYear, form.semester]);


    const remainingECTS = (typeof maxECTS === 'number' && typeof usedECTS === 'number')
        ? maxECTS - usedECTS
        : 30;

    async function fetchProgrammeDetails(acronym, name) {
        try {
            const res = await fetch(`${process.env.REACT_APP_API_URL}/programmes/${encodeURIComponent(name)}/${encodeURIComponent(acronym)}`);
            const data = await res.json();

            if (data && data.quantSemesters?.quantityOfSemesters) {
                const quant = data.quantSemesters.quantityOfSemesters;
                const semesters = Array.from({ length: quant }, (_, i) => i + 1);
                const estimatedYears = Math.ceil(quant / 2);
                const years = Array.from({ length: estimatedYears }, (_, i) => i + 1);
                const durations = [1, 2];

                setAvailableSemesters(semesters);
                setAvailableYears(years);
                setAvailableDurations(durations);
                const totalProgrammeECTS = data.maxEcts?.maxEcts;

                const defaultECTSPerSemester = 30;
                const quantSemesters = data.quantSemesters?.quantityOfSemesters || 0;

                const ectsPerSemester = (totalProgrammeECTS && quantSemesters)
                    ? Math.round(totalProgrammeECTS / quantSemesters)
                    : defaultECTSPerSemester;

                setProgrammeMaxECTS(totalProgrammeECTS || null);
                setMaxECTS(ectsPerSemester);
                setProgrammeSemesters(quant);
            }
        } catch (err) {
            console.error("Failed to fetch programme details", err);
        }
    }

    function handleChange(e) {
        const {name, value} = e.target;

        setForm(prev => {
            const newForm = {...prev, [name]: value};

            if (name === "programme") {
                const selectedProgramme = programmes.find(p => p.acronym === value);
                if (selectedProgramme) {
                    fetchProgrammeDetails(selectedProgramme.acronym, selectedProgramme.name);
                    newForm.qtdECTS = '';
                } else {
                    setAvailableYears([]);
                    setAvailableSemesters([]);
                    setAvailableDurations([]);
                    setMaxECTS(null);
                    newForm.qtdECTS = '';
                }
            }

            if (name === "semester") {
                const parsedSemester = Number(value);
                updateDurationsWithSemesterAndProgramme(parsedSemester, programmeSemesters);
            }

            if (name === "qtdECTS") {
                let valNum = Number(value);

                if (isNaN(valNum) || valNum < 0) {

                    newForm.qtdECTS = '';
                } else if (remainingECTS !== null && valNum > remainingECTS) {

                    newForm.qtdECTS = String(remainingECTS);
                } else {
                    newForm.qtdECTS = value;
                }
            }

            return newForm;
        });

    }

    function handleClear() {
        setForm({
            programme: '',
            course: '',
            curricularYear: '',
            semester: '',
            duration: '',
            qtdECTS: ''
        });
        setAvailableYears([]);
        setAvailableSemesters([]);
        setAvailableDurations([]);
        setMaxECTS(null);
        setError('');
        setErrorMessage('');
        setSuccess(null);
        setSuccessData(null);
        setShowModal(false);
        setShowErrorModal(false);
    }

    function updateDurationsWithSemesterAndProgramme(selectedSemester, totalProgrammeSemesters) {
        if (!selectedSemester || !totalProgrammeSemesters) {
            setAvailableDurations([]);
            return;
        }

        const isLastSemester = selectedSemester === totalProgrammeSemesters;
        const isEvenSemester = selectedSemester % 2 === 0;

        if (isLastSemester || isEvenSemester) {
            setAvailableDurations([1]);
        } else {
            setAvailableDurations([1, 2]);
        }
    }

    async function handleSubmit(e) {
        e.preventDefault();
        setError('');
        setErrorMessage('');
        setSuccess(null);
        setSuccessData(null);
        setLoading(true);

        const parsedECTS = cleanNumber(form.qtdECTS);
        const parsedDuration = cleanNumber(form.duration);
        const parsedSemester = cleanNumber(form.semester);
        const parsedYear = cleanNumber(form.curricularYear);

        if (
            isNaN(parsedECTS) ||
            isNaN(parsedDuration) ||
            isNaN(parsedSemester) ||
            isNaN(parsedYear)
        ) {
            setError("All numeric fields must be filled and valid numbers.");
            setLoading(false);
            return;
        }

        if (parsedECTS > remainingECTS) {
            setError(`ECTS value cannot exceed the remaining ${remainingECTS} ECTS for this semester.`);
            setLoading(false);
            return;
        }

        const selectedProgramme = programmes.find(p => p.acronym === form.programme);
        const selectedCourse = courses.find(c => c.acronym === form.course);

        if (!selectedProgramme || !selectedCourse) {
            setError("Invalid programme or course selection.");
            setLoading(false);
            return;
        }

        const today = new Date();
        const studyPlanDate = `${String(today.getDate()).padStart(2, '0')}-${String(today.getMonth() + 1).padStart(2, '0')}-${today.getFullYear()}`;

        const payload = {
            semester: parsedSemester,
            curricularYear: parsedYear,
            courseAcronym: selectedCourse.acronym,
            courseName: selectedCourse.name,
            programmeAcronym: selectedProgramme.acronym,
            programmeName: selectedProgramme.name,
            studyPlanDate,
            duration: parsedDuration,
            credits: parsedECTS
        };

        try {
            const response = await registerCourseInStudyPlan(payload);
            setSuccess(response);
            setSuccessData(response);
            setShowModal(true);

        } catch (err) {
            const msg = err.message || "Failed to register the course.";
            setError(msg);
            setErrorMessage(msg);
            setShowErrorModal(true);
        } finally {
            setLoading(false);
        }
    }

    return (
        <div className="form-main-component-div">
            <div className="form-main-grid">
                <div className="form-img-main-div student-img-background">
                    <div className="form-logo-img-div">
                        <img src={ISEPLogoBranco} alt="ISEP Logo" />
                    </div>
                </div>

                <form className="form" onSubmit={handleSubmit}>
                    <div style={{ display: 'flex', alignItems: 'center', justifyContent: 'space-between', marginBottom: '2rem'}}>
                        <h1 style={{ margin: 0 }}>Register a Course</h1>
                        <Link to="/" className="pagination-btn2 pagination-btn-secondary" style={{textDecoration: 'none'}}>
                            Back to Home Page
                        </Link>
                    </div>

                    <div className="form-and-buttons-main-div">
                        <div className="form-div">
                            <ProgrammeSelector programmes={programmes} value={form.programme} onChange={handleChange} />
                            <CourseSelector courses={courses} value={form.course} onChange={handleChange} />
                            <CurricularYearSelector years={availableYears} value={form.curricularYear} onChange={handleChange} />
                            <SemesterSelector semesters={availableSemesters} value={form.semester} onChange={handleChange} />
                            <DurationSelector value={form.duration} onChange={handleChange} durations={availableDurations} />
                            <ECTSInput value={form.qtdECTS} onChange={handleChange} maxECTS={remainingECTS} />

                            {error && !showErrorModal && <div className="error">{error}</div>}

                            <div className="form-actions">
                                <button
                                    type="button"
                                    className="btn btn-secondary"
                                    onClick={handleClear}
                                >
                                    CLEAR
                                </button>
                                <button type="submit" className="btn btn-primary" disabled={loading}>
                                    {loading ? 'REGISTERING…' : 'REGISTER'}
                                </button>
                            </div>
                        </div>

                        {showModal && successData && (
                            <div className="modal-overlay">
                                <div className="modal-content">
                                    <h2>Success!</h2>
                                    <p>The course was registered successfully.</p>
                                    <div className="success" style={{ marginTop: '1rem', color: '#080' }}>
                                        <p><strong>Programme:</strong> {successData.programmeName || successData.programme}</p>
                                        <p><strong>Course:</strong> {successData.courseName || successData.course}</p>
                                        <p><strong>Curricular Year:</strong> {successData.curricularYear}</p>
                                        <p><strong>Semester:</strong> {successData.semester}</p>
                                        <p><strong>Duration:</strong> {successData.duration}</p>
                                        <p><strong>ECTS:</strong> {successData.credits || successData.qtdECTS}</p>
                                        <p><strong>Study Plan Date:</strong> {successData.studyPlanDate}</p>
                                    </div>
                                    <button className="modal-btn" onClick={() => {
                                        setShowModal(false);
                                        handleClear();
                                    }}>Close</button>
                                </div>
                            </div>
                        )}

                        {showErrorModal && errorMessage && (
                            <div className="modal-overlay">
                                <div className="modal-content" style={{borderColor: 'red'}}>
                                    <h2 style={{color:'red'}}>Registration Error</h2>
                                    <p style={{  marginTop: '1rem' }}>{errorMessage}</p>
                                    <button className="modal-btn" onClick={() =>{
                                        setShowErrorModal(false);
                                        setError('');
                                        setErrorMessage('');
                                    }}>
                                        Close</button>
                                </div>
                            </div>
                        )}
                    </div>
                </form>
            </div>
        </div>
    );
}