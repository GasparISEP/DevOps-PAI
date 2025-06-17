import React, { useEffect, useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';

import { updateTeacherCategory } from '../../services/updateTeacherCategoryService';
import ISEPLogoBranco from '../../assets/images/ISEP_logo-branco.png';
import '../../styles/Form.css';
import {format, parse} from 'date-fns';

import TeacherSelect from './TeacherSelect';
import CategorySelect from './CategorySelect';
import DateInput from './DateInput';
import SuccessModal from './SuccessModal';
import ErrorModal from './ErrorModal';

const initialFormState = {
    teacher: '',
    teacherCategory: '',
    date: ''
};

export default function UpdateTeacherCategoryForm() {

    const [form, setForm] = useState(initialFormState);
    const [teachers, setTeachers] = useState([]);
    const [teacherCategories, setTeacherCategories] = useState([]);
    const [hasSubmitted, setHasSubmitted] = useState(false);

    const [loading, setLoading] = useState(false);
    const [showModal, setShowModal] = useState(false);
    const [showErrorModal, setShowErrorModal] = useState(false);
    const [formErrors, setFormErrors] = useState({});
    const [successData, setSuccessData] = useState(null);
    const [errorMessage, setErrorMessage] = useState('');

    useEffect(() => {
        async function fetchOptions() {
            try {
                const [teacherRes, categoryRes] = await Promise.all([
                    fetch(`${process.env.REACT_APP_API_URL}/teachers`),
                    fetch(`${process.env.REACT_APP_API_URL}/teacher-categories`)
                ]);
                const teacherData = await teacherRes.json();
                const categoryData = await categoryRes.json();

                setTeachers(teacherData._embedded?.teacherDTOList || []);
                setTeacherCategories(categoryData || []);
            } catch (err) {
                console.error("Failed to load options:", err);
            }
        }

        fetchOptions();
    }, []);

    function getErrorMessage(field) {
        const messages = {
            date: " Insert the date."
        };
        return messages[field];
    }

    function handleChange(e) {
        const { name, value } = e.target;

        setForm(prev => ({ ...prev, [name]: value }));

        if (hasSubmitted) {
            setFormErrors(prevErrors => {
                const newErrors = { ...prevErrors };
                if (value) {
                    delete newErrors[name];
                } else {
                    newErrors[name] = getErrorMessage(name);
                }
                return newErrors;
            });
        }
    }

    function handleDateChange(dateStr) {
        setForm(prev => ({ ...prev, date: dateStr }));

        if (hasSubmitted) {
            setFormErrors(prevErrors => {
                const newErrors = { ...prevErrors };
                if (dateStr) {
                    delete newErrors.date;
                } else {
                    newErrors.date = getErrorMessage("date");
                }
                return newErrors;
            });
        }
    }


    function clearForm() {
        setForm(initialFormState);
        setFormErrors({});
        setHasSubmitted(false);
    }

    async function handleSubmit(e) {
        e.preventDefault();
        setHasSubmitted(true);
        setErrorMessage('');
        setSuccessData(null);

        const fields = ["teacher", "teacherCategory", "date"];
        const errors = {};
        fields.forEach((field) => {
            if (!form[field]) {
                errors[field] = getErrorMessage(field);
            }
        });

        if (Object.keys(errors).length > 0) {
            setFormErrors(errors);
            return;
        }
        setLoading(true);

        try {
            const dateObj = parse(form.date, 'dd-MM-yyyy', new Date());
            const isoDate = format(dateObj, 'yyyy-MM-dd');
            const payload = {
                teacherId: form.teacher,
                teacherCategoryID: form.teacherCategory,
                date: isoDate
            };
            const { data } = await updateTeacherCategory(payload);
            setSuccessData(data);
            setShowModal(true);
        } catch (err) {
            const msg = err?.response?.data?.message || err.message || "Unexpected error.";
            setErrorMessage(msg);
            setShowErrorModal(true);
        } finally {
            setLoading(false);
        }
    }

    return (
        <div className="form-main-component-div">
            <div className="form-main-grid">
                <div className="form-img-main-div teacher-category-img-background">
                    <div className="form-logo-img-div">
                        <img src={ISEPLogoBranco} alt="Logo do ISEP" />
                    </div>
                </div>

                <form className="form" onSubmit={handleSubmit} autoComplete="off">
                    <div style={{ display: 'flex', alignItems: 'center', justifyContent: 'space-between', marginBottom: '2rem' }}>
                        <h1 style={{ margin: 0 }}>Update Teacher Category</h1>
                        <Link to="/" className="pagination-btn2 pagination-btn-secondary" style={{ textDecoration: 'none' }}>
                            Back to Home Page
                        </Link>
                    </div>

                    <div className="form-and-buttons-main-div">
                        <div className="form-div">
                            <TeacherSelect
                                value={form.teacher}
                                onChange={handleChange}
                                options={teachers}
                                error={hasSubmitted ? formErrors.teacher : null}
                            />

                            <CategorySelect
                                value={form.teacherCategory}
                                onChange={handleChange}
                                options={teacherCategories}
                                error={hasSubmitted ? formErrors.teacherCategory : null}
                            />

                            <DateInput
                                value={form.date}
                                onChange={handleDateChange}
                                error={hasSubmitted ? formErrors.date : null}
                            />

                            {errorMessage && !showErrorModal && <div className="error">{errorMessage}</div>}

                            <div className="form-actions">
                                <button type="button" className="btn btn-secondary" onClick={clearForm}>CLEAR</button>
                                <button type="submit" className="btn btn-primary" disabled={loading}>
                                    {loading ? 'UPDATING…' : 'UPDATE'}
                                </button>
                            </div>
                        </div>
                    </div>
                </form>

                <SuccessModal
                    data={successData}
                    form={form}
                    onClose={() => {
                        setShowModal(false);
                        setSuccessData(null);
                        clearForm();
                    }}
                />

                <ErrorModal
                    message={errorMessage}
                    onClose={() => {
                        setShowErrorModal(false);
                        setErrorMessage('');
                    }}
                />
            </div>
        </div>
    );
}
