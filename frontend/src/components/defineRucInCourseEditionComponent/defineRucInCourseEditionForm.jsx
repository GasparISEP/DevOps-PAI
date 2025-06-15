import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import { defineRucInCourseEdition, getAllSchoolYears, findAllCourseEditions } from '../../services/DefineRucInCourseEditionService';
import ISEPLogoBranco from '../../assets/images/ISEP_logo-branco.png';
import '../../styles/Form.css';
import '../../styles/NavBar.css';
import '../../styles/Footer.css';

import CourseEditionSelector from "./CourseEditionSelector";
import TeacherSelector from "./TeacherSelector";
import SchoolYearSelector from "./SchoolYearSelector";
import { getAllTeachers } from "../../services/teacherService";
import NavBar from "../NavBar";
import Footer from "../Footer";

export default function DefineRucInCourseEditionForm() {
    const [form, setForm] = useState({
        schoolYear: '',
        courseEditionId: '',
        teacherId: ''
    });

    const [allCourseEditions, setAllCourseEditions] = useState([]);
    const [filteredCourseEditions, setFilteredCourseEditions] = useState([]);
    const [teachers, setTeachers] = useState([]);
    const [schoolYears, setSchoolYears] = useState([]);
    const [loading, setLoading] = useState(false);
    const [success, setSuccess] = useState(null);
    const [error, setError] = useState('');

    console.log(allCourseEditions)
    console.log(form.schoolYear)

    useEffect(() => {
        async function fetchData() {
            const schoolYearsData = await getAllSchoolYears();
            console.log(schoolYearsData)
            setSchoolYears(schoolYearsData);

            const courseEditionsData = await findAllCourseEditions();
            console.log(courseEditionsData)
            setAllCourseEditions(courseEditionsData);

            const teachersData = await getAllTeachers();
            console.log(teachersData)
            setTeachers(teachersData);
        }
        fetchData();
    }, []);

    useEffect(() => {
        if (form.schoolYear) {

            const filtered = allCourseEditions.filter(
                ce => ce.schoolYearID === form.schoolYear
            )

            setFilteredCourseEditions(filtered);

            if (!filtered.find(ce => ce.courseEditionGeneratedID === form.courseEditionId)) {
                setForm(prev => ({ ...prev, courseEditionId: '' }));
            }
        } else {
            setFilteredCourseEditions([]);
            setForm(prev => ({ ...prev, courseEditionId: '' }));
        }
    }, [form.schoolYear, allCourseEditions]);

    function handleChange(event) {
        const { name, value } = event.target;

        setForm(prevForm => ({
            ...prevForm,
            [name]: value
        }));
    }

    function handleClear() {
        setForm({
            schoolYear: '',
            courseEditionId: '',
            teacherId: ''
        });
        setSuccess(null);
        setError('');
    }

    async function handleSubmit(event) {
        event.preventDefault();
        setError('');
        setSuccess(null);
        setLoading(true);
        console.log('Sending:', { id: form.courseEditionId, teacherId: form.teacherId });
        try {
            await defineRucInCourseEdition({
                id: form.courseEditionId,
                teacherId: form.teacherId

            });
            console.log('Sending:', { id: form.courseEditionId, teacherId: form.teacherId });
            setSuccess("RUC successfully defined in the course edition.");
        } catch (error) {
            setError("An error occurred when defining the RUC in the course edition.");
            console.error(error);
        } finally {
            setLoading(false);
        }
    }

    return (
        <>
            <NavBar />
            <div className="form-main-component-div">
                <div className="form-main-grid">
                    <div className="form-img-main-div teacher-img-background">
                        <div className="form-logo-img-div">
                            <img src={ISEPLogoBranco} alt="ISEP Logo" />
                        </div>
                    </div>
                    <form className="form" onSubmit={handleSubmit}>
                        <div style={{
                            display: 'flex', alignItems: 'center', justifyContent: 'space-between', marginBottom: '2rem'
                        }}>
                            <h1 style={{ margin: 0 }}>Define a Ruc</h1>
                            <Link to="/" className="pagination-btn2 pagination-btn-secondary"
                                  style={{ textDecoration: 'none' }}>
                                Back to Home Page
                            </Link>
                        </div>
                        <div className="form-and-buttons-main-div">
                            <div className="form-div">
                                <SchoolYearSelector
                                    schoolYears={schoolYears}
                                    value={form.schoolYear}
                                    onChange={handleChange}
                                    name="schoolYear"
                                />
                                <CourseEditionSelector
                                    courseEditions={filteredCourseEditions}
                                    value={form.courseEditionId}
                                    onChange={handleChange}
                                    name="courseEditionId"
                                />
                                <TeacherSelector
                                    teachers={teachers}
                                    value={form.teacherId}
                                    onChange={handleChange}
                                    name="teacherId"
                                />
                            </div>
                            <div className="form-actions">
                                <button
                                    type="button" className="btn btn-secondary" onClick={handleClear}>
                                    CLEAR
                                </button>
                                <button
                                    type="submit" className="btn btn-primary" disabled={loading}>
                                    {loading ? 'LOADING...' : 'REGISTER'}
                                </button>
                            </div>
                            {success && <div className="success-message">{success}</div>}
                            {error && <div className="error-message">{error}</div>}
                        </div>
                    </form>
                </div>
            </div>
            <Footer />
        </>
    );
}