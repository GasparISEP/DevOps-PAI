import React, { useState, useEffect } from 'react';
import Select from 'react-select';
import 'react-phone-input-2/lib/style.css';
import ISEPLogoBranco from "../../assets/images/ISEP_logo-branco.png";
import '../../styles/Form.css';
import { Link } from "react-router-dom";
import { findAllDepartments, findProgrammesByDepartment, enrolStudentInProgramme } from '../../services/studentService';
import '../../styles/Form.css';

const initialFormState = {
    studentUniqueNumber: '',
    departmentID: '',
    programmeID: '',
    enrolmentDate: ''
};

export default function StudentProgrammeEnrolmentForm() {
    const [form, setForm] = useState(initialFormState);
    const [departments, setDepartments] = useState([]);
    const [programmes, setProgrammes] = useState([]);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState('');
    const [success, setSuccess] = useState(null);
    const [showModal, setShowModal] = useState(false);
    const [showErrorModal, setShowErrorModal] = useState(false);
    const [formErrors, setFormErrors] = useState({});

    useEffect(() => {
        async function fetchDepartments() {
            try {
                const data = await findAllDepartments();
                setDepartments(data);
            } catch (err) {
                setError("Error loading departments.");
                setShowErrorModal(true);
            }
        }
        fetchDepartments();
    }, []);

    async function handleDepartmentChange(option) {
        const departmentID = option?.value ?? '';
        setForm(f => ({ ...f, departmentID, programmeID: '' }));

        if (departmentID) {
            try {
                const data = await findProgrammesByDepartment(departmentID);
                setProgrammes(data);
            } catch (err) {
                setError("Error loading programmes.");
                setShowErrorModal(true);
            }
        } else {
            setProgrammes([]);
        }
    }

    function handleProgrammeChange(option) {
        setForm(f => ({ ...f, programmeID: option?.value ?? '' }));
    }

    function handleInputChange(e) {
        const { name, value } = e.target;
        setForm(f => ({ ...f, [name]: value }));
    }

    async function handleSubmit(e) {
        e.preventDefault();
        setError('');
        setSuccess(null);

        if (!form.studentUniqueNumber || !form.departmentID || !form.programmeID || !form.enrolmentDate) {
            setError("Please fill in all fields.");
            setShowErrorModal(true);
            return;
        }

        const dateRegex = /^(0[1-9]|[12][0-9]|3[01])-(0[1-9]|1[0-2])-\d{4}$/;
        if (!dateRegex.test(form.enrolmentDate)) {
            setError("Date must be in DD-MM-YYYY format.");
            setShowErrorModal(true);
            return;
        }

        setLoading(true);
        try {
            const response = await enrolStudentInProgramme(form);
            setSuccess(response);
            setShowModal(true);
        } catch (err) {
            setError(err.message || 'An unexpected error occurred.');
            setShowErrorModal(true);
        } finally {
            setLoading(false);
        }
    }

    const departmentOptions = departments.map(dep => ({ value: dep.id, label: dep.name }));
    const programmeOptions = programmes.map(prog => ({ value: prog.id, label: prog.name }));

    return (
        <div className="form-main-component-div">
            <div className="form-main-grid">
                <div className="form-img-main-div">
                    <div className="form-logo-img-div">
                        <img src={ISEPLogoBranco} alt="ISEP Logo" />
                    </div>
                </div>

                <form className="form" onSubmit={handleSubmit}>
                    <div style={{ display: 'flex', alignItems: 'center', justifyContent: 'space-between', marginBottom: '2rem' }}>
                        <h1 style={{ margin: 0 }}>Programme Enrolment</h1>
                        <Link to="/" className="pagination-btn2 pagination-btn-secondary" style={{ textDecoration: 'none' }}>
                            Back to Home Page
                        </Link>
                    </div>

                    <div className="form-and-buttons-main-div">
                        <div className="form-div">
                            <div className="form-group">
                                <label className="form-label" htmlFor="studentUniqueNumber">Student Unique Number</label>
                                <input
                                    className="form-input"
                                    type="text"
                                    id="studentUniqueNumber"
                                    name="studentUniqueNumber"
                                    placeholder="Enter Student ID"
                                    value={form.studentUniqueNumber}
                                    onChange={handleInputChange}
                                    style={{ width: '554px' }}
                                    required
                                />
                            </div>

                            <div className="form-group">
                                <label className="form-label" htmlFor="departmentID">Department</label>
                                <Select
                                    id="departmentID"
                                    name="departmentID"
                                    options={departmentOptions}
                                    value={departmentOptions.find(o => o.value === form.departmentID)}
                                    onChange={handleDepartmentChange}
                                    placeholder="Select Department"
                                    isSearchable
                                    styles={{
                                        control: base => ({
                                            ...base,
                                            width: '554px',
                                            height: '4rem'
                                        })
                                    }}
                                />
                            </div>

                            <div className="form-group">
                                <label className="form-label" htmlFor="programmeID">Programme</label>
                                <Select
                                    id="programmeID"
                                    name="programmeID"
                                    options={programmeOptions}
                                    value={programmeOptions.find(o => o.value === form.programmeID)}
                                    onChange={handleProgrammeChange}
                                    placeholder="Select Programme"
                                    isDisabled={!form.departmentID}
                                    isSearchable
                                    styles={{
                                        control: base => ({
                                            ...base,
                                            width: '554px',
                                            height: '4rem'
                                        })
                                    }}
                                />
                            </div>

                            <div className="form-group">
                                <label className="form-label" htmlFor="enrolmentDate">Enrolment Date (DD-MM-YYYY)</label>
                                <input
                                    className="form-input"
                                    type="text"
                                    id="enrolmentDate"
                                    name="enrolmentDate"
                                    placeholder="e.g. 12-06-2025"
                                    value={form.enrolmentDate}
                                    onChange={handleInputChange}
                                    style={{ width: '554px' }}
                                    required
                                />
                            </div>

                            <div className="form-actions">
                                <button type="submit" className="btn btn-primary" disabled={loading}>
                                    {loading ? 'ENROLLING…' : 'ENROL'}
                                </button>
                            </div>
                        </div>
                    </div>
                </form>
            </div>

            {showModal && success && (
                <div className="modal-overlay">
                    <div className="modal-content">
                        <h2>Success!</h2>
                        <p>The student was enrolled successfully.</p>
                        <p><strong>Student ID:</strong> {form.studentUniqueNumber}</p>
                        <p><strong>Department:</strong> {departmentOptions.find(d => d.value === form.departmentID)?.label}</p>
                        <p><strong>Programme:</strong> {programmeOptions.find(p => p.value === form.programmeID)?.label}</p>
                        <p><strong>Enrolment Date:</strong> {form.enrolmentDate}</p>
                        <button className="modal-btn" onClick={() => window.location.reload()}>Close</button>
                    </div>
                </div>
            )}

            {showErrorModal && (
                <div className="modal-overlay">
                    <div className="modal-content" style={{ borderColor: 'red' }}>
                        <h2 style={{ color: 'red' }}>Error</h2>
                        <p>{error}</p>
                        <button className="modal-btn" onClick={() => setShowErrorModal(false)}>Close</button>
                    </div>
                </div>
            )}
        </div>
    );
}
