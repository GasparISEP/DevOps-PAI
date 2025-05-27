import React, { useState } from 'react';
import { registerStudent } from '../../services/studentService';
import Select from 'react-select';
import CountryFlag from 'react-country-flag';
import countryList from 'react-select-country-list';
import PhoneInput from 'react-phone-input-2';
import 'react-phone-input-2/lib/style.css';
import ISEPLogoBranco from "../../assets/images/ISEP_logo-branco.png";
import '../../styles/Form.css'

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
    countryCode: '+351',
    phoneNumber: '',
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

        if (['studentID', 'nif', 'postalCodePart1', 'postalCodePart2'].includes(name)) {
            newValue = value.replace(/[^0-9]/g, '');
        } else if (['name', 'location', 'street'].includes(name)) {
            newValue = value.replace(/(^\w{1})|(\s+\w{1})/g, letra => letra.toUpperCase());
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
        <div className="form-main-component-div">
            <div className="form-main-grid">
                <div className="form-img-main-div student-img-background">
                    <div className="form-logo-img-div">
                        <img src={ISEPLogoBranco} alt="Logo do ISEP"/>
                    </div>
                </div>

                <form className="form" onSubmit={handleSubmit}>
                    <h1>Register Student</h1>

                    <div className="form-and-buttons-main-div">
                    <div className="form-div">
                            <div className="form-group">
                                <label className="form-label" htmlFor="studentID">Student ID</label>
                                <input
                                    className="form-input"
                                    placeholder="Enter Student ID"
                                    id="studentID"
                                    name="studentID"
                                    type="text"
                                    inputMode="numeric"
                                    pattern="\d*"
                                    maxLength="8"
                                    value={form.studentID}
                                    onChange={handleChange}
                                    required
                                    style={{ width: '300px' }}
                                />
                            </div>

                            <div className="form-group">
                                <label className="form-label" htmlFor="name">Name</label>
                                <input
                                    className="form-input"
                                    placeholder="Enter required information"
                                    id="name"
                                    name="name"
                                    type="text"
                                    value={form.name}
                                    onChange={handleChange}
                                    required
                                    style={{ width: '300px' }}
                                />
                            </div>

                            <div className="form-group">
                                <label className="form-label" htmlFor="nif">NIF</label>
                                <input
                                    className="form-input"
                                    placeholder="Enter NIF"
                                    id="nif"
                                    name="nif"
                                    type="text"
                                    inputMode="numeric"
                                    pattern="\d*"
                                    maxLength="9"
                                    value={form.nif}
                                    onChange={handleChange}
                                    required
                                    style={{ width: '300px' }}
                                />
                            </div>

                            <div className="form-group">
                                <label className="form-label" htmlFor="nifcountry">NIF Country</label>
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
                                    styles={{ control: (base) => ({ ...base, width: '310px' }) }}
                                />
                            </div>

                            <div className="form-group">
                                <label className="form-label" htmlFor="street">Street</label>
                                <input
                                    className="form-input"
                                    placeholder="Enter Street"
                                    id="street"
                                    name="street"
                                    type="text"
                                    value={form.street}
                                    onChange={handleChange}
                                    required
                                    style={{ width: '300px' }}
                                />
                            </div>

                            <div className="form-group postal-code-group">
                                <label className="form-label" htmlFor="postalCodePart1">Postal Code</label>
                                <div className="postal-code-inputs">
                                    <input id="postalCodePart1" name="postalCodePart1" type="text" value={form.postalCodePart1 || ''} onChange={handleChange} pattern="\d{4}" maxLength="4" required placeholder="0000" style={{ width: '160px' }} />
                                    <span className="postal-code-separator">-</span>
                                    <input id="postalCodePart2" name="postalCodePart2" type="text" value={form.postalCodePart2 || ''} onChange={handleChange} pattern="\d{3}" maxLength="3" required placeholder="000" style={{ width: '130px' }} />
                                </div>
                            </div>

                            <div className="form-group">
                                <label className="form-label" htmlFor="location">Location</label>
                                <input
                                    className="form-input"
                                    placeholder="Enter Location"
                                    id="location"
                                    name="location"
                                    type="text"
                                    value={form.location}
                                    onChange={handleChange}
                                    required
                                    style={{ width: '300px' }}
                                />
                            </div>

                            <div className="form-group">
                                <label className="form-label" htmlFor="addressCountry">Address Country</label>
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
                                    styles={{ control: (base) => ({ ...base, width: '310px' }) }}
                                />
                            </div>


                            <div className="form-group">
                                <label className="form-label" htmlFor="phone">Phone</label>
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
                                    inputStyle={{ width: '310px' }}
                                />
                            </div>

                            <div className="form-group">
                                <label className="form-label" htmlFor="email">E-mail</label>
                                <input
                                    className="form-input"
                                    placeholder="Enter Email"
                                    id="email"
                                    name="email"
                                    type="email"
                                    value={form.email}
                                    onChange={handleChange}
                                    required
                                    style={{ width: '300px' }}
                                />
                            </div>

                            {error && <div className="error">⚠️ {error}</div>}

                            <div className="form-actions">
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