import React, { useEffect, useState } from 'react';
import { registerProgramme } from '../../services/programmeService';
import ISEPLogoBranco from '../../assets/images/ISEP_logo-branco.png';
import '../../styles/Form.css'
import {Link} from "react-router-dom";

export default function ProgrammeForm() {
    const [form, setForm] = useState({
        name: '',
        acronym: '',
        quantSemesters: '',
        degreeTypeID: '',
        departmentID: '',
        teacherID: ''
    });

    const [departments, setDepartments] = useState([]);
    const [teachers, setTeachers] = useState([]);
    const [degreeTypes, setDegreeTypes] = useState([]);

    const [loading, setLoading] = useState(false);
    const [error, setError] = useState('');
    const [success, setSuccess] = useState(null);
    const [showModal, setShowModal] = useState(false);
    const [showErrorModal, setShowErrorModal] = useState(false);


    useEffect(() => {
        async function fetchOptions() {
            try {
                const [deptRes, teacherRes, degreeTypeRes] = await Promise.all([
                    fetch(`${process.env.REACT_APP_API_URL}/departments`),
                    fetch(`${process.env.REACT_APP_API_URL}/teachers`),
                    fetch(`${process.env.REACT_APP_API_URL}/degreetypes`)
                ]);
                const deptData = await deptRes.json();
                const teacherData = await teacherRes.json();
                const degreeTypeData = await degreeTypeRes.json();

                setDepartments(deptData);
                setTeachers(teacherData);
                setDegreeTypes(degreeTypeData);
            } catch (err) {
                console.error("Failed to load options:", err);
            }
        }
        fetchOptions();
    }, []);

    function handleChange(e) {
        setForm(f => ({ ...f, [e.target.name]: e.target.value }));
    }

    function handleClear() {
        setForm({
            name: '',
            acronym: '',
            quantSemesters: '',
            degreeTypeID: '',
            departmentID: '',
            teacherID: ''
        });
    }

    async function handleSubmit(e) {
        e.preventDefault();
        setError('');
        setSuccess(null);
        setLoading(true);
        try {
            const selectedDegreeType = degreeTypes.find(dt => dt.id === form.degreeTypeID);
            const payload = {
                ...form,
                maxECTS: parseInt(selectedDegreeType.maxEcts),
                quantSemesters: parseInt(form.quantSemesters)
            };
            const response = await registerProgramme(payload);
            setSuccess(response);
            setShowModal(true);

            handleClear();

        } catch (err) {
            setError(err.message || "An unexpected error occurred.");
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
                        <h1 style={{margin: 0}}>Register Programme</h1>
                        <Link to="/" className="pagination-btn2 pagination-btn-secondary"
                              style={{textDecoration: 'none'}}>
                            Back to Main Page
                        </Link>
                    </div>

                    <div className="form-and-buttons-main-div">
                        <div className="form-div">
                            <div className="form-group">
                                <label className="form-label" htmlFor="name">Name</label>
                                <input className="form-input" placeholder="Enter Programme's name" id="name" name="name"
                                       value={form.name} onChange={handleChange} required/>
                            </div>

                            <div className="form-group">
                                <label className="form-label" htmlFor="acronym">Acronym</label>
                                <input className="form-input" placeholder="Enter Programme's acronym" id="acronym"
                                       name="acronym"
                                       value={form.acronym} onChange={handleChange} required/>
                            </div>

                            <div className="form-group">
                                <label className="form-label" htmlFor="quantSemesters">Semesters</label>
                                <input className="form-input"
                                       placeholder="Enter number of semesters"
                                       id="quantSemesters"
                                       name="quantSemesters"
                                       type="number"
                                       min="1"
                                       max="10"
                                       value={form.quantSemesters}
                                       onChange={handleChange}
                                       required/>
                            </div>

                            <div className="form-group">
                                <label className="form-label" htmlFor="degreeTypeID">Degree Type</label>
                                <select
                                    className="form-input"
                                    id="degreeTypeID"
                                    name="degreeTypeID"
                                    value={form.degreeTypeID}
                                    onChange={handleChange}
                                    required
                                >
                                    <option value="" disabled hidden>Select Degree Type</option>
                                    {degreeTypes.map(d => (
                                        <option key={d.id} value={d.id}>{d.name}</option>
                                    ))}
                                </select>
                            </div>

                            <div className="form-group">
                                <label className="form-label" htmlFor="departmentID">Department</label>
                                <select className="form-input" id="departmentID" name="departmentID"
                                        value={form.departmentID} onChange={handleChange} required>
                                    <option value="" disabled hidden>Select Department</option>
                                    {departments.map(d => (
                                        <option key={d.id} value={d.id}>
                                            {d.name} ({d.acronym})
                                        </option>
                                    ))}
                                </select>
                            </div>

                            <div className="form-group">
                                <label className="form-label" htmlFor="teacherID">Programme's Director</label>
                                <select className="form-input" id="teacherID" name="teacherID"
                                        value={form.teacherID} onChange={handleChange} required>
                                    <option value="" disabled hidden>Select Teacher</option>
                                    {teachers.map(t => (
                                        <option key={t.id} value={t.id}>
                                            {t.name}
                                        </option>
                                    ))}
                                </select>
                            </div>

                            <div className="form-actions">
                                <button type="button" className="btn btn-secondary"
                                        onClick={handleClear}
                                        disabled={loading}>
                                    CLEAR
                                </button>
                                <button type="submit" className="btn btn-primary" disabled={loading}>
                                    {loading ? 'Registering…' : 'REGISTER'}
                                </button>
                            </div>
                        </div>
                    </div>
                </form>
            </div>

            {showModal && (
                <div className="modal-overlay">
                    <div className="modal-content">
                        <h2>Success!</h2>
                        <p>The programme was registered successfully.</p>
                        {success && (
                            <div className="success" style={{marginTop: '1rem', color: '#080'}}>
                            <p><strong>Name:</strong> {success.name}</p>
                                <p><strong>Acronym:</strong> {success.acronym}</p>
                                <p><strong>Semesters:</strong> {success.quantSemesters}</p>
                                <p><strong>Degree Type:</strong> {degreeTypes.find(dt => dt.id === success.degreeTypeID)?.name || 'Unknown'}</p>
                                <p><strong>ECTS Credits:</strong> {degreeTypes.find(dt => dt.id === success.degreeTypeID)?.maxEcts || 'Unknown'}</p>
                                <p><strong>Department:</strong> {departments.find(d => d.id === success.departmentID)?.name || 'Unknown'}</p>
                                <p><strong>Programme's Director:</strong> {teachers.find(t => t.id === success.teacherID)?.name || 'Unknown'}</p>
                            </div>
                        )}
                        <button className="modal-btn" onClick={() => setShowModal(false)}>Close</button>
                    </div>
                </div>
            )}
            {showErrorModal && (
                <div className="modal-overlay">
                    <div className="modal-content" style={{ borderColor: 'red' }}>
                        <h2 style={{ color: 'red' }}>Registration Error</h2>
                        <p>{error}</p>
                        <button className="modal-btn" onClick={() => setShowErrorModal(false)}>Close</button>
                    </div>
                </div>
            )}
        </div>
    );
}
