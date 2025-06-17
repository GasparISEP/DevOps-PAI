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
import { fetchDepartments } from '../../services/departmentService';
import TeacherSuccessModal from './TeacherSuccessModal';
import TeacherErrorModal from './TeacherErrorModal';

export default function TeacherForm() {
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
    const [showModal, setShowModal] = useState(false);
    const [showErrorModal, setShowErrorModal] = useState(false);

    useEffect(() => {
        async function loadDepartments() {
            try {
                const deptData = await fetchDepartments();
                setDepartments(deptData._embedded?.departmentWithDirectorDTOList || []);
            } catch (err) {
                console.error("Failed to load departments:", err);
            }
        }
        loadDepartments();
    }, []);

    function handleChange(e) {
        const { name, value } = e.target;
        setForm(f => ({ ...f, [name]: value }));

    }

    useEffect(() => {
        console.log("API URL:", process.env.REACT_APP_API_URL);
    }, []);

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

        try {
            const payload = {
                ...form,
                postalCode: form.postalCode,
            };
            const response = await registerTeacher(payload);
            setSuccess(response);
            setShowModal(true);
            setForm({ ...initialFormState });
        } catch (err) {
            setError(err.message);
            setShowErrorModal(true);
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
                            Back to Home Page
                        </Link>
                    </div>

                    <div className="form-and-buttons-main-div">

                        <div className="form-div">

                            <div className="form-group">
                                <label className="form-label" htmlFor="name">Name</label>
                                <input
                                    className="form-input" placeholder="Enter Teacher's name" id="name" name="name"
                                    value={form.name}
                                    onChange={e => {
                                        const value = e.target.value.replace(/[^a-zA-ZçÇáàâãäéèêëíìîïóòôõöúùûüñÑ\s]/g, '');
                                        setForm(f => ({ ...f, name: value }));
                                    }}
                                    required
                                />
                            </div>

                            <div className="form-group">
                                <label className="form-label" htmlFor="acronym">Acronym</label>
                                <input
                                    className="form-input" placeholder="Enter Teacher's acronym" id="acronym" name="acronym"
                                    value={form.acronym}
                                    onChange={e => {
                                        // Only allow 3 uppercase letters
                                        let value = e.target.value.toUpperCase().replace(/[^A-Z]/g, '').slice(0, 3);
                                        setForm(f => ({...f, acronym: value, id: value}));
                                    }}
                                    maxLength={3} pattern="[A-Z]{3}" required
                                />
                            </div>

                            <div className="form-group">
                                <label className="form-label" htmlFor="email">Email</label>
                                <input
                                    className="form-input" placeholder="Enter Teacher's Email" id="email" name="email"
                                    value={form.email} onChange={handleChange}
                                    pattern="[^@\s]+@[^@\s]+\.[^@\s]+" title="Please enter a valid email address." required
                                />
                            </div>

                            <div className="form-group">
                                <label className="form-label" htmlFor="nif">NIF</label>
                                <input className="form-input" placeholder="Enter Teacher's NIF" id="nif" name="nif" type="number"
                                       value={form.nif} onChange={handleChange} required/>
                            </div>

                            <div className="form-group">
                                <label className="form-label" htmlFor="academicBackground">Academic Background</label>
                                <input className="form-input" placeholder="Enter Teacher's Academic Background" id="academicBackground" name="academicBackground"
                                       value={form.academicBackground} onChange={handleChange} required/>
                            </div>

                            <div className="form-group">
                                <label className="form-label" htmlFor="street">Street</label>
                                <input className="form-input" placeholder="Enter Teacher's Street" id="street" name="street"
                                       value={form.street || ''} onChange={handleChange} required/>
                            </div>

                            <div className="form-group">
                                <label className="form-label" htmlFor="postalCode">Postal Code</label>
                                <input
                                    className="form-input" placeholder="Enter Postal Code" id="postalCode" name="postalCode"
                                    value={form.postalCode || ''} onChange={handleChange} required/>
                            </div>

                            <div className="form-group">
                                <label className="form-label" htmlFor="location">Location</label>
                                <input className="form-input" placeholder="Enter Teacher's Location" id="location" name="location"
                                       value={form.location || ''} onChange={handleChange} required/>
                            </div>

                            <div className="form-group">
                                <label className="form-label" htmlFor="country">Country</label>
                                <Select
                                    classNamePrefix="teacher-form-select" placeholder="Select Country" id="country" name="country"
                                    menuPlacement="auto" menuPosition="fixed"
                                    options={countryList().getData()}
                                    value={countryList().getData().find(option => option.label === form.country)}
                                    onChange={option => setForm(f => ({ ...f, country: option?.label ?? '' }))}
                                    formatOptionLabel={option => (
                                        <div style={{ display: 'flex', alignItems: 'center', gap: 16 }}>
                                            <CountryFlag countryCode={option.value} svg style={{ width: '1.9em', height: '1.9em' }} />
                                            <span style={{ fontSize: '1.5rem', fontWeight: 400 }}>{option.label}</span>
                                        </div>
                                    )}
                                    required isSearchable
                                    styles={{
                                        control: (base, state) => ({
                                            ...base,
                                            width: '55.4rem',
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
                                <label className="form-label" htmlFor="phoneNumber">Phone Number</label>
                                <div className="teacher-phone-row">
                                    <div className="teacher-phone-country">
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
                                            containerClass="teacher-phone-row"
                                            buttonClass="teacher-phone-country"
                                            inputClass="teacher-phone-number teacher-form-input"
                                            dropdownStyle={{ zIndex: 9999 }}
                                            enableSearch
                                            fontSize={16}
                                            placeholder="Enter phone number" // <-- set your desired placeholder here
                                            searchClass="teacher-form-input"
                                            required
                                            inputStyle={{ width: '55.4rem'}}
                                        />
                                    </div>
                                </div>
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
                <TeacherSuccessModal
                    success={success}
                    departments={departments}
                    onClose={() => setShowModal(false)}
                />
            )}
            {showErrorModal && (
                <TeacherErrorModal
                    error={error}
                    onClose={() => setShowErrorModal(false)}
                />
            )}
        </div>
    );
}
