import React, { useEffect, useState } from 'react';
import { registerProgramme } from '../../services/programmeService';
import ISEPLogoBranco from '../../assets/images/ISEP_logo-branco.png';
import '../../styles/Form.css'
import { Link } from "react-router-dom";
import ProgrammeSuccessModal from "./ProgrammeSuccessModal";
import ProgrammeErrorModal from "./ProgrammeErrorModal";

export default function ProgrammeForm() {
    const [form, setForm] = useState({
        name: '',
        acronym: '',
        quantSemesters: '',
        maxEcts: '',
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
    const [detailsDisplayed, setDetailsDisplayed] = useState(false);
    const [loadingDetails, setLoadingDetails] = useState(false);

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

                setDepartments(deptData._embedded?.departmentWithDirectorDTOList || []);
                setTeachers(teacherData._embedded?.teacherDTOList || []);
                setDegreeTypes(degreeTypeData._embedded?.degreeTypeDTOList || []);
            } catch (err) {
                console.error("Failed to load options:", err);
            }
        }
        fetchOptions();
    }, []);

    function handleChange(e) {
        const { name, value } = e.target;

        setForm((prevForm) => {
            const updatedForm = { ...prevForm, [name]: value };

            if (name === "degreeTypeID") {
                const selectedDegree = degreeTypes.find(d => d.id.toString() === value);
                if (selectedDegree) {
                    const maxEcts = parseInt(selectedDegree.maxEcts);
                    updatedForm.maxEcts = maxEcts || '';

                    updatedForm.quantSemesters = maxEcts ? Math.ceil(maxEcts / 30) : '';
                } else {
                    updatedForm.quantSemesters = '';
                    updatedForm.maxEcts = '';
                }
            }

            return updatedForm;
        });
    }

    function handleClear() {
        setForm({
            name: '',
            acronym: '',
            quantSemesters: '',
            maxEcts: '',
            degreeTypeID: '',
            departmentID: '',
            teacherID: ''
        });
        setDetailsDisplayed(false);
    }

    async function handleSubmit(e) {
        e.preventDefault();
        setError('');
        setSuccess(null);
        setLoading(true);
        try {
            const selectedDegreeType = degreeTypes.find(dt => dt.id.toString() === form.degreeTypeID);
            if (!selectedDegreeType) throw new Error('Degree Type not selected or invalid.');

            const payload = {
                ...form,
                quantSemesters: parseInt(form.quantSemesters),
                maxECTS: parseInt(form.maxEcts)
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

    async function handleDisplayDetails() {
        if (!success?._links?.self?.href) {
            setError("No details link available");
            setShowErrorModal(true);
            return;
        }

        setLoadingDetails(true);

        try {
            const res = await fetch(success._links.self.href);
            if (!res.ok) throw new Error("Failed to fetch programme details");

            const displayProgramme = await res.json();
            setSuccess(displayProgramme);
            setDetailsDisplayed(true);
        } catch (err) {
            setError(err.message || "Failed to load details");
            setShowErrorModal(true);
        } finally {
            setLoadingDetails(false);
        }
    }


    const selectedDegree = degreeTypes.find(d => d.id.toString() === form.degreeTypeID);


    const displayedMaxEcts = selectedDegree ? parseInt(selectedDegree.maxEcts) : null;
    const displayedSemesters = displayedMaxEcts ? Math.ceil(displayedMaxEcts / 30) : null;

    return (
        <div className="form-main-component-div">
            <div className="form-main-grid">
                <div className="form-img-main-div">
                    <div className="form-logo-img-div">
                        <img src={ISEPLogoBranco} alt="Logo do ISEP" />
                    </div>
                </div>

                <form className="form" onSubmit={handleSubmit} autoComplete="off">

                    <div style={{ display: 'flex', alignItems: 'center', justifyContent: 'space-between', marginBottom: '2rem' }}>
                        <h1 style={{ margin: 0 }}>Register Programme</h1>
                        <Link to="/" className="pagination-btn2 pagination-btn-secondary" style={{ textDecoration: 'none' }}>
                            Back to Home Page
                        </Link>
                    </div>

                    <div className="form-and-buttons-main-div">
                        <div className="form-div">

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
                                <div className="semester-and-Ects-div">
                                    {displayedSemesters !== null && (
                                        <p>Semesters: {displayedSemesters}</p>
                                    )}
                                    {displayedMaxEcts !== null && (
                                        <p>Max ECTS: {displayedMaxEcts}</p>
                                    )}
                                </div>
                            </div>

                            <div className="form-group">
                                <label className="form-label" htmlFor="departmentID">Department</label>
                                <select className="form-input" id="departmentID" name="departmentID" value={form.departmentID} onChange={handleChange} required>
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
                                <select className="form-input" id="teacherID" name="teacherID" value={form.teacherID} onChange={handleChange} required>
                                    <option value="" disabled hidden>Select Teacher</option>
                                    {teachers.map(t => (
                                        <option key={t.id} value={t.id}>
                                            {t.name}
                                        </option>
                                    ))}
                                </select>
                            </div>

                            <div className="form-group">
                                <label className="form-label" htmlFor="name">Name</label>
                                <input className="form-input" placeholder="Enter Programme's name" id="name" name="name" value={form.name} onChange={handleChange} required />
                            </div>

                            <div className="form-group">
                                <label className="form-label" htmlFor="acronym">Acronym</label>
                                <input className="form-input" placeholder="Enter Programme's acronym" id="acronym" name="acronym" value={form.acronym} onChange={handleChange} required />
                            </div>

                            <div className="form-actions">
                                <button type="button" className="btn btn-secondary" onClick={handleClear} disabled={loading}>
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

            {showModal && (
                <ProgrammeSuccessModal
                    success={success}
                    degreeTypes={degreeTypes}
                    departments={departments}
                    teachers={teachers}
                    onDisplay={handleDisplayDetails}
                    onClose={() => setShowModal(false)}
                    detailsDisplayed={detailsDisplayed}
                />
            )}

            {showErrorModal && (
                <ProgrammeErrorModal
                    error={error}
                    onClose={() => setShowErrorModal(false)}
                />
            )}
        </div>
    );
}
