import React, { useState, useEffect } from 'react';
import { registerStudent } from '../../services/studentService';
import Select from 'react-select';
import CountryFlag from 'react-country-flag';
import countryList from 'react-select-country-list';
import PhoneInput from 'react-phone-input-2';
import 'react-phone-input-2/lib/style.css';
import ISEPLogoBranco from "../../assets/images/ISEP_logo-branco.png";
import '../../styles/Form.css';
import {Link} from "react-router-dom";

const initialFormState = {
    studentID: '',
    name: '',
    nif: '',
    nifcountry: null,
    street: '',
    postalCode: '',
    location: '',
    addressCountry: null,
    countryCode: '+351',
    phoneNumber: '',
    email: ''
};


export default function StudentForm() {
    const [form, setForm] = useState(initialFormState);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState('');
    const [success, setSuccess] = useState(null);
    const [showModal, setShowModal] = useState(false);
    const [showErrorModal, setShowErrorModal] = useState(false);

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
                setShowErrorModal(true);
            }
        }

        fetchLastStudentID();
    }, []);

    function handleChange(e) {
        const { name, value } = e.target;
        let newValue = value;

        if (['studentID', 'postalCodePart1', 'postalCodePart2'].includes(name)) {
            newValue = value.replace(/[^0-9]/g, '');
        } else if (name === 'nif') {
            newValue = value.toUpperCase().replace(/[^A-Z0-9]/g, '').substring(0, 18);
        } else if (['name', 'location', 'street'].includes(name)) {
            newValue = value.replace(/(^\w{1})|(\s+\w{1})/g, letra => letra.toUpperCase());
        }

        setForm(f => ({ ...f, [name]: newValue }));
    }

    function clearForm() {
        setForm(initialFormState);
        setError('');
        setSuccess(null);
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
            setShowModal(true);
        } catch (err) {
            setError(err.message || 'An unexpected error occurred.');
            setShowErrorModal(true);
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

                    <div style={{
                        display: 'flex',
                        alignItems: 'center',
                        justifyContent: 'space-between',
                        marginBottom: '2rem'
                    }}>
                        <h1 style={{margin: 0}}>Register Student</h1>
                        <Link to="/" className="pagination-btn2 pagination-btn-secondary"
                              style={{textDecoration: 'none'}}>
                            Back to Home Page
                        </Link>
                    </div>

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
                                    style={{ width: '554px' }}
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
                                    maxLength="18"
                                    value={form.nif}
                                    onChange={handleChange}
                                    required
                                    style={{ width: '554px' }}
                                />
                            </div>

                            <div className="form-group">
                                <label className="form-label" htmlFor="nifcountry">NIF Country</label>
                                <Select
                                    id="nifcountry"
                                    name="nifcountry"
                                    classNamePrefix="student-form-select"
                                    options={countryList().getData()}
                                    value={form.nifcountry}
                                    onChange={option => setForm(f => ({ ...f, nifcountry: option }))}
                                    formatOptionLabel={option => (
                                        <div style={{ display: 'flex', alignItems: 'center', gap: 16 }}>
                                            <CountryFlag countryCode={option.value} svg style={{ width: '1.9em', height: '1.9em' }} />
                                            <span style={{ fontSize: '1.5rem', fontWeight: 400 }}>{option.label}</span>
                                        </div>
                                    )}
                                    placeholder="Select Country"
                                    isSearchable
                                    menuPlacement="auto"
                                    menuPosition="fixed"
                                    styles={{
                                        control: (base, state) => ({
                                            ...base,
                                            width: '554px',
                                            height: '4rem',
                                            border: '1px solid #ccc',
                                            borderRadius: '4px',
                                            padding: 0,
                                            fontSize: '1.5rem',
                                            fontFamily: '-apple-system, BlinkMacSystemFont, "Segoe UI", "Roboto", "Oxygen", "Ubuntu", "Cantarell", "Fira Sans", "Droid Sans", "Helvetica Neue", sans-serif',
                                            color: '#000',
                                            boxShadow: state.isFocused ? '0 0 0 1px #ccc' : 'none',
                                            '&:hover': { border: '1px solid #999' }
                                        }),
                                        option: (base) => ({
                                            ...base,
                                            fontSize: '1.5rem',
                                            minHeight: '40px',
                                            display: 'flex',
                                            alignItems: 'center',
                                        }),
                                        placeholder: (base) => ({
                                            ...base,
                                            fontSize: '1.5rem',
                                            fontFamily: '-apple-system, BlinkMacSystemFont, "Segoe UI", "Roboto", "Oxygen", "Ubuntu", "Cantarell", "Fira Sans", "Droid Sans", "Helvetica Neue", sans-serif',
                                        }),
                                        input: (base) => ({
                                            ...base,
                                            fontSize: '1.5rem',
                                            fontFamily: '-apple-system, BlinkMacSystemFont, "Segoe UI", "Roboto", "Oxygen", "Ubuntu", "Cantarell", "Fira Sans", "Droid Sans", "Helvetica Neue", sans-serif',
                                        }),
                                        singleValue: (base) => ({
                                            ...base,
                                            fontSize: '1.5rem',
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
                                    style={{ width: '554px' }}
                                />
                            </div>

                            <div className="form-group postal-code-group">
                                <label className="form-label" htmlFor="postalCode">Postal Code</label>
                                <input
                                    id="postalCode"
                                    name="postalCode"
                                    type="text"
                                    value={form.postalCode}
                                    onChange={handleChange}
                                    placeholder="Enter Student's Postal Code"
                                    required
                                    className="form-input"
                                    style={{width: '554px'}}
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
                                    style={{ width: '554px' }}
                                />
                            </div>

                            <div className="form-group">
                                <label className="form-label" htmlFor="addressCountry">Address Country</label>
                                <Select
                                    id="addressCountry"
                                    name="addressCountry"
                                    classNamePrefix="student-form-select"
                                    options={countryList().getData()}
                                    value={form.addressCountry}
                                    onChange={option => setForm(f => ({ ...f, addressCountry: option }))}
                                    formatOptionLabel={option => (
                                        <div style={{ display: 'flex', alignItems: 'center', gap: 16 }}>
                                            <CountryFlag countryCode={option.value} svg style={{ width: '1.9em', height: '1.9em' }} />
                                            <span style={{ fontSize: '1.5rem', fontWeight: 400 }}>{option.label}</span>
                                        </div>
                                    )}
                                    placeholder="Select Country"
                                    isSearchable
                                    menuPlacement="auto"
                                    menuPosition="fixed"
                                    styles={{
                                        control: (base, state) => ({
                                            ...base,
                                            width: '554px',
                                            height: '4rem',
                                            border: '1px solid #ccc',
                                            borderRadius: '4px',
                                            padding: 0,
                                            fontSize: '1.5rem',
                                            fontFamily: '-apple-system, BlinkMacSystemFont, "Segoe UI", "Roboto", "Oxygen", "Ubuntu", "Cantarell", "Fira Sans", "Droid Sans", "Helvetica Neue", sans-serif',
                                            color: '#000',
                                            boxShadow: state.isFocused ? '0 0 0 1px #ccc' : 'none',
                                            '&:hover': { border: '1px solid #999' }
                                        }),
                                        option: (base) => ({
                                            ...base,
                                            fontSize: '1.5rem',
                                            minHeight: '40px',
                                            display: 'flex',
                                            alignItems: 'center',
                                        }),
                                        placeholder: (base) => ({
                                            ...base,
                                            fontSize: '1.5rem',
                                            fontFamily: '-apple-system, BlinkMacSystemFont, "Segoe UI", "Roboto", "Oxygen", "Ubuntu", "Cantarell", "Fira Sans", "Droid Sans", "Helvetica Neue", sans-serif',
                                        }),
                                        input: (base) => ({
                                            ...base,
                                            fontSize: '1.5rem',
                                            fontFamily: '-apple-system, BlinkMacSystemFont, "Segoe UI", "Roboto", "Oxygen", "Ubuntu", "Cantarell", "Fira Sans", "Droid Sans", "Helvetica Neue", sans-serif',
                                        }),
                                        singleValue: (base) => ({
                                            ...base,
                                            fontSize: '1.5rem',
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
                                        let phone = value.replace(new RegExp(`^\\+?${data.dialCode}`), '').trim();

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
                                    inputStyle={{ width: '554px', height: '4rem'}}
                                    inputProps={{
                                        onKeyDown: (e) => {
                                            const phone = form.phoneNumber;
                                            const isPortugal = form.countryCode === '+351';

                                            if (isPortugal && phone.length >= 9 && !['Backspace', 'Delete', 'ArrowLeft', 'ArrowRight', 'Tab'].includes(e.key)) {
                                                e.preventDefault();
                                            }
                                        }
                                    }}
                                />
                            </div>

                            <div className="form-group">
                                <label className="form-label" htmlFor="email">Email</label>
                                <input
                                    className="form-input"
                                    placeholder="Enter Student's Email"
                                    id="email"
                                    name="email"
                                    type="email"
                                    value={form.email}
                                    onChange={handleChange}
                                    required
                                    style={{ width: '554px' }}
                                />
                            </div>


                            <div className="form-actions">
                                <button type="button" className="btn btn-secondary"
                                        onClick={clearForm}
                                        disabled={loading}>
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
                <div className="modal-overlay">
                    <div className="modal-content">
                        <h2>Success!</h2>
                        <p>The student was registered successfully.</p>
                        {success && (
                                <div className="success" style={{ marginTop: '1rem', color: '#080' }}>
                                    <p><strong>Name:</strong> {success.name}</p>
                                    <p><strong>NIF:</strong> {success.nif}</p>
                                    <p><strong>NIF Country:</strong> {success.nifcountry}</p>
                                    <p><strong>Street:</strong> {success.street}</p>
                                    <p><strong>Postal Code:</strong> {success.postalCode}</p>
                                    <p><strong>Location:</strong> {success.location}</p>
                                    <p><strong>Address Country:</strong> {success.addressCountry}</p>
                                    <p><strong>Phone Number:</strong> {success.phoneNumber}</p>
                                    <p><strong>Email:</strong> {success.email}</p>
                                </div>
                        )}
                        <button className="modal-btn" onClick={() => {
                            setShowModal(false);
                            window.location.reload();  // Força o reload da página
                        }}>Close</button>
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
