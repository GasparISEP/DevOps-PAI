import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import { defineRucInCourseEdition, getAllSchoolYears, findAllCourseEditions } from '../../services/DefineRucInCourseEditionService';
import { getAllTeachers } from "../../services/teacherService";
import { findAllProgrammes } from "../../services/studentService";

import ISEPLogoBranco from '../../assets/images/ISEP_logo-branco.png';
import '../../styles/Form.css';
import '../../styles/NavBar.css';
import '../../styles/Footer.css';

import CourseEditionSelector from "./CourseEditionSelector";
import TeacherSelector from "./TeacherSelector";
import SchoolYearSelector from "./SchoolYearSelector";
import NavBar from "../NavBar";
import Footer from "../Footer";
import SuccessModal from "./SuccessModal";
import ErrorModal from "./ErrorModal";

export default function DefineRucInCourseEditionForm() {
    const [form, setForm] = useState({
        schoolYear: '',
        courseEditionId: '',
        teacherId: ''
    });

    const [schoolYears, setSchoolYears] = useState([]);
    const [allCourseEditions, setAllCourseEditions] = useState([]);
    const [filteredCourseEditions, setFilteredCourseEditions] = useState([]);
    const [teachers, setTeachers] = useState([]);
    const [allProgrammes, setAllProgrammes] = useState([]);
    const [loading, setLoading] = useState(false);

    const [successData, setSuccessData] = useState(null);
    const [showSuccessModal, setShowSuccessModal] = useState(false);
    const [errorMessage, setErrorMessage] = useState('');
    const [showErrorModal, setShowErrorModal] = useState(false);
    const [courseEditionError, setCourseEditionError] = useState('');

    const filteredCourseEditionsWithProgrammeName = filteredCourseEditions.map(ce => {
        const programme = allProgrammes.find(
            p => p.acronym.trim().toLowerCase() === ce.programmeAcronym.trim().toLowerCase()
        );

        const programmeName = programme ? programme.name : ce.programmeAcronym;
        const displayName = `${ce.courseName}`;

        return {
            ...ce,
            programmeName,
            name: displayName
        };
    });
    useEffect(() => {
        async function fetchData() {
            try {
                const [schoolYearsData, courseEditionsData, teachersData, programmesData] = await Promise.all([
                    getAllSchoolYears(),
                    findAllCourseEditions(),
                    getAllTeachers(),
                    findAllProgrammes()
                ]);

                setSchoolYears(schoolYearsData);
                setAllCourseEditions(courseEditionsData);
                setTeachers(teachersData);
                setAllProgrammes(programmesData);
            } catch (e) {
                console.error("Erro ao carregar dados iniciais:", e);
            }
        }
        fetchData();
    }, []);

    useEffect(() => {
        if (form.schoolYear) {
            const filtered = allCourseEditions.filter(ce => ce.schoolYearID === form.schoolYear);
            setFilteredCourseEditions(filtered);

            if (filtered.length === 0) {
                setCourseEditionError('No Course Editions found for the selected School Year.');
            } else {
                setCourseEditionError('');
            }

            if (!filtered.find(ce => ce.courseEditionGeneratedID === form.courseEditionId)) {
                setForm(prev => ({ ...prev, courseEditionId: '' }));
            }
        } else {
            setFilteredCourseEditions([]);
            setForm(prev => ({ ...prev, courseEditionId: '' }));
            setCourseEditionError('');
        }
    }, [form.schoolYear, allCourseEditions]);

    function clearForm() {
        setForm({
            schoolYear: '',
            courseEditionId: '',
            teacherId: ''
        });
        setSuccessData(null);
        setErrorMessage('');
    }

    function extractBackendMessage(error) {
        const defaultMessage = "An error occurred when defining the RUC in the course edition.";
        if (!error.message) return defaultMessage;

        try {
            const jsonPart = error.message.split('Response: ')[1];
            if (jsonPart) {
                const parsed = JSON.parse(jsonPart);
                return parsed.message || parsed.error || defaultMessage;
            }
        } catch {
            return defaultMessage;
        }
        return defaultMessage;
    }

    function handleChange(event) {
        const { name, value } = event.target;
        setForm(prev => ({
            ...prev,
            [name]: value
        }));
    }

    async function handleSubmit(event) {
        event.preventDefault();
        setLoading(true);
        setErrorMessage('');
        setSuccessData(null);

        try {
            const response = await defineRucInCourseEdition({
                id: form.courseEditionId,
                teacherId: form.teacherId
            });


            // Get teacher and course edition names
            const teacher = teachers.find(t => t.id === form.teacherId);
            const courseEdition = filteredCourseEditionsWithProgrammeName.find(
                ce => ce.courseEditionGeneratedID === form.courseEditionId
            );

// Set enriched success data
            setSuccessData({
                teacherId: form.teacherId,
                teacherName: teacher?.name,
                courseEditionId: form.courseEditionId,
                courseEditionName: courseEdition?.name,
                programmeName: courseEdition?.programmeName
            });



            setShowSuccessModal(true);
        } catch (error) {
            console.error(error);
            setErrorMessage(extractBackendMessage(error));
            setShowErrorModal(true);
        } finally {
            setLoading(false);
        }
    }

    return (
        <>
            <NavBar />
            <div className="form-main-component-div">
                <div className="form-main-grid">
                    <div className="form-img-main-div teacher-img-background">
                        <div className="form-logo-img-div">
                            <img src={ISEPLogoBranco} alt="ISEP Logo" />
                        </div>
                    </div>

                    <form className="form" onSubmit={handleSubmit}>
                        <div style={{ display: 'flex', justifyContent: 'space-between', marginBottom: '2rem' }}>
                            <h1 style={{ margin: 0 }}>Define a RUC</h1>
                            <Link to="/" className="pagination-btn2 pagination-btn-secondary">Back to Home Page</Link>
                        </div>

                        <div className="form-and-buttons-main-div">
                            <div className="form-div">
                                <SchoolYearSelector
                                    schoolYears={schoolYears}
                                    value={form.schoolYear}
                                    onChange={handleChange}
                                    name="schoolYear"
                                />
                                <CourseEditionSelector
                                    courseEditions={filteredCourseEditionsWithProgrammeName}
                                    value={form.courseEditionId}
                                    onChange={handleChange}
                                    name="courseEditionId"
                                />
                                {courseEditionError && (
                                    <div style={{ color: 'red', marginTop: '0.5rem' }}>{courseEditionError}</div>
                                )}
                                <TeacherSelector
                                    teachers={teachers}
                                    value={form.teacherId}
                                    onChange={handleChange}
                                    name="teacherId"
                                />
                            </div>

                            <div className="form-actions">
                                <button type="button" className="btn btn-secondary" onClick={clearForm}>
                                    CLEAR
                                </button>
                                <button type="submit" className="btn btn-primary" disabled={loading}>
                                    {loading ? 'LOADING…' : 'REGISTER'}
                                </button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>

            <SuccessModal
                data={successData}
                form={form}
                onClose={() => {
                    setShowSuccessModal(false);
                    clearForm();
                }}
                show={showSuccessModal}
            />

            <ErrorModal
                message={errorMessage}
                onClose={() => {
                    setShowErrorModal(false);
                    setErrorMessage('');
                }}
                show={showErrorModal}
            />

            <Footer />
        </>
    );
}
