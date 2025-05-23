import React, { useState } from 'react';

export default function StudentForm() {
    const [form, setForm]       = useState({
        studentID: '', name: '', NIF: '', NIFCountry: '',
        street: '', postalCode: '', location: '', addressCountry: '',
        phoneCountryCode: '', phoneNumber: '', email: ''
    });
    const [loading, setLoading] = useState(false);
    const [error, setError]     = useState('');
    const [success, setSuccess] = useState(null);

    function handleChange(e) {
        setForm(f => ({ ...f, [e.target.name]: e.target.value }));
    }

    async function handleSubmit(e) {
        e.preventDefault();
        setError(''); setSuccess(null); setLoading(true);
        try {
            // Aqui chamarias o teu studentService.registerStudent(form)
            // const resp = await registerStudent(form);
            // setSuccess(resp);
            setSuccess({ studentID: form.studentID });
        } catch (err) {
            setError(err.message);
        } finally {
            setLoading(false);
        }
    }

    const firstFields = [
        { label:'Student ID',   name:'studentID',    type:'number', required:true },
        { label:'Name',         name:'name',         type:'text',   required:true },
        { label:'NIF',          name:'NIF',          type:'text',   required:true },
        { label:'NIF Country',  name:'NIFCountry',   type:'text',   required:true }
    ];
    const restFields = [
        'street','postalCode','location','addressCountry',
        'phoneCountryCode','phoneNumber','email'
    ];

    return (
        <form className="student-form" onSubmit={handleSubmit}>
            <div className="form-grid">
                {firstFields.map(f => (
                    <div className="form-group" key={f.name}>
                        <label htmlFor={f.name}>{f.label}</label>
                        <input
                            id={f.name}
                            name={f.name}
                            type={f.type}
                            required={f.required}
                            value={form[f.name]}
                            onChange={handleChange}
                        />
                    </div>
                ))}
                {restFields.map(name => (
                    <div className="form-group" key={name}>
                        <label htmlFor={name}>
                            {name.replace(/([A-Z])/g, ' $1').replace(/^./, s => s.toUpperCase())}
                        </label>
                        <input
                            id={name}
                            name={name}
                            type={name === 'email' ? 'email' : 'text'}
                            value={form[name]}
                            onChange={handleChange}
                        />
                    </div>
                ))}
            </div>

            {error && <div className="error">{error}</div>}

            <div className="form-actions">
                <button
                    type="button"
                    className="btn btn-secondary"
                    onClick={() => window.history.back()}
                    disabled={loading}
                >
                    Cancel
                </button>

                <button
                    type="submit"
                    className="btn btn-primary"
                    disabled={loading}
                >
                    {loading ? 'Registering…' : 'Register'}
                </button>
            </div>

            {success && (
                <div className="success" style={{ marginTop: '1rem', color: '#080' }}>
                    Student registered with ID {success.studentID}
                </div>
            )}
        </form>
    );
}
