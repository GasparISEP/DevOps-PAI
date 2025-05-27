import React, {useEffect, useState, useRef} from 'react';
import { registerTeacher } from '../../services/teacherService';
import formImage from '../../assets/images/form-image.jpg';
import PhoneInput from 'react-phone-input-2';
import 'react-phone-input-2/lib/style.css';
import Select from 'react-select';
import CountryFlag from 'react-country-flag';
import countryList from 'react-select-country-list';

export default function TeacherForm() {
    const [form, setForm] = useState({
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
    });

    const [departments, setDepartments] = useState([]);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState('');
    const [success, setSuccess] = useState(null);

    useEffect(() => {
        async function fetchOptions() {
            try {
                const [deptRes, teacherRes, degreeTypeRes] = await Promise.all([
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
        setForm(f => ({ ...f, [e.target.name]: e.target.value }));
    }

    useEffect(() => {
        console.log("API URL:", process.env.REACT_APP_API_URL);
    }, []);


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

        <div className="teacher-main-component-div">
            <div className="teacher-main-grid">

                <div className="img-main-div">
                    <img className="form-img" src={formImage} alt="Person typing on a computer."/>
                </div>

                <form className="teacher-form" onSubmit={handleSubmit}>
                    <h1>Register Teacher</h1>

                    <div className="teacher-form-and-buttons-main-div">

                        <div className="teacher-form-div">

                            <div className="teacher-form-group">
                                <label className="teacher-form-label" htmlFor="name">Name</label>
                                <input className="teacher-form-input" placeholder="Enter Teacher's name" id="name" name="name"
                                       value={form.name} onChange={handleChange} required/>
                            </div>

                            <div className="teacher-form-group">
                                <label className="teacher-form-label" htmlFor="acronym">Acronym</label>
                                <input
                                    className="teacher-form-input" placeholder="Enter Teacher's acronym" id="acronym" name="acronym" value={form.acronym}
                                    onChange={e => {
                                        // Only allow 3 uppercase letters
                                        let value = e.target.value.toUpperCase().replace(/[^A-Z]/g, '').slice(0, 3);
                                        setForm(f => ({ ...f, acronym: value, id: value }));
                                    }}
                                    maxLength={3}
                                    pattern="[A-Z]{3}"
                                    required
                                />
                            </div>

                            <div className="teacher-form-group">
                                <label className="teacher-form-label" htmlFor="email">Email</label>
                                <input
                                    className="teacher-form-input" placeholder="Enter Teacher's Email" id="email" name="email" value={form.email}
                                    onChange={handleChange}
                                    required
                                    pattern="[^@\s]+@[^@\s]+\.[^@\s]+"
                                    title="Please enter a valid email address."
                                />
                            </div>

                            <div className="teacher-form-group">
                                <label className="teacher-form-label" htmlFor="nif">NIF</label>
                                <input className="teacher-form-input" placeholder="Enter Teacher's NIF" id="nif" name="nif" type="number"
                                       value={form.nif} onChange={handleChange} required/>
                            </div>

                            <div className="teacher-form-group">
                                <label className="teacher-form-label" htmlFor="academicBackground">Academic Background</label>
                                <input className="teacher-form-input" placeholder="Enter Teacher's Academic Background" id="academicBackground" name="academicBackground"
                                       value={form.academicBackground} onChange={handleChange} required/>
                            </div>

                            <div className="teacher-form-group">
                                <label className="teacher-form-label" htmlFor="street">Street</label>
                                <input className="teacher-form-input" placeholder="Enter Teacher's Street" id="street" name="street"
                                       value={form.street || ''} onChange={handleChange} required/>
                            </div>
                            <div className="teacher-form-group">
                                <label className="teacher-form-label" htmlFor="postalCode">Postal Code</label>
                                <input className="teacher-form-input" placeholder="Enter Teacher's Postal Code" id="postalCode" name="postalCode"
                                       value={form.postalCode || ''} onChange={handleChange} required/>
                            </div>
                            <div className="teacher-form-group">
                                <label className="teacher-form-label" htmlFor="location">Location</label>
                                <input className="teacher-form-input" placeholder="Enter Teacher's Location" id="location" name="location"
                                       value={form.location || ''} onChange={handleChange} required/>
                            </div>

                            <div className="teacher-form-group">
                                <label className="teacher-form-label" htmlFor="location">Country</label>
                                <input className="teacher-form-input" placeholder="Enter Teacher's Country" id="country" name="country"
                                       value={form.country || ''} onChange={handleChange} required/>
                            </div>

                            {/*<div className="teacher-form-group">*/}
                            {/*    <label className="teacher-form-label" htmlFor="country">Country</label>*/}
                            {/*    <div style={{ width: '100%' }}>*/}
                            {/*        <Select*/}
                            {/*            id="country"*/}
                            {/*            name="country"*/}
                            {/*            classNamePrefix="teacher-form-select"*/}
                            {/*            options={countryList().getData()}*/}
                            {/*            value={countryList().getData().find(option => option.label === form.country)}*/}
                            {/*            onChange={option => {*/}
                            {/*                if (option && option.label) {*/}
                            {/*                    const name = option.label.charAt(0).toUpperCase() + option.label.slice(1);*/}
                            {/*                    setForm(f => ({ ...f, country: name }));*/}
                            {/*                } else {*/}
                            {/*                    setForm(f => ({ ...f, country: '' }));*/}
                            {/*                }*/}
                            {/*            }}*/}
                            {/*            formatOptionLabel={option => (*/}
                            {/*                <div style={{ display: 'flex', alignItems: 'center', gap: 8 }}>*/}
                            {/*                    <CountryFlag countryCode={option.value} svg style={{ width: '1.5em', height: '1.5em' }} />*/}
                            {/*                    <span>{option.label}</span>*/}
                            {/*                </div>*/}
                            {/*            )}*/}
                            {/*            placeholder="Select Country"*/}
                            {/*            isSearchable*/}
                            {/*            menuPlacement="auto"*/}
                            {/*            menuPosition="fixed"*/}
                            {/*        />*/}
                            {/*    </div>*/}
                            {/*</div>*/}

                            <div className="teacher-form-group">
                                <label className="teacher-form-label" htmlFor="phoneNumber">Phone Number</label>
                                <div className="teacher-phone-row">
                                    <PhoneInput
                                        country={'pt'}
                                        value={form.countryCode + form.phoneNumber}
                                        onChange={(value, data) => {
                                            const newCountryCode = '+' + data.dialCode;
                                            let phone = value.replace(data.dialCode, '').replace(/^\+/, '');
                                            let newPhoneNumber = phone;
                                            if (form.countryCode !== newCountryCode) {
                                                newPhoneNumber = '';
                                            }
                                            setForm(f => ({
                                                ...f,
                                                countryCode: newCountryCode,
                                                phoneNumber: newPhoneNumber
                                            }));
                                        }}
                                        containerClass="teacher-phone-row"
                                        buttonClass="teacher-phone-country"
                                        inputClass="teacher-phone-number teacher-form-input"
                                        dropdownStyle={{ zIndex: 9999 }}
                                        enableSearch
                                        searchClass="teacher-form-input"
                                        required
                                    />
                                </div>
                            </div>

                            <div className="teacher-form-group">
                                <label className="teacher-form-label" htmlFor="departmentID">Department</label>
                                <select className="teacher-form-input" id="departmentID" name="departmentID"
                                        value={form.departmentID} onChange={handleChange} required>
                                    <option value="">Select Department</option>
                                    {departments.map(d => (
                                        <option key={d.id} value={d.id}>{d.name}</option>
                                    ))}
                                </select>
                            </div>

                            {error && <div className="error">{error}</div>}

                            <div className="programme-form-actions">
                                <button type="button" className="btn btn-secondary" onClick={() => window.history.back()}
                                        disabled={loading}>
                                    CANCEL
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

