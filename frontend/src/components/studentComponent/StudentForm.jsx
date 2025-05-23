import React, { useState } from 'react';
import Modal from './Modal';
import { registerStudent } from '../../services/studentService';


const initialForm = {
    studentID: '', name: '', NIF: '', NIFCountry: '',
    street: '', postalCode: '', location: '', addressCountry: '',
    phoneCountryCode: '', phoneNumber: '', email: ''
}

export default function StudentForm() {
    const [form, setForm]       = useState(initialForm);
    const [loading, setLoading] = useState(false);
    const [error, setError]     = useState('');
    const [successMsg, setSuccessMsg] = useState('');

    function handleChange(e) {
        setForm(f => ({ ...f, [e.target.name]: e.target.value }));
    }

    async function handleSubmit(e) {
        e.preventDefault();
        setError('');
        setSuccessMsg('');
        setLoading(true);


          const payload = {
                ...form,
                studentID: Number(form.studentID),
                academicEmail: `${form.studentID}@isep.ipp.pt`
          };
        try {
            // Aqui chamarias o teu studentService.registerStudent(form)
            // const resp = await registerStudent(form);
            // setSuccess(resp);
            const respDTO = await registerStudent(payload);
            setSuccessMsg(`Student "${respDTO.name}" (ID ${respDTO.studentID}) registered!`);

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

            {successMsg && (
                <Modal
                    message={successMsg}
                    onClose={() => {
                        setSuccessMsg('');        // apaga a mensagem de sucesso
                        setForm({                  // reseta o form
                            studentID: '',
                            name: '',
                            NIF: '',
                            NIFCountry: '',
                            street: '',
                            postalCode: '',
                            location: '',
                            addressCountry: '',
                            phoneCountryCode: '',
                            phoneNumber: '',
                            email: ''
                        });
                    }}
                    />
                  )}
        </form>
    );
}
