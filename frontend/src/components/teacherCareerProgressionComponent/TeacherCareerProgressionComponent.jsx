import React from 'react';
import { useEffect, useState } from 'react';
import { useParams, Link } from 'react-router-dom'; // Importa o Link
import '../../styles/DisplayATeacherCareerProgression.css';
import NavBar from '../NavBar';
import Footer from '../Footer';

const TeacherCareerProgressionComponent = () => {
    const { id } = useParams();
    const [progression, setProgression] = useState(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        // A sua lógica de fetch continua igual
        fetch(`http://localhost:8081/teacher-career-progressions/${id}`)
            .then(response => response.ok ? response.json() : Promise.reject('Failed to fetch'))
            .then(data => {
                setProgression(data);
                setLoading(false);
            })
            .catch(error => {
                console.error('Error fetching data:', error);
                setError(error.toString());
                setLoading(false);
            });
    }, [id]);

    if (loading) return <p className="loading">Loading data...</p>;
    if (error) return <p className="error">Error: {error}</p>;
    if (!progression) return <p className="no-data">No data found.</p>;

    return (
        <div className="component-page-wrapper">
            <NavBar />

            <div className="component-main-content">
                <div className="tcp-container">

                    {}
                    <Link to="/" className="back-to-home-link">
                        Back to Home Page
                    </Link>
                    <h2 className="tcp-title">Teacher Career Progression Details</h2>
                    <table className="tcp-table">
                        <tbody>
                        <tr className="tcp-row"><th className="tcp-label">Progression ID</th><td className="tcp-value">{progression.teacherCareerProgressionId}</td></tr>
                        <tr className="tcp-row"><th className="tcp-label">Date</th><td className="tcp-value">{new Date(progression.date).toLocaleDateString()}</td></tr>
                        <tr className="tcp-row"><th className="tcp-label">Teacher ID</th><td className="tcp-value">{progression.teacherID}</td></tr>
                        <tr className="tcp-row"><th className="tcp-label">Category ID</th><td className="tcp-value">{progression.teacherCategoryID}</td></tr>
                        <tr className="tcp-row"><th className="tcp-label">Working Percentage</th><td className="tcp-value">{progression.workingPercentage}%</td></tr>
                        </tbody>
                    </table>
                </div>
            </div>

            <div className="component-footer-wrapper">
                <Footer />
            </div>
        </div>
    );
};

export default TeacherCareerProgressionComponent;