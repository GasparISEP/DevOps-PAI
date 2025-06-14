import React, {useEffect, useState} from 'react';
import {Link} from 'react-router-dom';
import {defineRucInCourseEdition} from '../../services/defineRucInCourseEditionService';
import ISEPLogoBranco from '../../assets/images/ISEP_logo-branco.png';
import '../../styles/Form.css';
import '../../styles/NavBar.css'
import '../../styles/Footer.css'

import CourseEditionSelector from "./CourseEditionSelector";
import TeacherSelector from "./TeacherSelector";
import SchoolYearSelector from "./SchoolYearSelector";
import {getAllTeachers} from "../../services/teacherService";
import {findAllCourseEditions} from "../../services/courseEditionGradeStudentService";
import {getAllSchoolYears} from "../../services/defineRucInCourseEditionService";
import NavBar from "../NavBar";
import Footer from "../Footer";

export default function DefineRucInCourseEditionForm() {
    const [form, setForm] = useState({
        courseEditionId: '', rucId: ''
    });

    const [courseEditions, setCourseEditions] = useState([]);
    const [teachers, setTeachers] = useState([]);
    const [schoolYears, setSchoolYears] = useState([]);
    const [loading, setLoading] = useState(false);
    const [success, setSuccess] = useState(null);
    const [error, setError] = useState('');

    useEffect(() => {
        async function fetchData() {

            //uses function in ... to getAllSchoolYears
            const schoolYears = await getAllSchoolYears(); // Example school years
            setSchoolYears([]);

            //uses function in courseEditionGradeStudentService to getAllCourseEditions
            const courseEditionsData = await findAllCourseEditions();
            setCourseEditions([])

            //uses function in TeacherService to getAllTeachers
            const teachersData = await getAllTeachers();
            setTeachers([]);
        }

        fetchData();
    }, []);

    function handleChange(event) {
        const {name, value} = event.target;
        setForm(prevForm => ({
            ...prevForm, [name]: value
        }));
    }

    function handleClear() {
        setForm({
            courseEditionId: '', teacherID: ''
        });
        setSuccess(null);
        setError('');
    }

    async function handleSubmit(event) {
        event.preventDefault();
        setError('');
        setSuccess(null);
        setLoading(true);
        try {
            await defineRucInCourseEdition({
                courseEditionId: form.courseEditionId, teacherId: form.teacherId
            });
            setSuccess("RUC defined successfully in the course edition.");
        } catch (error) {
            setError("An error occurred while defining the RUC in the course edition.");
            console.error(error);
        } finally {
            setLoading(false);
        }
    }

    return (<>
            <NavBar/>
            <div className="form-main-component-div">
                <div className="form-main-grid">
                    <div className="form-img-main-div teacher-img-background">
                        <div className="form-logo-img-div">
                            <img src={ISEPLogoBranco} alt="ISEP Logo"/>
                        </div>
                    </div>

                    <form className="form" onSubmit={handleSubmit}>
                        <div style={{
                            display: 'flex', alignItems: 'center', justifyContent: 'space-between', marginBottom: '2rem'
                        }}>
                            <h1 style={{margin: 0}}>Define a Ruc</h1>
                            <Link to="/" className="pagination-btn2 pagination-btn-secondary"
                                  style={{textDecoration: 'none'}}>
                                Back to Home Page
                            </Link>
                        </div>
                        <div className="form-and-buttons-main-div">
                            <div className="form-div">
                                <SchoolYearSelector
                                    schoolYears={courseEditions.map(ce => ce.schoolYear)}
                                    value={form.schoolYear}
                                    onChange={handleChange}
                                    name="schoolYear"
                                />
                                <CourseEditionSelector
                                    courseEditions={courseEditions}
                                    value={form.courseEdition}
                                    onChange={handleChange}
                                    name="courseEditionId"
                                />
                                <TeacherSelector
                                    teachers={teachers}
                                    value={form.teacher}
                                    onChange={handleChange}
                                    name="teacherID"/>
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
            <Footer/>
        </>
    );
}
