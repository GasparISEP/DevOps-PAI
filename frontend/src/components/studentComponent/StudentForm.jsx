import React, { useState } from 'react';
import { registerStudent } from '../../services/studentService';
import studentImage from '../../assets/images/form-image.jpg';
import Select from 'react-select';
import CountryFlag from 'react-country-flag';
import countryList from 'react-select-country-list';
import PhoneInput from 'react-phone-input-2';
import 'react-phone-input-2/lib/style.css';

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
    countryCode: '+351',   // Indicativo inicial (Portugal)
    phoneNumber: '',       // Número local
    email: ''
};

export default function StudentForm() {
    const [form, setForm] = useState(initialForm);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState('');
    const [success, setSuccess] = useState(null);

    function handleChange(e) {
        const { name, value } = e.target;
        let newValue = value;

        if (['name', 'street', 'location'].includes(name)) {
            newValue = value.replace(/\b\w/g, (char) => char.toUpperCase());
        }

        if (['nif', 'postalCodePart1', 'postalCodePart2'].includes(name)) {
            newValue = value.replace(/\D/g, '');
        }

        setForm(f => ({ ...f, [name]: newValue }));
    }

    async function handleSubmit(e) {
        e.preventDefault();
        setError('');
        setSuccess(null);

        if (!form.countryCode || !form.phoneNumber) {
            setError('⚠️ Preencha o indicativo e o número de telefone.');
            return;
        }
        if (!/^\d{4}$/.test(form.postalCodePart1) || !/^\d{3}$/.test(form.postalCodePart2)) {
            setError('⚠️ Código postal inválido. Use formato XXXX-XXX.');
            return;
        }
        if (!/^\d{9}$/.test(form.nif)) {
            setError('⚠️ NIF inválido. Deve ter 9 dígitos.');
            return;
        }

        setLoading(true);

        const payload = {
            ...form,
            postalCode: `${form.postalCodePart1}-${form.postalCodePart2}`,
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
                                { label: 'Name', name: 'name' },
                                { label: 'NIF', name: 'nif', type: 'text' }
                            ].map(({ label, name, type = 'text' }) => (
                                <div className="student-form-group" key={name}>
                                    <label className="student-form-label" htmlFor={name}>{label}</label>
                                    <input className="student-form-input" placeholder="Enter required information" id={name} name={name} type={type} value={form[name]} onChange={handleChange} required />
                                </div>
                            ))}

                            <div className="student-form-group">
                                <label className="student-form-label" htmlFor="nifcountry">NIF Country</label>
                                <Select
                                    id="nifcountry"
                                    name="nifcountry"
                                    classNamePrefix="student-form-select"
                                    options={countryList().getData()}
                                    value={countryList().getData().find(option => option.label === form.nifcountry)}
                                    onChange={option => setForm(f => ({ ...f, nifcountry: option?.label ?? '' }))}
                                    formatOptionLabel={option => (
                                        <div style={{ display: 'flex', alignItems: 'center', gap: 8 }}>
                                            <CountryFlag countryCode={option.value} svg style={{ width: '1.5em', height: '1.5em' }} />
                                            <span>{option.label}</span>
                                        </div>
                                    )}
                                    placeholder="Select Country"
                                    isSearchable
                                    menuPlacement="auto"
                                    menuPosition="fixed"
                                />
                            </div>

                            <div className="student-form-group">
                                <label className="student-form-label" htmlFor="street">Street</label>
                                <input className="student-form-input" placeholder="Enter Street" id="street" name="street" value={form.street} onChange={handleChange} required />
                            </div>

                            <div className="student-form-group postal-code-group">
                                <label className="student-form-label" htmlFor="postalCodePart1">Postal Code</label>
                                <div className="postal-code-inputs">
                                    <input id="postalCodePart1" name="postalCodePart1" type="text" value={form.postalCodePart1 || ''} onChange={handleChange} pattern="\d{4}" maxLength="4" required placeholder="0000" />
                                    <span className="postal-code-separator">-</span>
                                    <input id="postalCodePart2" name="postalCodePart2" type="text" value={form.postalCodePart2 || ''} onChange={handleChange} pattern="\d{3}" maxLength="3" required placeholder="000" />
                                </div>
                            </div>

                            <div className="student-form-group">
                                <label className="student-form-label" htmlFor="location">Location</label>
                                <input className="student-form-input" placeholder="Enter Location" id="location" name="location" value={form.location} onChange={handleChange} required />
                            </div>

                            <div className="student-form-group">
                                <label className="student-form-label" htmlFor="addressCountry">Address Country</label>
                                <Select
                                    id="addressCountry"
                                    name="addressCountry"
                                    classNamePrefix="student-form-select"
                                    options={countryList().getData()}
                                    value={countryList().getData().find(option => option.label === form.addressCountry)}
                                    onChange={option => setForm(f => ({ ...f, addressCountry: option?.label ?? '' }))}
                                    formatOptionLabel={option => (
                                        <div style={{ display: 'flex', alignItems: 'center', gap: 8 }}>
                                            <CountryFlag countryCode={option.value} svg style={{ width: '1.5em', height: '1.5em' }} />
                                            <span>{option.label}</span>
                                        </div>
                                    )}
                                    placeholder="Select Country"
                                    isSearchable
                                    menuPlacement="auto"
                                    menuPosition="fixed"
                                />
                            </div>

                            <div className="student-form-group">
                                <label className="student-form-label" htmlFor="phone">Phone</label>
                                <PhoneInput
                                    country={'pt'}
                                    value={form.countryCode + form.phoneNumber}
                                    onChange={(value, data) => {
                                        const newCountryCode = '+' + data.dialCode;
                                        const phone = value.replace(new RegExp(`^\\+?${data.dialCode}`), '').trim();
                                        setForm(f => ({
                                            ...f,
                                            countryCode: newCountryCode,
                                            phoneNumber: phone
                                        }));
                                    }}
                                    containerClass="student-phone-row"
                                    buttonClass="student-phone-country"
                                    inputClass="student-phone-number student-form-input"
                                    dropdownStyle={{ zIndex: 9999 }}
                                    enableSearch
                                    searchClass="student-form-input"
                                    required
                                />
                            </div>

                            <div className="student-form-group">
                                <label className="student-form-label" htmlFor="email">E-mail</label>
                                <input className="student-form-input" placeholder="Enter Email" id="email" name="email" type="email" value={form.email} onChange={handleChange} required />
                            </div>

                            {error && <div className="error">⚠️ {error}</div>}

                            <div className="student-form-actions">
                                <button type="button" className="btn btn-secondary" onClick={() => window.history.back()} disabled={loading}>
                                    CANCEL
                                </button>
                                <button type="submit" className="btn btn-primary" disabled={loading}>
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