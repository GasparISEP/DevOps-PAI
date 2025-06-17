import React, { useState, useEffect } from 'react';
import Select from 'react-select';
import 'react-phone-input-2/lib/style.css';
import ISEPLogoBranco from "../../assets/images/ISEP_logo-branco.png";
import '../../styles/Form.css';
import { Link } from "react-router-dom";
import { findAllAccessMethods, findAllDepartments, findAllProgrammes, enrolStudentInProgramme, getAllStudents } from '../../services/studentService';
import '../../styles/Form.css';


const initialFormState = {
    studentID: '',
    departmentID: '',
    programmeAcronym: '',
    accessMethodID: '',
    date: ''
};

export default function StudentProgrammeEnrolmentForm() {
    const [studentNotFound, setStudentNotFound] = useState(false);
    const [form, setForm] = useState(initialFormState);
    const [departments, setDepartments] = useState([]);
    const [programmes, setProgrammes] = useState([]);
    const [accessMethodIDs, setaccessMethodIDs] = useState([]);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState('');
    const [success, setSuccess] = useState(null);
    const [showModal, setShowModal] = useState(false);
    const [showErrorModal, setShowErrorModal] = useState(false);
    const [formErrors, setFormErrors] = useState({});

    const handleStudentBlur = async () => {
        setError('');
        setStudentNotFound(false);

        const enteredId = form.studentID;

        if (!enteredId || enteredId.length !== 7) {
            setError('Student ID must be exactly 7 digits.');
            return;
        }

        try {
            const allStudents = await getAllStudents();
            const exists = allStudents.some(s => s.studentID === parseInt(enteredId));

            if (!exists) {
                setStudentNotFound(true);
            }
        } catch (err) {
            console.error(err);
            setError('Erro ao validar o estudante.');
            setStudentNotFound(true);
        }
    };


    useEffect(() => {
        async function fetchDepartments() {
            try {
                const data = await findAllDepartments();
                setDepartments(data);
                const ams = await findAllAccessMethods();
                setaccessMethodIDs(ams);
            } catch (err) {
                setError("Error loading departments.");
                setShowErrorModal(true);
            }
        }
        fetchDepartments();
    }, []);

    async function handleDepartmentChange(option) {
        const departmentID = option?.value ?? '';
        setForm(f => ({ ...f, departmentID, programmeAcronym: '' }));

        if (departmentID) {
            try {
                const allProgrammes = await findAllProgrammes();
                const filteredProgrammes = allProgrammes.filter(p => p.departmentID === departmentID);
                setProgrammes(filteredProgrammes);
                console.log("Filtered programmes for department:", departmentID, filteredProgrammes);
            } catch (err) {
                setError("Error loading programmes.");
                setShowErrorModal(true);
            }
        } else {
            setProgrammes([]);
        }
    }


    function handleProgrammeChange(option) {
        setForm(f => ({ ...f, programmeAcronym: option?.value ?? '' }));
    }

    function handleaccessMethodIDChange(option) {
        setForm(f => ({ ...f, accessMethodID: option?.value ?? '' }));
    }

    function handleDateChange(e) {
        const value = e.target.value;
        const cleaned = value.replace(/[^0-9\-]/g, '');

        if (cleaned.length <= 10) {
            setForm(f => ({ ...f, date: cleaned }));
        }
    }

    async function handleSubmit(e) {
        e.preventDefault();
        setError('');
        setSuccess(null);

        if (!form.studentID || !form.departmentID || !form.programmeAcronym || !form.date || !form.accessMethodID) {
            setError("Please fill in all fields.");
            setShowErrorModal(true);
            return;
        }

        const idRegex = /^\d{7}$/;
        if (!idRegex.test(form.studentID)) {
            setError("Student Unique Number must be exactly 7 digits.");
            setShowErrorModal(true);
            return;
        }

        const dateRegex = /^(0[1-9]|[12][0-9]|3[01])-(0[1-9]|1[0-2])-\d{4}$/;
        if (!dateRegex.test(form.date)) {
            setError("Date must be in DD-MM-YYYY format.");
            setShowErrorModal(true);
            return;
        }

        const [day, month, year] = form.date.split("-");
        const isoDate = `${year}-${month}-${day}`;

        const { departmentID, ...rest } = form;

        const formDataToSend = {
            studentID: parseInt(form.studentID),
            accessMethodID: form.accessMethodID,
            programmeAcronym: form.programmeAcronym,
            date: isoDate
        };

        setLoading(true);
        try {
            const response = await enrolStudentInProgramme(formDataToSend);
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
    const programmeOptions = programmes.map(prog => ({ value: prog.acronym, label: prog.name }));

    const isFormValid = () => {
        return (
            form.studentID &&
            form.programmeAcronym &&
            form.departmentID &&
            form.accessMethodID &&
            form.date &&
            Object.values(formErrors).every(error => !error) &&
            studentNotFound===false
        );
    };

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
                                <label className="form-label" htmlFor="studentID">Student Unique
                                    Number</label>
                                <input
                                    className={`form-input ${form.studentID && !/^\d{7}$/.test(form.studentID) ? 'input-error' : ''}`}
                                    type="text"
                                    id="studentID"
                                    name="studentID"
                                    placeholder="Enter 7-digit Student Unique Number"
                                    value={form.studentID}
                                    onChange={(e) => {
                                        const rawValue = e.target.value.replace(/\D/g, '');
                                        if (rawValue.length <= 7) {
                                            setForm(f => ({...f, studentID: rawValue}));
                                        }
                                    }}
                                    onBlur={handleStudentBlur}
                                    inputMode="numeric"
                                    maxLength={7}
                                    style={{width: '554px'}}
                                    required
                                />
                                {form.studentID && form.studentID.length !== 7 && (
                                    <p className="input-warning" style={{color: 'red', marginTop: '0.5rem'}}>
                                        Student ID must be exactly 7 digits.
                                    </p>
                                )}
                                {studentNotFound && (
                                    <p className="input-warning" style={{ color: 'red', marginTop: '0.5rem' }}>
                                        Student does not exist.
                                    </p>
                                )}
                            </div>

                            <div className="form-group">
                                <label className="form-label" htmlFor="departmentID">Department</label>
                                <Select
                                    id="departmentID"
                                    name="departmentID"
                                    aria-label="Department"
                                    options={departmentOptions}
                                    value={departmentOptions.find(o => o.value === form.departmentID)}
                                    onChange={handleDepartmentChange}
                                    placeholder="Select Department"
                                    isSearchable
                                    isDisabled={studentNotFound}
                                    isClearable
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
                                <label className="form-label" htmlFor="programmeAcronym">Programme</label>
                                <Select
                                    id="programmeAcronym"
                                    name="programmeAcronym"
                                    options={programmeOptions}
                                    value={programmeOptions.find(o => o.value === form.programmeAcronym)}
                                    onChange={handleProgrammeChange}
                                    placeholder="Select Programme"
                                    isDisabled={studentNotFound || !form.departmentID}
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
                                <label className="form-label" htmlFor="accessMethodID">Access Method</label>
                                <Select
                                    id="accessMethodID"
                                    name="accessMethodID"
                                    options={accessMethodIDs.map(am => ({value: am.id, label: am.name}))}
                                    value={accessMethodIDs.map(am => ({
                                        value: am.id,
                                        label: am.name
                                    })).find(o => o.value === form.accessMethodID)}
                                    onChange={handleaccessMethodIDChange}
                                    placeholder="Select Access Method"
                                    isSearchable
                                    isDisabled={studentNotFound || !form.programmeAcronym}
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
                                <label className="form-label" htmlFor="date">Enrolment Date
                                </label>
                                <input
                                    disabled={studentNotFound || !form.accessMethodID}
                                    className={`form-input ${form.date && !/^(0[1-9]|[12][0-9]|3[01])-(0[1-9]|1[0-2])-\d{4}$/.test(form.date) ? 'input-error' : ''}`}
                                    type="text"
                                    id="date"
                                    name="date"
                                    placeholder="e.g. 12-06-2025"
                                    value={form.date}
                                    onChange={handleDateChange}
                                    inputMode="numeric"
                                    maxLength={10}
                                    style={{width: '554px'}}
                                    required
                                />
                                {form.date && !/^(0[1-9]|[12][0-9]|3[01])-(0[1-9]|1[0-2])-\d{4}$/.test(form.date) && (
                                    <p className="input-warning" style={{color: 'red', marginTop: '0.5rem'}}>
                                        Date must be in DD-MM-YYYY format.
                                    </p>
                                )}
                            </div>

                            <div className="form-actions">
                                <button type="submit" className="btn btn-primary" disabled={!isFormValid()}>
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
                        <p><strong>Student ID:</strong> {form.studentID}</p>
                        <p>
                            <strong>Department:</strong> {departmentOptions.find(d => d.value === form.departmentID)?.label}
                        </p>
                        <p><strong>Programme:</strong> {programmeOptions.find(p => p.value === form.programmeAcronym)?.label}
                        </p>
                        <p><strong>Enrolment Date:</strong> {form.date}</p>
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
