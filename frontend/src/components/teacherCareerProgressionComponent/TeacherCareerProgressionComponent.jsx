import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';

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

    if (loading) return <p>Loading data...</p>;
    if (error) return <p>Error: {error}</p>;
    if (!progression) return <p>No data found.</p>;

    return (
        <div>
            <h2>Teacher Career Progression Details</h2>
            <p><strong>Progression ID:</strong> {progression.teacherCareerProgressionId}</p>
            <p><strong>Date:</strong> {progression.date}</p>
            <p><strong>Teacher ID:</strong> {progression.teacherID}</p>
            <p><strong>Category ID:</strong> {progression.teacherCategoryID}</p>
            <p><strong>Working Percentage:</strong> {progression.workingPercentage}%</p>
        </div>
    );
};

export default TeacherCareerProgressionComponent;