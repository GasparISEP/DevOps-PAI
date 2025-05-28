import React, {useEffect, useState} from 'react';
import { registerTeacher } from '../../services/teacherService';
import PhoneInput from 'react-phone-input-2';
import 'react-phone-input-2/lib/style.css';
import ISEPLogoBranco from "../../assets/images/ISEP_logo-branco.png";
import '../../styles/Form.css'
import Select from "react-select";
import countryList from "react-select-country-list";
import CountryFlag from "react-country-flag";
import {Link} from "react-router-dom";

export default function TeacherForm() {
    // Store initial form state for reset
    const initialFormState = {
        id: '',
        name: '',
        email: '',
        nif: '',
        academicBackground: '',
        countryCode: '',
        phoneNumber: '',
        street: '',
        postalCode: '',
        location: '',
        country: '',
        departmentID: ''
    };
    const [form, setForm] = useState(initialFormState);

    const [departments, setDepartments] = useState([]);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState('');
    const [success, setSuccess] = useState(null);

    useEffect(() => {
        async function fetchOptions() {
            try {
                const [deptRes] = await Promise.all([
                    fetch(`${process.env.REACT_APP_API_URL}/departments`),
                ]);
                const deptData = await deptRes.json();

                console.log("Fetched departments:", deptData);

                setDepartments(deptData);

            } catch (err) {
                console.error("Failed to load options:", err);
            }
        }

        fetchOptions();
    }, []);

    function handleChange(e) {
        setForm(f => ({...f, [e.target.name]: e.target.value}));
    }

    useEffect(() => {
        console.log("API URL:", process.env.REACT_APP_API_URL);
    }, []);

    // Add a clearForm function
    function clearForm() {
        setForm(initialFormState);
        setError('');
        setSuccess(null);
    }

    async function handleSubmit(e) {
        e.preventDefault();
        setError('');
        setSuccess(null);
        setLoading(true);

        const payload = {
            ...form,
        };

        try {
            const response = await registerTeacher(payload);
            setSuccess(response);
        } catch (err) {
            setError(err.message);
        } finally {
            setLoading(false);
        }
    }

    return (

        <div className="form-main-component-div">
            <div className="form-main-grid">

                <div className="form-img-main-div teacher-img-background">
                    <div className="form-logo-img-div">
                        <img src={ISEPLogoBranco} alt="Logo do ISEP"/>
                    </div>
                </div>

                <form className="form" onSubmit={handleSubmit}>

                    <div style={{ display: 'flex', alignItems: 'center', justifyContent: 'space-between', marginBottom: '2rem' }}>
                        <h1 style={{ margin: 0 }}>Register Teacher</h1>
                        <Link to="/" className="pagination-btn2 pagination-btn-secondary" style={{ textDecoration: 'none' }}>
                            Back to Main Page
                        </Link>
                    </div>

                    <div className="form-and-buttons-main-div">

                        <div className="form-div">

                            <div className="form-group">
                                <label className="form-label" htmlFor="name">Name</label>
                                <input className="form-input" placeholder="Enter Teacher's name" id="name" name="name"
                                       value={form.name} onChange={handleChange} required/>
                            </div>

                            <div className="form-group">
                                <label className="form-label" htmlFor="acronym">Acronym</label>
                                <input
                                    className="form-input" placeholder="Enter Teacher's acronym" id="acronym"
                                    name="acronym" value={form.acronym}
                                    onChange={e => {
                                        // Only allow 3 uppercase letters
                                        let value = e.target.value.toUpperCase().replace(/[^A-Z]/g, '').slice(0, 3);
                                        setForm(f => ({...f, acronym: value, id: value}));
                                    }}
                                    maxLength={3}
                                    pattern="[A-Z]{3}"
                                    required
                                />
                            </div>

                            <div className="form-group">
                                <label className="form-label" htmlFor="email">Email</label>
                                <input
                                    className="form-input" placeholder="Enter Teacher's Email" id="email" name="email"
                                    value={form.email}
                                    onChange={handleChange}
                                    required
                                    pattern="[^@\s]+@[^@\s]+\.[^@\s]+"
                                    title="Please enter a valid email address."
                                />
                            </div>

                            <div className="form-group">
                                <label className="form-label" htmlFor="nif">NIF</label>
                                <input className="form-input" placeholder="Enter Teacher's NIF" id="nif" name="nif"
                                       type="number"
                                       value={form.nif} onChange={handleChange} required/>
                            </div>

                            <div className="form-group">
                                <label className="form-label" htmlFor="academicBackground">Academic Background</label>
                                <input className="form-input" placeholder="Enter Teacher's Academic Background"
                                       id="academicBackground" name="academicBackground"
                                       value={form.academicBackground} onChange={handleChange} required/>
                            </div>

                            <div className="form-group">
                                <label className="form-label" htmlFor="street">Street</label>
                                <input className="form-input" placeholder="Enter Teacher's Street" id="street"
                                       name="street"
                                       value={form.street || ''} onChange={handleChange} required/>
                            </div>
                            <div className="form-group">
                                <label className="form-label" htmlFor="postalCode">Postal Code</label>
                                <input className="form-input" placeholder="Enter Teacher's Postal Code" id="postalCode"
                                       name="postalCode"
                                       value={form.postalCode || ''} onChange={handleChange} required/>
                            </div>
                            <div className="form-group">
                                <label className="form-label" htmlFor="location">Location</label>
                                <input className="form-input" placeholder="Enter Teacher's Location" id="location"
                                       name="location"
                                       value={form.location || ''} onChange={handleChange} required/>
                            </div>

                            <div className="form-group">
                                <label className="form-label" htmlFor="addressCountry">Country</label>
                                <Select
                                    id="country"
                                    name="country"
                                    className="teacher-form-input"
                                    classNamePrefix="teacher-form-input"
                                    options={countryList().getData()}
                                    value={countryList().getData().find(option => option.label === form.country)}
                                    onChange={option => setForm(f => ({ ...f, country: option?.label ?? '' }))}
                                    formatOptionLabel={option => (
                                        <div style={{ display: 'flex', alignItems: 'center', gap: 10 }}>
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
                                            width: '100%',
                                            minHeight: '40px',
                                            border: '1px solid #ccc',
                                            borderRadius: '4px',
                                            padding: 0, // Remove padding
                                            fontSize: '1rem',
                                            fontFamily: '-apple-system, BlinkMacSystemFont, "Segoe UI", "Roboto", "Oxygen", "Ubuntu", "Cantarell", "Fira Sans", "Droid Sans", "Helvetica Neue", sans-serif',
                                            color: '#000',
                                            boxShadow: state.isFocused ? '0 0 0 1px #ccc' : 'none',
                                            '&:hover': { border: '1px solid #999' }
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
                                <label className="form-label" htmlFor="phone">Phone Number</label>
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
                                <label className="form-label" htmlFor="departmentID">Department</label>
                                <select className="form-input" id="departmentID" name="departmentID"
                                        value={form.departmentID} onChange={handleChange} required>
                                    <option value="">Select Department</option>
                                    {departments.map(d => (
                                        <option key={d.id} value={d.id}>{d.name}</option>
                                    ))}
                                </select>
                            </div>

                            {error && <div className="error">{error}</div>}

                            <div className="form-actions">
                                <button type="button" className="btn btn-secondary"
                                        onClick={clearForm}
                                        disabled={loading}>
                                    CLEAR
                                </button>
                                <button type="submit" className="btn btn-primary" disabled={loading}>
                                    {loading ? 'Registering…' : 'REGISTER'}
                                </button>
                            </div>
                        </div>

                        {success && (
                            <div className="success" style={{marginTop: '1rem', color: '#080'}}>
                                Teacher registered successfully!
                            </div>
                        )}
                    </div>
                </form>
            </div>
        </div>
    );
}

