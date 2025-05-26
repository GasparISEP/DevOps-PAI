import React, { useState } from 'react';
import { registerStudent } from '../../services/studentService';
import studentImage from '../../assets/images/form-image.jpg';

const initialForm = {
    studentID: '',
    name: '',
    nif: '',
    nifcountry: '',
    street: '',
    postalCodePart1: '',
    postalCodePart2: '',
    location: '',
    addressCountry: '',
    phoneCountryCode: '',
    phoneNumber: '',
    email: ''
};

export default function StudentForm() {
    const [form, setForm] = useState(initialForm);
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

        const payload = {
            ...form,
            postalCode: form.postalCodePart1 + '-' + form.postalCodePart2,
            studentID: Number(form.studentID),
            academicEmail: `${form.studentID}@isep.ipp.pt`
        };

        try {
            const resp = await registerStudent(payload);
            setSuccess(resp);
        } catch (err) {
            setError(err.message);
        } finally {
            setLoading(false);
        }
    }

    return (
        <div className="student-main-component-div">
            <div className="student-main-grid">

                <div className="img-main-div">
                    <img className="form-img" src={studentImage} alt="Student registration visual" />
                </div>

                <form className="student-form" onSubmit={handleSubmit}>
                    <h1>Register Student</h1>

                    <div className="student-form-and-buttons-main-div">
                        <div className="student-form-div">
                            {[
                                { label: 'Student ID', name: 'studentID', type: 'number' },
                                { label: 'Name', name: 'name' },
                                { label: 'NIF', name: 'nif', type: 'number' },
                                { label: 'NIF Country', name: 'nifcountry', type:'text' },
                                { label: 'Street', name: 'street' },
                            ].map(({ label, name, type = 'text' }) => (
                                <div className="student-form-group" key={name}>
                                    <label className="student-form-label" htmlFor={name}>{label}</label>
                                    <input
                                        className="student-form-input"
                                        id={name}
                                        name={name}
                                        type={type}
                                        value={form[name]}
                                        onChange={handleChange}
                                        required
                                    />
                                </div>
                            ))}

                            <div className="student-form-group postal-code-group">
                                <label className="student-form-label" htmlFor="postalCodePart1">Postal Code</label>
                                <div className="postal-code-inputs">
                                    <input
                                        id="postalCodePart1"
                                        name="postalCodePart1"
                                        type="text"
                                        value={form.postalCodePart1 || ''}
                                        onChange={handleChange}
                                        pattern="\d{4}"
                                        maxLength="4"
                                        required
                                        className="postal-code-input"
                                    />
                                    <span className="postal-code-separator">-</span>
                                    <input
                                        id="postalCodePart2"
                                        name="postalCodePart2"
                                        type="text"
                                        value={form.postalCodePart2 || ''}
                                        onChange={handleChange}
                                        pattern="\d{3}"
                                        maxLength="3"
                                        required
                                        className="postal-code-input"
                                    />
                                </div>
                            </div>
                            {[
                                { label: 'Location', name: 'location' },
                                { label: 'Address Country', name: 'addressCountry' },
                                { label: 'Phone Country Code', name: 'phoneCountryCode' },
                                { label: 'Phone Number', name: 'phoneNumber' },
                                { label: 'Email', name: 'email', type: 'email' }
                            ].map(({ label, name, type = 'text' }) => (
                                <div className="student-form-group" key={name}>
                                    <label className="student-form-label" htmlFor={name}>{label}</label>
                                    <input
                                        className="student-form-input"
                                        id={name}
                                        name={name}
                                        type={type}
                                        value={form[name]}
                                        onChange={handleChange}
                                        required
                                    />
                                </div>
                            ))}


                            {error && <div className="error">⚠️ {error}</div>}

                            <div className="student-form-actions">
                                <button
                                    type="button"
                                    className="btn btn-secondary"
                                    onClick={() => window.history.back()}
                                    disabled={loading}
                                >
                                    CANCEL
                                </button>
                                <button
                                    type="submit"
                                    className="btn btn-primary"
                                    disabled={loading}
                                >
                                    {loading ? 'Registering…' : 'REGISTER'}
                                </button>
                            </div>

                            {success && (
                                <div className="success" style={{ marginTop: '1rem', color: '#080' }}>
                                    Student {success.name} (ID {success.studentID}) registered successfully!
                                </div>
                            )}
                        </div>
                    </div>
                </form>
            </div>
        </div>
    );
}
