import React, { useEffect, useState } from 'react';
import { getAllTeachers } from '../../services/teacherService';
import '../../styles/TeacherDisplay.css';
import '../../styles/Buttons.css';

export default function TeacherDisplay() {
    const [teachers, setTeachers] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState('');
    const [currentPage, setCurrentPage] = useState(1);
    const [teachersPerPage, setTeachersPerPage] = useState(20);
    const teachersPerPageOptions = [5, 10, 20, 50];
    const totalPages = Math.ceil(teachers.length / teachersPerPage);
    const startIndex = (currentPage - 1) * teachersPerPage;
    const endIndex = startIndex + teachersPerPage;
    const teachersToShow = teachers.slice(startIndex, endIndex);

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

    useEffect(() => {
        setCurrentPage(1); // Reset to first page when teachersPerPage changes
    }, [teachersPerPage]);

    if (loading) return <div>Loading teachers...</div>;
    if (error) return <div>{error}</div>;

    function PaginationButton({ onClick, disabled, children }) {
        return (
            <button className="pagination-btn" onClick={onClick} disabled={disabled}>
                {children}
            </button>
        );
    }

    function PerPageButton({ value, selected, onClick }) {
        return (
            <button
                className={`pagination-btn per-page-btn${selected ? ' selected' : ''}`}
                style={{
                    background: selected ? '#9a1a24' : '#fff',
                    color: selected ? '#fff' : '#9a1a24',
                    border: '1px solid #9a1a24',
                    margin: '0 0.25rem',
                    fontWeight: selected ? 700 : 500
                }}
                onClick={onClick}
            >
                {value}
            </button>
        );
    }

    return (
        <div className="teacher-main-component-div">
            <div className="teacher-main-grid">
                <div className="teacher-form teacher-display-table-wrapper">
                    <h1>Teachers</h1>
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
                            {teachersToShow.map(teacher => (
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
                                    <td>{teacher.departmentID}</td>
                                </tr>
                            ))}
                        </tbody>
                    </table>
                    <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', marginTop: '1rem', gap: '1rem' }}>
                        <div style={{ display: 'flex', alignItems: 'center', gap: '2rem' }}>
                            <PaginationButton onClick={() => setCurrentPage(p => Math.max(p - 1, 1))} disabled={currentPage === 1}>
                                Previous
                            </PaginationButton>
                            <span style={{ minWidth: '120px', textAlign: 'center', display: 'inline-block' }}>Page {currentPage} of {totalPages}</span>
                            <PaginationButton onClick={() => setCurrentPage(p => Math.min(p + 1, totalPages))} disabled={currentPage === totalPages}>
                                Next
                            </PaginationButton>
                        </div>
                        <div style={{ display: 'flex', alignItems: 'center', gap: '0.5rem' }}>
                            <span style={{ fontWeight: 600, color: '#9a1a24', marginRight: '0.5rem' }}>Show:</span>
                            {teachersPerPageOptions.map(opt => (
                                <PerPageButton
                                    key={opt}
                                    value={opt}
                                    selected={teachersPerPage === opt}
                                    onClick={() => setTeachersPerPage(opt)}
                                />
                            ))}
                            <span style={{ color: '#333', marginLeft: '0.5rem' }}>per page</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}

