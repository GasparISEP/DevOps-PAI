import React, { useState } from 'react';
import { registerProgramme } from '../../services/programmeService';

export default function ProgrammeForm() {
    const [form, setForm] = useState({
        name: '',
        acronym: '',
        quantECTS: '',
        quantSemesters: '',
        degreeTypeID: '',
        departmentID: '',
        teacherID: ''
    });

    const [loading, setLoading] = useState(false);
    const [error, setError] = useState('');
    const [success, setSuccess] = useState(null);

    function handleChange(e) {
        setForm(f => ({ ...f, [e.target.name]: e.target.value }));
    }

    async function handleSubmit(e) {
        e.preventDefault();
        setError('');
        setSuccess(null);
        setLoading(true);
        try {
            // Converte quantECTS e quantSemesters para número
            const payload = {
                ...form,
                quantECTS: parseInt(form.quantECTS),
                quantSemesters: parseInt(form.quantSemesters)
            };

            const response = await registerProgramme(payload);
            setSuccess(response);
        } catch (err) {
            setError(err.message);
        } finally {
            setLoading(false);
        }
    }

    return (
        <form className="programme-form" onSubmit={handleSubmit}>
            <div className="form-grid">
                <div className="form-group">
                    <label htmlFor="name">Name</label>
                    <input id="name" name="name" value={form.name} onChange={handleChange} required />
                </div>

                <div className="form-group">
                    <label htmlFor="acronym">Acronym</label>
                    <input id="acronym" name="acronym" value={form.acronym} onChange={handleChange} required />
                </div>

                <div className="form-group">
                    <label htmlFor="quantECTS">ECTS</label>
                    <input id="quantECTS" name="quantECTS" type="number" value={form.quantECTS} onChange={handleChange} required />
                </div>

                <div className="form-group">
                    <label htmlFor="quantSemesters">Semesters</label>
                    <input id="quantSemesters" name="quantSemesters" type="number" value={form.quantSemesters} onChange={handleChange} required />
                </div>

                <div className="form-group">
                    <label htmlFor="degreeTypeID">Degree Type ID</label>
                    <input id="degreeTypeID" name="degreeTypeID" value={form.degreeTypeID} onChange={handleChange} required />
                </div>

                <div className="form-group">
                    <label htmlFor="departmentID">Department ID</label>
                    <input id="departmentID" name="departmentID" value={form.departmentID} onChange={handleChange} required />
                </div>

                <div className="form-group">
                    <label htmlFor="teacherID">Teacher ID</label>
                    <input id="teacherID" name="teacherID" value={form.teacherID} onChange={handleChange} required />
                </div>
            </div>

            {error && <div className="error">{error}</div>}

            <div className="form-actions">
                <button type="button" className="btn btn-secondary" onClick={() => window.history.back()} disabled={loading}>
                    Cancel
                </button>
                <button type="submit" className="btn btn-primary" disabled={loading}>
                    {loading ? 'Registering…' : 'Register'}
                </button>
            </div>

            {success && (
                <div className="success" style={{ marginTop: '1rem', color: '#080' }}>
                    Programme registered successfully!
                </div>
            )}
        </form>
    );
}
