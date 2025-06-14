import React, { useEffect, useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';

import { updateTeacherCategory } from '../../services/updateTeacherCategoryService';
import ISEPLogoBranco from '../../assets/images/ISEP_logo-branco.png';
import '../../styles/Form.css';

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
    const navigate = useNavigate();

    const [form, setForm] = useState(initialFormState);
    const [teachers, setTeachers] = useState([]);
    const [teacherCategories, setTeacherCategories] = useState([]);

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

    function handleChange(e) {
        const { name, value } = e.target;
        setForm(prev => ({ ...prev, [name]: value }));
    }

    function handleDateChange(dateStr) {
        setForm(prev => ({ ...prev, date: dateStr }));
    }

    function clearForm() {
        setForm(initialFormState);
        setFormErrors({});
    }

    async function handleSubmit(e) {
        e.preventDefault();
        setErrorMessage('');
        setSuccessData(null);

        const errors = {};
        if (!form.teacher) errors.teacher = "⚠️ Choose a Teacher.";
        if (!form.teacherCategory) errors.teacherCategory = "⚠️ Choose a Teacher Category.";
        if (!form.date) errors.date = "⚠️ Insert the date.";
        setFormErrors(errors);

        if (Object.keys(errors).length > 0) return;
        setLoading(true);

        try {
            const payload = {
                teacherId: form.teacher,
                teacherCategoryID: form.teacherCategory,
                date: form.date
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
                                error={formErrors.teacher}
                            />

                            <CategorySelect
                                value={form.teacherCategory}
                                onChange={handleChange}
                                options={teacherCategories}
                                error={formErrors.teacherCategory}
                            />

                            <DateInput
                                value={form.date}
                                onChange={handleDateChange}
                                error={formErrors.date}
                            />

                            {errorMessage && !showErrorModal && <div className="error">{errorMessage}</div>}

                            <div className="form-actions">
                                <button type="button" className="btn btn-secondary" onClick={clearForm}>CLEAR</button>
                                <button type="submit" className="btn btn-primary" disabled={loading}>
                                    {loading ? 'UPDATING…' : 'UPDATE'}
                                </button>
                            </div>
                        </div>

                        <SuccessModal
                            data={successData}
                            form={form}
                            onClose={() => {
                                setShowModal(false);
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
                </form>
            </div>
        </div>
    );
}
