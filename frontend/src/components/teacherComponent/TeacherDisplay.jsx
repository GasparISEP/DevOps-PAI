import React, { useEffect, useState } from 'react';
import { getAllTeachers } from '../../services/teacherService';
import '../../styles/DisplayTeacherPage.css';
import '../../styles/Buttons.css';
import { Link } from 'react-router-dom';

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
    const [sortConfig, setSortConfig] = useState({ key: null, direction: 'asc' });

    function handleSort(key) {
        setSortConfig(prev => {
            if (prev.key === key) {
                // Toggle direction
                return { key, direction: prev.direction === 'asc' ? 'desc' : 'asc' };
            } else {
                return { key, direction: 'asc' };
            }
        });
    }

    function getSortedTeachers() {
        if (!sortConfig.key) return teachers;
        const sorted = [...teachers].sort((a, b) => {
            let aValue = a[sortConfig.key];
            let bValue = b[sortConfig.key];
            if (typeof aValue === 'string') aValue = aValue.toLowerCase();
            if (typeof bValue === 'string') bValue = bValue.toLowerCase();
            if (aValue < bValue) return sortConfig.direction === 'asc' ? -1 : 1;
            if (aValue > bValue) return sortConfig.direction === 'asc' ? 1 : -1;
            return 0;
        });
        return sorted;
    }

    const sortedTeachers = getSortedTeachers();
    const teachersToShow = sortedTeachers.slice(startIndex, endIndex);

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

    if (loading) return (
        <div style={{
            display: 'flex',
            justifyContent: 'center',
            alignItems: 'center',
            height: '60vh',
            width: '100%'
        }}>
            <div className="loader"></div>
        </div>
    );
    if (error) return <div>{error}</div>;

    function PaginationButton({ onClick, disabled, children }) {
        return (
            <button className="pagination-btn pagination-btn-primary" onClick={onClick} disabled={disabled}>
                {children}
            </button>
        );
    }

    function PerPageButton({ value, selected, onClick }) {
        return (
            <button
                className={`pagination-btn pagination-btn-primary per-page-btn${selected ? ' selected' : ''}`}
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
                    <div style={{ display: 'flex', justifyContent: 'flex-start', marginBottom: '1rem' }}>
                        <Link to="/" className="btn btn-primary" style={{ minWidth: 'unset', padding: '0.5rem 1.5rem', fontSize: '1rem' }}>
                            Back to Main Page
                        </Link>
                    </div>
                    <div className="teacher-table-center-wrapper">
                        <table className="teacher-form-table">
                            <thead>
                                <tr>
                                    <th className={`sortable${sortConfig.key === 'name' ? ' selected' : ''}`} onClick={() => handleSort('name')}>
                                        Name {sortConfig.key === 'name' ? (sortConfig.direction === 'asc' ? '▲' : '▼') : ''}
                                    </th>
                                    <th className={`sortable${sortConfig.key === 'id' ? ' selected' : ''}`} onClick={() => handleSort('id')}>
                                        Acronym {sortConfig.key === 'id' ? (sortConfig.direction === 'asc' ? '▲' : '▼') : ''}
                                    </th>
                                    <th className={`sortable${sortConfig.key === 'email' ? ' selected' : ''}`} onClick={() => handleSort('email')}>
                                        Email {sortConfig.key === 'email' ? (sortConfig.direction === 'asc' ? '▲' : '▼') : ''}
                                    </th>
                                    <th className={`sortable${sortConfig.key === 'nif' ? ' selected' : ''}`} onClick={() => handleSort('nif')}>
                                        NIF {sortConfig.key === 'nif' ? (sortConfig.direction === 'asc' ? '▲' : '▼') : ''}
                                    </th>
                                    <th className={`sortable${sortConfig.key === 'academicBackground' ? ' selected' : ''}`} onClick={() => handleSort('academicBackground')}>
                                        Academic Background {sortConfig.key === 'academicBackground' ? (sortConfig.direction === 'asc' ? '▲' : '▼') : ''}
                                    </th>
                                    <th className={`sortable${sortConfig.key === 'street' ? ' selected' : ''}`} onClick={() => handleSort('street')}>
                                        Street {sortConfig.key === 'street' ? (sortConfig.direction === 'asc' ? '▲' : '▼') : ''}
                                    </th>
                                    <th className={`sortable${sortConfig.key === 'postalCode' ? ' selected' : ''}`} onClick={() => handleSort('postalCode')}>
                                        Postal Code {sortConfig.key === 'postalCode' ? (sortConfig.direction === 'asc' ? '▲' : '▼') : ''}
                                    </th>
                                    <th className={`sortable${sortConfig.key === 'location' ? ' selected' : ''}`} onClick={() => handleSort('location')}>
                                        Location {sortConfig.key === 'location' ? (sortConfig.direction === 'asc' ? '▲' : '▼') : ''}
                                    </th>
                                    <th className={`sortable${sortConfig.key === 'country' ? ' selected' : ''}`} onClick={() => handleSort('country')}>
                                        Country {sortConfig.key === 'country' ? (sortConfig.direction === 'asc' ? '▲' : '▼') : ''}
                                    </th>
                                    <th className={`sortable${sortConfig.key === 'departmentID' ? ' selected' : ''}`} onClick={() => handleSort('departmentID')}>
                                        Department {sortConfig.key === 'departmentID' ? (sortConfig.direction === 'asc' ? '▲' : '▼') : ''}
                                    </th>
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

