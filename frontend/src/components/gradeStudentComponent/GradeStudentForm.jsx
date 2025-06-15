import React, { useState, useEffect } from 'react';
import {getEnrolmentsForStudent, gradeAStudentWithLink} from '../../services/courseEditionGradeStudentService';
import ISEPLogoBranco from "../../assets/images/ISEP_logo-branco.png";
import '../../styles/Form.css';
import '../../styles/RegisterGradeStudentPage.css';
import { Link } from "react-router-dom";
import GradeStudentErrorModal from "./GradeStudentErrorModal";
import GradeStudentSuccessModal from "./GradeStudentSuccessModal";

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
                    setIsLoading(false); // 🚀 Garante que o carregamento termina
                }
            }
        }

        fetchEnrolments();

    }, [form.studentUniqueNumber]);

    function handleChange(e) {
        const { name, value } = e.target;

        // Student ID's validation
        if (name === "studentUniqueNumber") {
            const sanitizedValue = value.replace(/\D/g, ''); // Remove caracteres não numéricos

            // 1) Checks that it contains only numbers
            if (value !== sanitizedValue) {
                setStudentIdError("Student's ID can only contain numbers.");
                return;
            }

            // 2) Checks if the field is empty
            if (sanitizedValue === "") {
                setStudentIdError("Student's ID cannot be empty.");
                setForm(f => ({ ...f, [name]: sanitizedValue }));
                return;
            }

            // 3) Checks if it has more than 7 digits
            if (sanitizedValue.length > 7) {
                setStudentIdError("Student's ID must have exactly 7 digits.");
                setForm(f => ({ ...f, [name]: sanitizedValue.slice(0, 7) }));
                return;
            }

            const studentID = parseInt(sanitizedValue, 10);

            // 4) If it is a valid number within the allowed range, clears the error
            if (!isNaN(studentID) && studentID >= 1000000 && studentID <= 2000000) {
                setStudentIdError("");
            }

            // Sempre atualiza o estado, mesmo que haja erro
            setForm(f => ({ ...f, [name]: sanitizedValue }));
        }

        // Grade's validation
        if (name === "grade") {
            let sanitizedGrade = value.replace(/[^0-9.]/g, ''); // Permite apenas números e um único ponto decimal

            // 1) Checks if it contains invalid characters
            if (value !== sanitizedGrade) {
                setGradeError("Grade can only contain numbers and a single decimal point."); // Exibe erro específico
                return;
            }

            // 2) Checks if the field is empty
            if (sanitizedGrade === "") {
                setGradeError("Grade cannot be empty.");
                setForm(f => ({ ...f, [name]: sanitizedGrade }));
                return;
            }

            // 3) Blocks multiple decimal points
            const dotCount = (sanitizedGrade.match(/\./g) || []).length;
            if (dotCount > 1) {
                setGradeError("Grade format is invalid.");
                return;
            }

            const gradeValue = parseFloat(sanitizedGrade);

            // 4) Checks if its NaN or if it's not allowed in the given range
            if (isNaN(gradeValue) || gradeValue < 0 || gradeValue > 20) {
                setGradeError("Grade must be a valid number between 0 and 20.");
                return;
            }

            // 5) Ensures that decimal numbers have exactly two decimal places
            const decimalCount = sanitizedGrade.split('.')[1]?.length || 0;
            if (decimalCount > 2) {
                setGradeError("Grade must have exactly two decimal places.");
                return;
            }

            // 6) Ensures that an integer does not have more than 2 digits.
            if (!sanitizedGrade.includes(".") && sanitizedGrade.length > 2) {
                setGradeError("Grade must be an integer with at most 2 digits.");
                return;
            }

            // Everything is valid
            setGradeError("");
            setForm(f => ({ ...f, [name]: sanitizedGrade }));
        }
    }

    async function handleSubmit(e) {
        e.preventDefault();
        setError('');
        setSuccess(null);
        setLoading(true);

        try {
            const response = await gradeAStudentWithLink(form);
            setSuccess(response.data);
            setShowSuccessModal(true);
            setForm({ ...initialFormState });
            setStudentIdError('');
            setGradeError('');
        } catch (err) {
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
                        <img src={ISEPLogoBranco} alt="Logo do ISEP"/>
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
                                       type="text" value={form.studentUniqueNumber} onChange={handleChange} required/>
                                {studentIdError && <p className="error-message">{studentIdError}</p>}
                            </div>

                            {/* Course Edition Dropdown */}
                            <div className="form-group">
                                <label className="form-label" htmlFor="courseEditionID">Course Edition</label>
                                <select className="form-input" id="courseEditionID" name="courseEditionID"
                                        value={form.courseEditionID} onChange={handleChange} required>
                                    <option value="">Select a course edition</option>
                                    {Array.isArray(enrolments) && enrolments.length > 0 ? (
                                        enrolments.map(ed => (
                                            <option key={ed.editionID} value={ed.editionID}>{ed.courseName}</option>
                                        ))
                                    ) : (
                                        <option disabled>No available courses</option>
                                    )}
                                </select>
                            </div>

                            {/* Grade */}
                            <div className="form-group">
                                <label className="form-label" htmlFor="grade">Grade</label>
                                <input className="form-input" placeholder="Enter Grade" id="grade" name="grade"
                                       type="text" value={form.grade} onChange={handleChange} required/>
                                {gradeError && <p className="error-message">{gradeError}</p>}
                            </div>

                            {/* Buttons */}
                            <div className="form-actions">
                                <button type="button" className="btn btn-secondary" onClick={() => setForm(initialFormState)}>
                                    CLEAR
                                </button>
                                <button type="submit" className="btn btn-primary" disabled={loading}>
                                    GRADE STUDENT
                                </button>
                            </div>
                        </div>
                    </div>
                </form>

                {/* Modal de Sucesso */}
                {showSuccessModal && <GradeStudentSuccessModal success={success} onClose={() => setShowSuccessModal(false)} />}

                {/* Modal de Erro */}
                {showErrorModal && <GradeStudentErrorModal error={error} onClose={() => setShowErrorModal(false)} />}
            </div>
        </div>
    );
}
