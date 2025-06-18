import React, { useEffect, useState } from 'react';
import { useParams, Link } from 'react-router-dom';
import '../../styles/DisplayATeacherCareerProgression.css';
import NavBar from '../NavBar';
import Footer from '../Footer';

export default function ProgrammeEnrolmentDetails() {
    const { id } = useParams(); // UUID do programmeEnrolment
    const [data, setData] = useState(null);
    const [error, setError] = useState('');
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        async function fetchDetails() {
            try {
                const response = await fetch(`http://localhost:8081/students/enrollStudent/${id}`);
                if (!response.ok) throw new Error('Failed to fetch enrolment details');
                const json = await response.json();
                setData(json);
            } catch (err) {
                setError(err.message);
            } finally {
                setLoading(false);
            }
        }
        fetchDetails();
    }, [id]);

    if (loading) return <p>Loading...</p>;
    if (error) return <p style={{ color: 'red' }}>{error}</p>;

    return (
        <div className="component-page-wrapper">
            <NavBar />

            <div className="component-main-content">
                <div className="tcp-container">
                    <Link to="/" className="back-to-home-link">Back to Home Page</Link>
                    <h2 className="tcp-title">Programme Enrolment Details</h2>
                    <table className="tcp-table">
                        <tbody>
                            <tr className="tcp-row">
                                <th className="tcp-label">Student ID</th>
                                <td className="tcp-value">{data.studentId}</td>
                            </tr>
                            <tr className="tcp-row">
                                <th className="tcp-label">Student Name</th>
                                <td className="tcp-value">{data.studentName}</td>
                            </tr>
                            <tr className="tcp-row">
                                <th className="tcp-label">Programme Name</th>
                                <td className="tcp-value">{data.programmeName}</td>
                            </tr>
                            <tr className="tcp-row">
                                <th className="tcp-label">Enrolment Date</th>
                                <td className="tcp-value">{data.date}</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>

            <div className="component-footer-wrapper">
                <Footer />
            </div>
        </div>
    );
}