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
import teacherImg1 from '../../assets/images/teacher-form-image3.jpg';
import teacherImg2 from '../../assets/images/teacher-form-image2.jpg';

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

    // Images for background rotation
    const images = [teacherImg1, teacherImg2];
    const [bgIndex, setBgIndex] = useState(0);
    const [nextIndex, setNextIndex] = useState(1);
    const [fade, setFade] = useState(false);

    const FADE_IN_DURATION = 2000;
    const VISIBLE_DURATION = 4000;
    const FADE_OUT_DURATION = 2000;

    useEffect(() => {
        let fadeTimeout;
        const interval = setInterval(() => {
            setFade(true);
            fadeTimeout = setTimeout(() => {
                setBgIndex(i => (i + 1) % images.length);
                setFade(false);
            }, 1000); // 1s fade duration
        }, 5000); // 5s per image (4s visible + 1s fade)
        return () => {
            clearInterval(interval);
            clearTimeout(fadeTimeout);
        };
    }, []);

    useEffect(() => {
        console.log('Image 1:', teacherImg1);
        console.log('Image 2:', teacherImg2);
    }, []);

    useEffect(() => {
        console.log('Current bgIndex:', bgIndex, 'Current image:', images[bgIndex]);
    }, [bgIndex]);

    useEffect(() => {
        async function loadDepartments() {
            try {
                const deptData = await fetchDepartments();
                setDepartments(deptData);
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

    const currentImageStyle = {
        position: 'absolute', top: 0, left: 0, width: '100%', height: '100%',
        backgroundImage: `linear-gradient(rgba(200, 26, 36, 0.5), rgba(228,7,7,0.6)), url(${images[bgIndex]})`,
        backgroundSize: 'cover', backgroundPosition: 'center', backgroundRepeat: 'no-repeat',
        opacity: fade ? 0 : 1,
        transition: `opacity ${fade ? FADE_OUT_DURATION : FADE_IN_DURATION}ms ease-in-out`,
        zIndex: 1
    };

    const nextImageStyle = {
        ...currentImageStyle,
        backgroundImage: `linear-gradient(rgba(200, 26, 36, 0.5), rgba(228,7,7,0.6)), url(${images[nextIndex]})`,
        opacity: fade ? 1 : 0,
        zIndex: 2
    };

    return (

        <div className="form-main-component-div">
            <div className="form-main-grid">

                <div className="form-img-main-div teacher-img-background" style={{position: 'relative', overflow: 'hidden'}}>
                    <div style={currentImageStyle} />
                    <div style={nextImageStyle} />
                    <div className="form-logo-img-div" style={{position: 'relative', zIndex: 3}}>
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
                                        // Only allow letters and spaces
                                        const value = e.target.value.replace(/[^a-zA-Z\s]/g, '');
                                        setForm(f => ({ ...f, name: value }));
                                    }}
                                    required
                                />
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
                                <input
                                    className="form-input"
                                    placeholder="Enter Postal Code"
                                    id="postalCode"
                                    name="postalCode"
                                    value={form.postalCode || ''}
                                    onChange={handleChange}
                                    required
                                    style={{ width: '100%' }}
                                />
                            </div>
                            <div className="form-group">
                                <label className="form-label" htmlFor="location">Location</label>
                                <input className="form-input" placeholder="Enter Teacher's Location" id="location"
                                       name="location"
                                       value={form.location || ''} onChange={handleChange} required/>
                            </div>

                            <div className="form-group">
                                <label className="form-label" htmlFor="country">Country</label>
                                <Select
                                    id="country"
                                    name="country"
                                    classNamePrefix="teacher-form-select"
                                    options={countryList().getData()}
                                    value={countryList().getData().find(option => option.label === form.country)}
                                    onChange={option => setForm(f => ({ ...f, country: option?.label ?? '' }))}
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

