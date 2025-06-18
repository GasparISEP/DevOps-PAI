import React, { useState, useEffect } from 'react';
import { useSearchParams, Link } from 'react-router-dom';
import NavBar from '../../components/NavBar';
import Footer from '../../components/Footer';
import StudentEnrolledCourses from '../../components/studentComponent/StudentEnrolledCourses';
import '../../styles/DisplayPage.css';

export default function StudentEnrolledCoursesPage() {
    const [params] = useSearchParams();
    const link = params.get('link');

    const [courses, setCourses] = useState([]);
    const [error, setError] = useState('');

    useEffect(() => {
        async function fetchCourses() {
            try {
                const res = await fetch(link);
                const data = await res.json();
                setCourses(data);
            } catch (err) {
                console.error(err);
                setError('Failed to load enrolled courses.');
            }
        }
        if (link) {
            fetchCourses();
        } else {
            setError('Missing HATEOAS link!');
        }
    }, [link]);

    return (
        <div className="component-page-wrapper">
            <NavBar />

            <div className="component-main-content">
                <div className="tcp-container">
                    <Link to="/" className="back-to-home-link">Back to Home Page</Link>
                    <h2 className="tcp-title">Student Enrolled Courses</h2>
                    {error && <p className="error">{error}</p>}
                    <StudentEnrolledCourses courses={courses} />
                </div>
            </div>

            <div className="component-footer-wrapper">
                <Footer />
            </div>
        </div>
    );
}