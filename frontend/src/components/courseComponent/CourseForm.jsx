import React, { useEffect, useState } from 'react';
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
    const [maxECTS, setMaxECTS] = useState(null);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState('');
    const [success, setSuccess] = useState(null);

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

    async function fetchProgrammeDetails(acronym, name) {
        try {
            const res = await fetch(`${process.env.REACT_APP_API_URL}/programmes/${encodeURIComponent(name)}/${encodeURIComponent(acronym)}`);
            const data = await res.json();

            if (data && data.quantSemesters) {
                const semesters = Array.from({ length: data.quantSemesters }, (_, i) => i + 1);
                const estimatedYears = Math.ceil(data.quantSemesters / 2);
                const years = Array.from({ length: estimatedYears }, (_, i) => i + 1);

                setAvailableSemesters(semesters);
                setAvailableYears(years);
                setMaxECTS(data.maxECTS || null);
            }
        } catch (err) {
            console.error("Failed to fetch programme details", err);
        }
    }

    function handleChange(e) {
        const { name, value } = e.target;
        setForm(prev => ({ ...prev, [name]: value }));

        if (name === "programme") {
            const selectedProgramme = programmes.find(p => p.acronym === value);
            if (selectedProgramme) {
                fetchProgrammeDetails(selectedProgramme.acronym, selectedProgramme.name);
            } else {
                setAvailableYears([]);
                setAvailableSemesters([]);
                setMaxECTS(null);
            }
        }
    }

    async function handleSubmit(e) {
        e.preventDefault();
        setError('');
        setSuccess(null);
        setLoading(true);

        const parsedECTS = parseInt(form.qtdECTS);
        if (maxECTS && parsedECTS > maxECTS) {
            setError(`ECTS value cannot exceed the programme's maximum of ${maxECTS}.`);
            setLoading(false);
            return;
        }

        try {
            const payload = {
                ...form,
                qtdECTS: parsedECTS,
                semester: parseInt(form.semester),
                curricularYear: parseInt(form.curricularYear)
            };

            const response = await registerCourseInStudyPlan(payload);
            setSuccess(response);
        } catch (err) {
            setError(err.message || "Failed to register the course.");
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
                    <h1>Register a Course</h1>
                    <div className="form-and-buttons-main-div">
                        <div className="form-div">
                            <ProgrammeSelector programmes={programmes} value={form.programme} onChange={handleChange} />
                            <CourseSelector courses={courses} value={form.course} onChange={handleChange} />
                            <SemesterSelector semesters={availableSemesters} value={form.semester} onChange={handleChange} />
                            <CurricularYearSelector years={availableYears} value={form.curricularYear} onChange={handleChange} />
                            <DurationSelector value={form.duration} onChange={handleChange} />
                            <ECTSInput value={form.qtdECTS} onChange={handleChange} maxECTS={maxECTS} />

                            {error && <div className="error">{error}</div>}

                            <div className="form-actions">
                                <button type="button" className="btn btn-secondary" onClick={() => window.history.back()} disabled={loading}>Cancel</button>
                                <button type="submit" className="btn btn-primary" disabled={loading}>
                                    {loading ? 'Submitting…' : 'Submit'}
                                </button>
                            </div>
                        </div>

                        {success && (
                            <div className="success" style={{ marginTop: '1rem', color: '#080' }}>
                                <h3>Course successfully added to the programme!</h3>
                                <p><strong>Programme:</strong> {success.programme}</p>
                                <p><strong>Course:</strong> {success.course}</p>
                                <p><strong>Curricular Year:</strong> {success.curricularYear}</p>
                                <p><strong>Semester:</strong> {success.semester}</p>
                                <p><strong>Duration:</strong> {success.duration}</p>
                                <p><strong>ECTS:</strong> {success.qtdECTS}</p>
                            </div>
                        )}
                    </div>
                </form>
            </div>
        </div>
    );
}
