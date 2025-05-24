import React, { useState } from 'react';
import { registerProgramme } from '../../services/programmeService';
import formImage from '../../assets/images/form-image.jpg';

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

        <div className="programme-main-component-div">
        <div className="programme-main-grid">

            <div className="img-main-div">
                <img className="form-img" src={formImage} alt="Person typing on a computer."/>
            </div>

            <form className="programme-form" onSubmit={handleSubmit}>
                <h1>Register Programme</h1>

                <div className="programme-form-and-buttons-main-div">

                    <div className="programme-form-div">

                        <div className="programme-form-group">
                            <label className="programme-form-label" htmlFor="name">Name</label>
                            <input className="programme-form-input" placeholder="Enter your name" id="name" name="name"
                                   value={form.name} onChange={handleChange} required/>
                        </div>

                        <div className="programme-form-group">
                            <label className="programme-form-label" htmlFor="acronym">Acronym</label>
                            <input className="programme-form-input" id="acronym" name="acronym" value={form.acronym}
                                   onChange={handleChange} required/>
                        </div>

                        <div className="programme-form-group">
                            <label className="programme-form-label" htmlFor="quantECTS">ECTS</label>
                            <input className="programme-form-input" id="quantECTS" name="quantECTS" type="number"
                                   value={form.quantECTS} onChange={handleChange} required/>
                        </div>

                        <div className="programme-form-group">
                            <label className="programme-form-label" htmlFor="quantSemesters">Semesters</label>
                            <input className="programme-form-input" id="quantSemesters" name="quantSemesters" type="number"
                                   value={form.quantSemesters} onChange={handleChange} required/>
                        </div>

                        <div className="programme-form-group">
                            <label className="programme-form-label" htmlFor="degreeTypeID">Degree Type</label>
                            <input className="programme-form-input" id="degreeTypeID" name="degreeTypeID"
                                   value={form.degreeTypeID} onChange={handleChange} required/>
                        </div>

                        <div className="programme-form-group">
                            <label className="programme-form-label" htmlFor="departmentID">Department</label>
                            <input className="programme-form-input" id="departmentID" name="departmentID"
                                   value={form.departmentID} onChange={handleChange} required/>
                        </div>

                        <div className="programme-form-group">
                            <label className="programme-form-label" htmlFor="teacherID">Programme's Director</label>
                            <input className="programme-form-input" id="teacherID" name="teacherID" value={form.teacherID}
                                   onChange={handleChange} required/>
                        </div>
                    </div>

                    {error && <div className="error">{error}</div>}

                    <div className="programme-form-actions">
                        <button type="button" className="btn btn-secondary" onClick={() => window.history.back()}
                                disabled={loading}>
                            CANCEL
                        </button>
                        <button type="submit" className="btn btn-primary" disabled={loading}>
                            {loading ? 'Registering…' : 'REGISTER'}
                        </button>
                    </div>
                </div>

                {success && (
                    <div className="success" style={{marginTop: '1rem', color: '#080'}}>
                        Programme registered successfully!
                    </div>
                )}
            </form>
        </div>
        </div>
    );
}
