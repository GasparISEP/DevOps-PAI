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
                onClick={onClick}
            >
                {value}
            </button>
        );
    }

    return (
        <div className="teacher-main-component-div">
            <div className="teacher-main-grid teacher-main-grid-center">
                <div className="teacher-form teacher-display-table-wrapper">
                    <h1>Teachers</h1>
                    <div className="teacher-table-center-wrapper">
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
                    </div>
                    <div className="teacher-pagination-bar">
                        <div className="teacher-pagination-controls teacher-pagination-left">
                            <PaginationButton onClick={() => setCurrentPage(p => Math.max(p - 1, 1))} disabled={currentPage === 1}>
                                Previous
                            </PaginationButton>
                            <span style={{ minWidth: '120px', textAlign: 'center', display: 'inline-block' }}>Page {currentPage} of {totalPages}</span>
                            <PaginationButton onClick={() => setCurrentPage(p => Math.min(p + 1, totalPages))} disabled={currentPage === totalPages}>
                                Next
                            </PaginationButton>
                        </div>
                        <div className="teacher-per-page-controls teacher-pagination-right">
                            <span className="teacher-per-page-label">Show:</span>
                            {teachersPerPageOptions.map(opt => (
                                <PerPageButton
                                    key={opt}
                                    value={opt}
                                    selected={teachersPerPage === opt}
                                    onClick={() => setTeachersPerPage(opt)}
                                />
                            ))}
                            <span className="teacher-per-page-label-end">per page</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}

