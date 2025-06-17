import React, { useEffect, useState } from 'react';
import { useParams, Link } from 'react-router-dom';
import '../../styles/DisplayATeacherCareerProgression.css';
import NavBar from '../NavBar';
import Footer from '../Footer';

const StudentDetailsComponent = () => {
    const { id } = useParams();
    const [student, setStudent] = useState(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        fetch(`http://localhost:8081/students/${id}`)
            .then(response => response.ok ? response.json() : Promise.reject('Failed to fetch'))
            .then(data => {
                setStudent(data);
                setLoading(false);
            })
            .catch(error => {
                console.error('Error fetching student:', error);
                setError(error.toString());
                setLoading(false);
            });
    }, [id]);

    if (loading) return <p className="loading">Loading student data...</p>;
    if (error) return <p className="error">Error: {error}</p>;
    if (!student) return <p className="no-data">No student data found.</p>;

    return (
        <div className="component-page-wrapper">
            <NavBar />

            <div className="component-main-content">
                <div className="tcp-container">
                    <Link to="/" className="back-to-home-link">Back to Home Page</Link>
                    <h2 className="tcp-title">Student Details</h2>
                    <table className="tcp-table">
                        <tbody>
                        <tr className="tcp-row">
                            <th className="tcp-label">Student ID</th>
                            <td className="tcp-value">{student.studentID}</td>
                        </tr>
                        <tr className="tcp-row">
                            <th className="tcp-label">Name</th>
                            <td className="tcp-value">{student.name}</td>
                        </tr>
                        <tr className="tcp-row">
                            <th className="tcp-label">NIF</th>
                            <td className="tcp-value">{student.nif}</td>
                        </tr>
                        <tr className="tcp-row">
                            <th className="tcp-label">NIF Country</th>
                            <td className="tcp-value">{student.nifCountry}</td>
                        </tr>
                        <tr className="tcp-row">
                            <th className="tcp-label">Street</th>
                            <td className="tcp-value">{student.street}</td>
                        </tr>
                        <tr className="tcp-row">
                            <th className="tcp-label">Postal Code</th>
                            <td className="tcp-value">{student.postalCode}</td>
                        </tr>
                        <tr className="tcp-row">
                            <th className="tcp-label">Location</th>
                            <td className="tcp-value">{student.location}</td>
                        </tr>
                        <tr className="tcp-row">
                            <th className="tcp-label">Address Country</th>
                            <td className="tcp-value">{student.addressCountry}</td>
                        </tr>
                        <tr className="tcp-row">
                            <th className="tcp-label">Phone</th>
                            <td className="tcp-value">{student.countryCode} {student.phoneNumber}</td>
                        </tr>
                        <tr className="tcp-row">
                            <th className="tcp-label">Email</th>
                            <td className="tcp-value">{student.email}</td>
                        </tr>
                        <tr className="tcp-row">
                            <th className="tcp-label">Academic Email</th>
                            <td className="tcp-value">{student.academicEmail}</td>
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
};

export default StudentDetailsComponent;
