import React, { useState, useEffect } from 'react';
import { registerStudent } from '../../services/studentService';
import Select from 'react-select';
import CountryFlag from 'react-country-flag';
import countryList from 'react-select-country-list';
import PhoneInput from 'react-phone-input-2';
import 'react-phone-input-2/lib/style.css';
import ISEPLogoBranco from "../../assets/images/ISEP_logo-branco.png";
import '../../styles/Form.css';

const initialForm = {
    studentID: '',
    name: '',
    nif: '',
    nifcountry: '',
    street: '',
    postalCode: '',
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

    useEffect(() => {
        async function fetchLastStudentID() {
            try {
                const response = await fetch('http://localhost:8081/students', {
                    headers: { 'Accept': 'application/json' }
                });

                if (!response.ok) {
                    const text = await response.text();
                    throw new Error(`HTTP ${response.status} - ${text}`);
                }

                const data = await response.json();
                const nextID = (data.lastStudentID || 0) + 1;

                setForm(f => ({
                    ...f,
                    studentID: nextID.toString()
                }));
            } catch (err) {
                console.error("❌ Failed to fetch student ID:", err);
                setError('❌ Failed to fetch student ID from backend.');
            }
        }

        fetchLastStudentID();
    }, []);

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

        if (!form.phoneNumber) {
            setError('⚠️ Fill in the phone number.');
            return;
        }
        if (!/^\d{4}-\d{3}$/.test(form.postalCode)) {
            setError('⚠️ Invalid zip code.');
            return;
        }
        if (!/^\d{9}$/.test(form.nif)) {
            setError('⚠️ Invalid NIF. ');
            return;
        }

        setLoading(true);

        const payload = {
            ...form,
            studentID: Number(form.studentID),
            academicEmail: `${form.studentID}@isep.ipp.pt`,
            phoneCountryCode: form.countryCode,
            phoneNumber: form.phoneNumber,
            nifCountryCode: countryList().getData().find(c => c.label === form.nifcountry)?.value || '',
            addressCountryCode: countryList().getData().find(c => c.label === form.addressCountry)?.value || ''
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
                        <img src={ISEPLogoBranco} alt="Logo do ISEP" />
                    </div>
                </div>

                <form className="form" onSubmit={handleSubmit}>
                    <h1>Register Student</h1>

                    <div className="form-and-buttons-main-div">
                        <div className="form-div">

                            <div className="form-group">
                                <label className="form-label" htmlFor="name">Name</label>
                                <input
                                    className="form-input"
                                    placeholder="Enter Student's Name"
                                    id="name"
                                    name="name"
                                    type="text"
                                    value={form.name}
                                    onChange={handleChange}
                                    required
                                    style={{ width: '330px' }}
                                />
                            </div>

                            <div className="form-group">
                                <label className="form-label" htmlFor="nif">NIF</label>
                                <input
                                    className="form-input"
                                    placeholder="Enter Student's NIF"
                                    id="nif"
                                    name="nif"
                                    type="text"
                                    inputMode="numeric"
                                    pattern="\d*"
                                    maxLength="9"
                                    value={form.nif}
                                    onChange={handleChange}
                                    required
                                    style={{ width: '330px' }}
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
                                    placeholder="Select NIF Country"
                                    isSearchable
                                    menuPlacement="auto"
                                    menuPosition="fixed"
                                    styles={{
                                        control: (base, state) => ({
                                            ...base,
                                            width: '330px',
                                            height: '40px',
                                            border: '1px solid #ccc',
                                            borderRadius: '4px',
                                            padding: '0 8px',
                                            fontSize: '1rem',
                                            fontFamily: '-apple-system, BlinkMacSystemFont, "Segoe UI", "Roboto", "Oxygen", "Ubuntu", "Cantarell", "Fira Sans", "Droid Sans", "Helvetica Neue", sans-serif',
                                            color: '#000',
                                            boxShadow: state.isFocused ? '0 0 0 1px #ccc' : 'none',
                                            '&:hover': { border: '1px solid #999' }
                                        }),
                                        placeholder: (base) => ({
                                            ...base,
                                            fontSize: '1rem',
                                            fontFamily: '-apple-system, BlinkMacSystemFont, "Segoe UI", "Roboto", "Oxygen", "Ubuntu", "Cantarell", "Fira Sans", "Droid Sans", "Helvetica Neue", sans-serif',
                                        }),
                                        input: (base) => ({
                                            ...base,
                                            fontSize: '1rem',
                                            fontFamily: '-apple-system, BlinkMacSystemFont, "Segoe UI", "Roboto", "Oxygen", "Ubuntu", "Cantarell", "Fira Sans", "Droid Sans", "Helvetica Neue", sans-serif',
                                        }),
                                        singleValue: (base) => ({
                                            ...base,
                                            fontSize: '1rem',
                                            fontFamily: '-apple-system, BlinkMacSystemFont, "Segoe UI", "Roboto", "Oxygen", "Ubuntu", "Cantarell", "Fira Sans", "Droid Sans", "Helvetica Neue", sans-serif',
                                            color: '#000',
                                        })
                                    }}
                                />
                            </div>

                            <div className="form-group">
                                <label className="form-label" htmlFor="street">Street</label>
                                <input
                                    className="form-input"
                                    placeholder="Enter Student's Address"
                                    id="street"
                                    name="street"
                                    type="text"
                                    value={form.street}
                                    onChange={handleChange}
                                    required
                                    style={{ width: '330px' }}
                                />
                            </div>

                            <div className="form-group postal-code-group">
                                <label className="form-label" htmlFor="postalCode">Código Postal</label>
                                <input
                                    id="postalCode"
                                    name="postalCode"
                                    type="text"
                                    value={form.postalCode}
                                    onChange={handleChange}
                                    placeholder="Enter the postal code"
                                    required
                                    className="form-input"
                                    style={{width: '330px'}}
                                />
                                </div>

                            <div className="form-group">
                                <label className="form-label" htmlFor="location">Location</label>
                                <input
                                    className="form-input"
                                    placeholder="Enter Student's City"
                                    id="location"
                                    name="location"
                                    type="text"
                                    value={form.location}
                                    onChange={handleChange}
                                    required
                                    style={{ width: '330px' }}
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
                                    placeholder="Select Address Country"
                                    isSearchable
                                    menuPlacement="auto"
                                    menuPosition="fixed"
                                    styles={{
                                        control: (base, state) => ({
                                            ...base,
                                            width: '330px',
                                            height: '40px',
                                            border: '1px solid #ccc',
                                            borderRadius: '4px',
                                            padding: '0 8px',
                                            fontSize: '1rem',
                                            fontFamily: '-apple-system, BlinkMacSystemFont, "Segoe UI", "Roboto", "Oxygen", "Ubuntu", "Cantarell", "Fira Sans", "Droid Sans", "Helvetica Neue", sans-serif',
                                            color: '#000',
                                            boxShadow: state.isFocused ? '0 0 0 1px #ccc' : 'none',
                                            '&:hover': { border: '1px solid #999' }
                                        }),
                                        placeholder: (base) => ({
                                            ...base,
                                            fontSize: '1rem',
                                            fontFamily: '-apple-system, BlinkMacSystemFont, "Segoe UI", "Roboto", "Oxygen", "Ubuntu", "Cantarell", "Fira Sans", "Droid Sans", "Helvetica Neue", sans-serif',
                                        }),
                                        input: (base) => ({
                                            ...base,
                                            fontSize: '1rem',
                                            fontFamily: '-apple-system, BlinkMacSystemFont, "Segoe UI", "Roboto", "Oxygen", "Ubuntu", "Cantarell", "Fira Sans", "Droid Sans", "Helvetica Neue", sans-serif',
                                        }),
                                        singleValue: (base) => ({
                                            ...base,
                                            fontSize: '1rem',
                                            fontFamily: '-apple-system, BlinkMacSystemFont, "Segoe UI", "Roboto", "Oxygen", "Ubuntu", "Cantarell", "Fira Sans", "Droid Sans", "Helvetica Neue", sans-serif',
                                            color: '#000',
                                        })
                                    }}
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
                                    inputStyle={{ width: '330px' }}
                                />
                            </div>

                            <div className="form-group">
                                <label className="form-label" htmlFor="email">E-mail</label>
                                <input
                                    className="form-input"
                                    placeholder="Enter Student's E-mail"
                                    id="email"
                                    name="email"
                                    type="email"
                                    value={form.email}
                                    onChange={handleChange}
                                    required
                                    style={{ width: '330px' }}
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
