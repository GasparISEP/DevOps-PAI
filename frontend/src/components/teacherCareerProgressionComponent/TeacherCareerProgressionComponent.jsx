import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import '../../styles/DisplayATeacherCareerProgression.css';

const TeacherCareerProgressionComponent = () => {
    const { id } = useParams();
    const [progression, setProgression] = useState(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        fetch(`http://localhost:8081/teacher-career-progressions/${id}`)
            .then((response) => {
                if (!response.ok) {
                    throw new Error('Failed to fetch teacher career progression');
                }
                return response.json();
            })
            .then((data) => {
                setProgression(data);
                setLoading(false);
            })
            .catch((error) => {
                console.error('Error fetching data:', error);
                setError(error.message);
                setLoading(false);
            });
    }, [id]);

    if (loading) return <p className="loading">Loading data...</p>;
    if (error) return <p className="error">Error: {error}</p>;
    if (!progression) return <p className="no-data">No data found.</p>;

    return (
        <div className="tcp-container">
            <h2 className="tcp-title">Teacher Career Progression Details</h2>
            <table className="tcp-table">
                <tbody>
                <tr className="tcp-row">
                    <th className="tcp-label">Progression ID</th>
                    <td className="tcp-value">{progression.teacherCareerProgressionId}</td>
                </tr>
                <tr className="tcp-row">
                    <th className="tcp-label">Date</th>
                    <td className="tcp-value">{progression.date}</td>
                </tr>
                <tr className="tcp-row">
                    <th className="tcp-label">Teacher ID</th>
                    <td className="tcp-value">{progression.teacherID}</td>
                </tr>
                <tr className="tcp-row">
                    <th className="tcp-label">Category ID</th>
                    <td className="tcp-value">{progression.teacherCategoryID}</td>
                </tr>
                <tr className="tcp-row">
                    <th className="tcp-label">Working Percentage</th>
                    <td className="tcp-value">{progression.workingPercentage}%</td>
                </tr>
                </tbody>
            </table>
        </div>
    );
};

export default TeacherCareerProgressionComponent;

