import React, { useState, useEffect } from 'react';
import { getEnrolmentsForStudent, gradeAStudentWithLink } from '../../services/courseEditionGradeStudentService';
import ISEPLogoBranco from "../../assets/images/ISEP_logo-branco.png";
import '../../styles/Form.css';
import '../../styles/Buttons.css'
import '../../styles/RegisterGradeStudentPage.css';
import { Link } from "react-router-dom";


export default function GradeStudentForm() {
    const initialFormState = {
        studentUniqueNumber: '',
        courseEditionID: '',
        grade: ''
    };

    const [form, setForm] = useState(initialFormState);
    const [loading, setLoading] = useState(false);
    const [isLoading, setIsLoading] = useState(false);
    const [error, setError] = useState('');
    const [success, setSuccess] = useState(null);
    const [showSuccessModal, setShowSuccessModal] = useState(false);
    const [showErrorModal, setShowErrorModal] = useState(false);
    const [studentIdError, setStudentIdError] = useState('');
    const [gradeError, setGradeError] = useState('');
    const [enrolments, setEnrolments] = useState([]);

    useEffect(() => {
        if (!form.studentUniqueNumber || form.studentUniqueNumber.length < 7) {
            setEnrolments([]);
        }
    }, [form.studentUniqueNumber]);

    useEffect(() => {
        async function fetchEnrolments() {
            if (form.studentUniqueNumber.length === 7) {
                setIsLoading(true);
                try {
                    console.log(`Buscando edições de curso para Student ID: ${form.studentUniqueNumber}`);
                    const enrolmentsData = await getEnrolmentsForStudent(form.studentUniqueNumber);
                    console.log("Dados retornados pela API:", enrolmentsData);

                    setEnrolments(Array.isArray(enrolmentsData) ? enrolmentsData : []);
                } catch (err) {
                    console.error("Erro ao buscar inscrições do estudante:", err);
                    setEnrolments([]);
                } finally {
                    setIsLoading(false);
                }
            }
        }

        fetchEnrolments();
    }, [form.studentUniqueNumber]);

    function handleChange(e) {
        const { name, value } = e.target;

        // Student ID's validation
        if (name === "studentUniqueNumber") {
            const sanitizedValue = value.replace(/\D/g, '');

            // Checks if it only contains numbers
            if (value !== sanitizedValue) {
                setStudentIdError("Student's ID can only contain numbers.");
                return;
            }

            // Checks if the Student's ID is empty
            if (sanitizedValue === "") {
                setStudentIdError("Student's ID cannot be empty.");
                setForm(f => ({ ...f, [name]: sanitizedValue }));
                return;
            }

            // Checks if it only contains 7 digits
            if (sanitizedValue.length > 7) {
                setStudentIdError("Student's ID must have exactly 7 digits.");
                setForm(f => ({ ...f, [name]: sanitizedValue.slice(0, 7) }));
                return;
            }

            const studentID = parseInt(sanitizedValue, 10);

            // Correct input
            if (!isNaN(studentID) && studentID >= 1000000 && studentID <= 2000000) {
                setStudentIdError("");
            }

            setForm(f => ({ ...f, [name]: sanitizedValue }));
        }

        // Grade's validation
        if (name === "grade") {
            let sanitizedGrade = value.replace(/[^0-9.]/g, '');

            // Checks if it only contains numbers and a single decimal point
            if (value !== sanitizedGrade) {
                setGradeError("Grade can only contain numbers and a single decimal point.");
                return;
            }

            // Checks if the grade is empty
            if (sanitizedGrade === "") {
                setGradeError("Grade cannot be empty.");
                setForm(f => ({ ...f, [name]: sanitizedGrade }));
                return;
            }

            const dotCount = (sanitizedGrade.match(/\./g) || []).length;
            if (dotCount > 1) {
                setGradeError("Grade format is invalid.");
                return;
            }

            const gradeValue = parseFloat(sanitizedGrade);

            // Checks if the grade is between 0 and 20
            if (isNaN(gradeValue) || gradeValue < 0 || gradeValue > 20) {
                setGradeError("Grade must be a valid number between 0 and 20.");
                return;
            }

            const decimalCount = sanitizedGrade.split('.')[1]?.length || 0;

            // Checks if it has more than 2 decimal places
            if (decimalCount > 2) {
                setGradeError("Grade must have exactly two decimal places.");
                return;
            }

            // Checks if the grade is an integer with more than 2 digits
            if (!sanitizedGrade.includes(".") && sanitizedGrade.length > 2) {
                setGradeError("Grade must be an integer with at most 2 digits.");
                return;
            }

            setGradeError("");
            setForm(f => ({ ...f, [name]: sanitizedGrade }));
        }

        // CourseEdition dropdown
        if (name === "courseEditionID") {
            setForm(f => ({ ...f, [name]: value }));
        }
    }

    async function handleSubmit(e) {
        e.preventDefault();
        setError('');
        setSuccess(null);
        setLoading(true);

        try {
            if (!form.courseEditionID || !form.courseEditionID.includes('|')) {
                throw new Error("Nenhuma edição de curso válida selecionada.");
            }

            const [schoolYearId, courseAcronym, courseName] = form.courseEditionID.split('|');

            if (!schoolYearId || !courseAcronym || !courseName) {
                throw new Error("Dados da edição do curso estão incompletos.");
            }

            const requestPayload = {
                studentUniqueNumber: form.studentUniqueNumber,
                schoolYearId,
                courseAcronym,
                courseName,
                grade: form.grade
            };

            console.log("📡 Payload antes do envio:", requestPayload);

            const response = await gradeAStudentWithLink(requestPayload);
            setSuccess(response.data);
            setShowSuccessModal(true);
        } catch (err) {
            console.error("Erro ao registrar nota:", err);
            setError(err.message || "Erro desconhecido ao registrar a nota.");
            setShowErrorModal(true);
        } finally {
            setLoading(false);
        }
    }


    return (
        <div className="form-main-component-div">
            <div className="form-main-grid">
                <div className="form-img-main-div grade-student-img-background">
                    <div className="form-logo-img-div">
                        <img src={ISEPLogoBranco} alt="Logo do ISEP" />
                    </div>
                </div>

                <form className="form" onSubmit={handleSubmit}>
                    <div style={{
                        display: 'flex',
                        alignItems: 'center',
                        justifyContent: 'space-between',
                        marginBottom: '2rem'
                    }}>
                        <h1 style={{ margin: 0 }}>Grade a Student</h1>
                        <Link to="/" className="pagination-btn2 pagination-btn-secondary"
                              style={{ textDecoration: 'none' }}>
                            Back to Home Page
                        </Link>
                    </div>

                    <div className="form-and-buttons-main-div">
                        <div className="form-div">
                            {/* Student's ID */}
                            <div className="form-group">
                                <label className="form-label" htmlFor="studentUniqueNumber">Student's ID</label>
                                <input className="form-input" placeholder="Enter Student's ID" id="studentUniqueNumber"
                                       name="studentUniqueNumber"
                                       type="text" value={form.studentUniqueNumber} onChange={handleChange} required />
                                {studentIdError && <p className="error-message">{studentIdError}</p>}
                            </div>

                            {/* Course Edition Dropdown */}
                            <div className="form-group">
                                <label className="form-label" htmlFor="courseEditionID">Course Edition</label>
                                <select className="form-input" id="courseEditionID" name="courseEditionID"
                                        value={form.courseEditionID} onChange={handleChange} required>
                                    <option value="">Select a course edition</option>
                                    {Array.isArray(enrolments) && enrolments.length > 0 ? (
                                        enrolments.map((ed, idx) => {
                                            const value = `${ed.schoolYearId}|${ed.courseAcronym}|${ed.courseName}`;
                                            return (
                                                <option key={idx} value={value}>
                                                    {ed.courseEditionName}
                                                </option>
                                            );
                                        })
                                    ) : (
                                        <option disabled>No available courses</option>
                                    )}
                                </select>
                            </div>

                            {/* Grade */}
                            <div className="form-group">
                                <label className="form-label" htmlFor="grade">Grade</label>
                                <input className="form-input" placeholder="Enter Grade" id="grade" name="grade"
                                       type="text" value={form.grade} onChange={handleChange} required />
                                {gradeError && <p className="error-message">{gradeError}</p>}
                            </div>

                            {/* Buttons */}
                            <div className="form-actions">
                                <button type="button" className="btn btn-secondary"
                                        onClick={() => setForm(initialFormState)}>
                                    CLEAR
                                </button>
                                <button type="submit" className="btn btn-primary" disabled={loading}>
                                    GRADE STUDENT
                                </button>
                            </div>
                        </div>
                    </div>
                </form>

                {/* Success Modal */}
                {showSuccessModal && success && (
                    <div className="modal-overlay">
                        <div className="modal-content">
                            <h2 style={{ color: 'green' }}>✅ Grade Registered Successfully</h2>
                            <p><strong>Student ID:</strong> {success.studentUniqueNumber}</p>
                            <p><strong>Student Name:</strong> {success.studentName}</p>
                            <p><strong>Course:</strong> {success.courseName} ({success.courseAcronym})</p>
                            <p><strong>School Year:</strong> {success.schoolYearId}</p>
                            <p><strong>Grade:</strong> {success.grade}</p>

                            <button className="btn btn-primary" onClick={() => setShowSuccessModal(false)}>Close</button>
                        </div>
                    </div>
                )}


                {/* Error Modal */}
                {showErrorModal && error && (
                    <div className="modal-overlay">
                        <div className="modal-content">
                            <h2 style={{ color: 'red' }}>❌ Error Registering Grade</h2>
                            <p>{error}</p>

                            <button className="btn btn-secondary" onClick={() => setShowErrorModal(false)}>Close</button>
                        </div>
                    </div>
                )}
            </div>
        </div>
    );
}
