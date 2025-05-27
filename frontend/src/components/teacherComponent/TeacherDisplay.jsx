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
    const [filterField, setFilterField] = useState('name');
    const [filterValue, setFilterValue] = useState('');

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
    const filteredTeachers = sortedTeachers.filter(teacher => {
        if (!filterValue) return true;
        const value = teacher[filterField];
        if (typeof value === 'string') {
            return value.toLowerCase().includes(filterValue.toLowerCase());
        }
        return false;
    });
    const teachersToShow = filteredTeachers.slice(startIndex, endIndex);

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
                disabled={selected}
            >
                {value}
            </button>
        );
    }

    return (
        <div className="teacher-main-component-div">
            <div className="teacher-main-grid teacher-main-grid-center">
                <div className="form teacher-display-table-wrapper">
                    <div className="teacher-table-header-bar" style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', marginBottom: '1.5rem' }}>
                        <div style={{ display: 'flex', justifyContent: 'flex-start' }}>
                            <Link to="/" className="pagination-btn pagination-btn-primary" >
                                Back to Main Page
                            </Link>
                        </div>
                        <h1 style={{ margin: 0 }}>Teachers</h1>
                        <div className="teacher-table-filter-bar" style={{ display: 'flex', alignItems: 'center', gap: '1rem', justifyContent: 'flex-end' }}>
                            <select
                                value={filterField}
                                onChange={e => setFilterField(e.target.value)}
                                className="teacher-table-filter-select"
                                style={{ fontSize: '1.4rem', padding: '0.5rem 1rem', borderRadius: '4px', border: '1px solid #ccc' }}
                            >
                                <option value="name">Name</option>
                                <option value="id">Acronym</option>
                                <option value="email">Email</option>
                                <option value="nif">NIF</option>
                                <option value="academicBackground">Academic Background</option>
                                <option value="street">Street</option>
                                <option value="postalCode">Postal Code</option>
                                <option value="location">Location</option>
                                <option value="country">Country</option>
                                <option value="departmentID">Department</option>
                            </select>
                            <input
                                type="text"
                                value={filterValue}
                                onChange={e => setFilterValue(e.target.value)}
                                placeholder={`Search by ${
                                    filterField === 'id' ? 'Acronym' : 
                                    filterField === 'academicBackground' ? 'Academic Background' : 
                                    filterField === 'postalCode' ? 'Postal Code' : 
                                    filterField === 'departmentID' ? 'Department' :
                                filterField.charAt(0).toUpperCase() + filterField.slice(1)}`}
                                className="teacher-table-filter-input"
                            />
                        </div>
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
                            <span className="pagination-page-info" style={{ minWidth: '120px', textAlign: 'center', display: 'inline-block' }}>Page {currentPage} of {totalPages}</span>
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

