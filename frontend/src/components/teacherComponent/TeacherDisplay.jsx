import React, { useEffect, useState } from 'react';
import { getAllTeachers } from '../../services/teacherService';
import '../../styles/TeacherDisplay.css';

export default function TeacherDisplay() {
    const [teachers, setTeachers] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState('');

    useEffect(() => {
        async function fetchTeachers() {
            try {
                const data = await getAllTeachers();
                setTeachers(data);
            } catch (err) {
                setError('Failed to load teachers');
            } finally {
                setLoading(false);
            }
        }
        fetchTeachers();
    }, []);

    if (loading) return <div>Loading teachers...</div>;
    if (error) return <div>{error}</div>;

    return (
        <div className="teacher-main-component-div">
            <div className="teacher-main-grid">
                <div className="teacher-form teacher-display-table-wrapper">
                    <h1>All Teachers</h1>
                    <table className="teacher-form-table">
                        <thead>
                            <tr>
                                <th>Name</th>
                                <th>Acronym</th>
                                <th>Email</th>
                                <th>NIF</th>
                                <th>Academic Background</th>
                                <th>Street</th>
                                <th>Postal Code</th>
                                <th>Location</th>
                                <th>Country</th>
                                <th>Department</th>
                            </tr>
                        </thead>
                        <tbody>
                            {teachers.map(teacher => (
                                <tr key={teacher.id}>
                                    <td>{teacher.name}</td>
                                    <td>{teacher.id}</td>
                                    <td>{teacher.email}</td>
                                    <td>{teacher.nif}</td>
                                    <td>{teacher.academicBackground}</td>
                                    <td>{teacher.street}</td>
                                    <td>{teacher.postalCode}</td>
                                    <td>{teacher.location}</td>
                                    <td>{teacher.country}</td>
                                    <td>{teacher.departmentName || teacher.departmentID}</td>
                                </tr>
                            ))}
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    );
}

