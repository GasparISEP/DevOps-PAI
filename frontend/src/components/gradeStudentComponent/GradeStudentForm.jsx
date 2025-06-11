import React, { useState } from 'react';
import { gradeAStudent } from '../../services/courseEditionGradeStudentService';
import ISEPLogoBranco from "../../assets/images/ISEP_logo-branco.png";
import '../../styles/Form.css';
import { Link } from "react-router-dom";

export default function GradeStudentForm() {
    const initialFormState = {
        studentUniqueNumber: '',
        grade: '',
        date: '',
        courseEditionID: '',
    };
    const [form, setForm] = useState(initialFormState);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState('');
    const [success, setSuccess] = useState(null);
    const [showSuccessModal, setShowSuccessModal] = useState(false);
    const [showErrorModal, setShowErrorModal] = useState(false);

    function handleChange(e) {
        const { name, value } = e.target;
        setForm(f => ({ ...f, [name]: value }));
    }

    function clearForm() {
        setForm(initialFormState);
        setError('');
        setSuccess(null);
    }

    async function handleSubmit(e) {
        e.preventDefault();
        setError('');
        setSuccess(null);
        setLoading(true);

        try {
            const response = await gradeAStudent(form);
            setSuccess(response);
            setShowSuccessModal(true);
            setForm({ ...initialFormState });
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
                            <div className="form-group">
                                <label className="form-label" htmlFor="studentUniqueNumber">Student's ID</label>
                                <input className="form-input" placeholder="Enter Student's ID" id="studentUniqueNumber" name="studentUniqueNumber"
                                       value={form.studentUniqueNumber} onChange={handleChange} required/>
                            </div>

                            <div className="form-group">
                                <label className="form-label" htmlFor="grade">Grade</label>
                                <input className="form-input" placeholder="Enter Grade" id="grade" name="grade"
                                       type="number" value={form.grade} onChange={handleChange} required/>
                            </div>

                            <div className="form-group">
                                <label className="form-label" htmlFor="date">Date</label>
                                <input className="form-input" id="date" name="date" type="date"
                                       value={form.date} onChange={handleChange} required/>
                            </div>

                            <div className="form-group">
                                <label className="form-label" htmlFor="courseEditionID">Course Edition's ID</label>
                                <input className="form-input" placeholder="Enter Course Edition ID" id="courseEditionID" name="courseEditionID"
                                       value={form.courseEditionID} onChange={handleChange} required/>
                            </div>

                            <div className="form-actions">
                                <button type="button" className="btn btn-secondary" onClick={clearForm} disabled={loading}>
                                    CLEAR
                                </button>
                                <button type="submit" className="btn btn-primary" disabled={loading}>
                                    {loading ? 'REGISTERING…' : 'REGISTER'}
                                </button>
                            </div>
                        </div>
                    </div>
                </form>
            </div>

            {showSuccessModal && (
                <div className="modal">
                    <p>Student grade registered successfully!</p>
                    <button onClick={() => setShowSuccessModal(false)}>Close</button>
                </div>
            )}
            {showErrorModal && (
                <div className="modal">
                    <p>Error registering student grade: {error}</p>
                    <button onClick={() => setShowErrorModal(false)}>Close</button>
                </div>
            )}
        </div>
    );
}
