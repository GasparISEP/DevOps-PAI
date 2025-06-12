import React, { useState, useEffect } from 'react';
import { gradeAStudent } from '../../services/courseEditionGradeStudentService';
import { findAllCourseEditions } from '../../services/courseEditionGradeStudentService';
import ISEPLogoBranco from "../../assets/images/ISEP_logo-branco.png";
import '../../styles/Form.css';
import { Link } from "react-router-dom";

export default function GradeStudentForm() {
    const initialFormState = {
        studentUniqueNumber: '',
        grade: '',
        courseEditionID: '',
    };

    const [form, setForm] = useState(initialFormState);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState('');
    const [success, setSuccess] = useState(null);
    const [showSuccessModal, setShowSuccessModal] = useState(false);
    const [showErrorModal, setShowErrorModal] = useState(false);
    const [courseEditions, setCourseEditions] = useState([]);
    const [studentIdError, setStudentIdError] = useState('');
    const [gradeError, setGradeError] = useState('');

    useEffect(() => {
        async function fetchCourseEditions() {
            try {
                const editions = await findAllCourseEditions();
                setCourseEditions(editions);
            } catch (err) {
                console.error("Error fetching course editions:", err);
            }
        }
        fetchCourseEditions();
    }, []);

    function handleChange(e) {
        const { name, value } = e.target;

        // Student ID's validation
        if (name === "studentUniqueNumber") {
            const sanitizedValue = value.replace(/\D/g, ''); // Remove caracteres não numéricos

            // Se não contiver apenas números
            if (value !== sanitizedValue) {
                setStudentIdError("Student's ID can only contain numbers.");
                return;
            }

            // Se o campo estiver vazio
            if (sanitizedValue === "") {
                setStudentIdError("Student's ID cannot be empty.");
                setForm(f => ({ ...f, [name]: sanitizedValue }));
                return;
            }

            // Se tiver mais de 7 dígitos
            if (sanitizedValue.length > 7) {
                setStudentIdError("Student's ID must have exactly 7 digits.");
                setForm(f => ({ ...f, [name]: sanitizedValue.slice(0, 7) }));
                return;
            }

            const studentID = parseInt(sanitizedValue, 10);

            // Se for um número válido dentro do intervalo permitido, **limpa o erro**
            if (!isNaN(studentID) && studentID >= 1000000 && studentID <= 2000000) {
                setStudentIdError(""); // Limpa a mensagem de erro
                setForm(f => ({ ...f, [name]: sanitizedValue }));
                return;
            }

            // Se estiver fora do intervalo permitido
            setStudentIdError("Student's ID must be a number between 1000000 and 2000000.");
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

            // ️ 6) Ensures that an integer does not have more than 2 digits.
            if (!sanitizedGrade.includes(".") && sanitizedGrade.length > 2) {
                setGradeError("Grade must be an integer with at most 2 digits.");
                return;
            }

            // Everything is valid
            setGradeError("");
            setForm(f => ({ ...f, [name]: sanitizedGrade }));
            return;
        }



        setForm(f => ({ ...f, [name]: value }));
    }

    async function handleSubmit(e) {
        e.preventDefault();
        setError('');
        setSuccess(null);
        setLoading(true);

        const studentID = parseInt(form.studentUniqueNumber, 10);
        const grade = parseFloat(form.grade);

        if (isNaN(studentID) || studentID < 1000000 || studentID > 2000000 || form.studentUniqueNumber.length !== 7) {
            setError("Student ID must be a valid number with exactly 7 digits between 1000000 and 2000000.");
            setShowErrorModal(true);
            setLoading(false);
            return;
        }

        if (isNaN(grade) || grade < 0 || grade > 20 || !form.grade) {
            setError("Grade must be a valid number between 0 and 20.");
            setShowErrorModal(true);
            setLoading(false);
            return;
        }

        if ((form.grade.match(/\./g) || []).length > 1 || (form.grade.split('.')[1]?.length || 0) > 2) {
            setError("Grade must have exactly two decimal places.");
            setShowErrorModal(true);
            setLoading(false);
            return;
        }

        try {
            const response = await gradeAStudent(form);
            setSuccess(response);
            setShowSuccessModal(true);
            setForm({ ...initialFormState });
            setStudentIdError('');
            setGradeError('');
        } catch (err) {
            setError(err.message);
            setShowErrorModal(true);
        } finally {
            setLoading(false);
        }
    }

    return (
        <div className="form-main-component-div">
            <div className="form-main-grid">
                <div className="form-img-main-div">
                    <div className="form-logo-img-div">
                        <img src={ISEPLogoBranco} alt="Logo do ISEP"/>
                    </div>
                </div>

                <form className="form" onSubmit={handleSubmit}>
                    <div style={{ display: 'flex', alignItems: 'center', justifyContent: 'space-between', marginBottom: '2rem' }}>
                        <h1 style={{ margin: 0 }}>Grade a Student</h1>
                        <Link to="/" className="pagination-btn2 pagination-btn-secondary" style={{ textDecoration: 'none' }}>
                            Back to Home Page
                        </Link>
                    </div>

                    <div className="form-and-buttons-main-div">
                        <div className="form-div">
                            {/* Student ID */}
                            <div className="form-group">
                                <label className="form-label" htmlFor="studentUniqueNumber">Student's ID</label>
                                <input className="form-input" placeholder="Enter Student's ID" id="studentUniqueNumber" name="studentUniqueNumber"
                                       type="text" value={form.studentUniqueNumber} onChange={handleChange} required/>
                                {studentIdError && <p className="error-message">{studentIdError}</p>}
                            </div>

                            {/* Grade */}
                            <div className="form-group">
                                <label className="form-label" htmlFor="grade">Grade</label>
                                <input className="form-input" placeholder="Enter Grade" id="grade" name="grade"
                                       type="text" value={form.grade} onChange={handleChange} required/>
                                {gradeError && <p className="error-message">{gradeError}</p>}
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    );
}
