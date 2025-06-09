import React, { useEffect, useState } from 'react';
import { getAllStudents } from '../../services/studentService';
import '../../styles/DisplayStudentPage.css';
import '../../styles/Buttons.css';
import { Link } from 'react-router-dom';

export default function StudentDisplay() {
    const [students, setStudents] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState('');
    const [currentPage, setCurrentPage] = useState(1);
    const [studentsPerPage, setStudentsPerPage] = useState(20);
    const studentsPerPageOptions = [5, 10, 20, 50];
    const totalPages = Math.ceil(students.length / studentsPerPage);
    const startIndex = (currentPage - 1) * studentsPerPage;
    const endIndex = startIndex + studentsPerPage;
    const [sortConfig, setSortConfig] = useState({ key: null, direction: 'asc' });
    const [filterField, setFilterField] = useState('studentID');
    const [filterValue, setFilterValue] = useState('');

    const placeholderMap = {
        studentID: 'Unique Number',
        name: 'Name',
        fullNIF: 'NIF',
        street: 'Street',
        postalCode: 'Postal Code',
        location: 'Location',
        addressCountry: 'Country',
        fullPhoneNumber: 'Phone Number',
        email: 'Email',
        academicEmail: 'Academic Email'
    };

    function handleSort(key) {
        setSortConfig(prev => {
            if (prev.key === key) {
                return { key, direction: prev.direction === 'asc' ? 'desc' : 'asc' };
            } else {
                return { key, direction: 'asc' };
            }
        });
    }

    function getSortedStudents() {
        if (!sortConfig.key || !Array.isArray(students)) return students;
        const sorted = [...students].sort((a, b) => {
            let aValue =
                sortConfig.key === 'fullPhoneNumber'
                    ? `${a.countryCode}${a.phoneNumber}`
                    : sortConfig.key === 'fullNIF'
                        ? `${a.nifCountry}${a.nif}`
                        : a[sortConfig.key];
            let bValue =
                sortConfig.key === 'fullPhoneNumber'
                    ? `${b.countryCode}${b.phoneNumber}`
                    : sortConfig.key === 'fullNIF'
                        ? `${b.nifCountry}${b.nif}`
                        : b[sortConfig.key];

            if (typeof aValue === 'string') aValue = aValue.toLowerCase();
            if (typeof bValue === 'string') bValue = bValue.toLowerCase();

            if (aValue < bValue) return sortConfig.direction === 'asc' ? -1 : 1;
            if (aValue > bValue) return sortConfig.direction === 'asc' ? 1 : -1;
            return 0;
        });
        return sorted;
    }

    const sortedStudents = getSortedStudents();
    const filteredStudents = sortedStudents.filter(student => {
        if (!filterValue) return true;

        const value =
            filterField === 'fullPhoneNumber'
                ? `${student.countryCode}${student.phoneNumber}`
                : filterField === 'fullNIF'
                    ? `${student.nifCountry}${student.nif}`
                    : student?.[filterField];

        if (typeof value === 'string') {
            return value.toLowerCase().includes(filterValue.toLowerCase());
        }
        if (typeof value === 'number') {
            return value.toString().includes(filterValue);
        }

        return false;
    });

    const studentsToShow = filteredStudents.slice(startIndex, endIndex);

    useEffect(() => {
        async function fetchStudents() {
            try {
                const data = await getAllStudents();
                setStudents(data);
            } catch (err) {
                setError('Failed to load students');
            } finally {
                setLoading(false);
            }
        }
        fetchStudents();
    }, []);

    useEffect(() => {
        setCurrentPage(1);
    }, [studentsPerPage]);

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
        <div className="student-main-component-div">
            <div className="student-main-grid student-main-grid-center">
                <div className="form student-display-table-wrapper">
                    <div className="student-table-container">
                        <div className="student-table-header-bar" style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', marginBottom: '1.5rem' }}>
                            <div style={{ display: 'flex', justifyContent: 'flex-start', alignItems: 'center', height: '100%' }}>
                                <Link to="/" className="pagination-btn2 pagination-btn-secondary" style={{ textDecoration: 'none', fontSize: '1.5rem', padding: '0rem 0rem', minHeight: '3.2rem', display: 'flex', alignItems: 'center' }}>
                                    Back to Main Page
                                </Link>
                            </div>
                            <h1 style={{ margin: 0 }}>Students</h1>
                            <div className="student-table-filter-bar" style={{ display: 'flex', alignItems: 'center', gap: '1rem', justifyContent: 'flex-end' }}>
                                <select
                                    value={filterField}
                                    onChange={e => setFilterField(e.target.value)}
                                    className="student-table-filter-select"
                                    style={{ fontSize: '1.4rem', padding: '0.5rem 1rem', borderRadius: '4px', border: '1px solid #ccc' }}
                                >
                                    {Object.entries(placeholderMap).map(([key, label]) => (
                                        <option key={key} value={key}>{label}</option>
                                    ))}
                                </select>
                                <input
                                    type="text"
                                    value={filterValue}
                                    onChange={e => setFilterValue(e.target.value)}
                                    placeholder={`Search by ${placeholderMap[filterField] || filterField}`}
                                    className="student-table-filter-input"
                                />
                            </div>
                        </div>
                        <div className="student-table-center-wrapper">
                            <table className="student-form-table">
                                <thead>
                                <tr>
                                    {Object.entries(placeholderMap).map(([key, label]) => (
                                        <th
                                            key={key}
                                            className={`sortable${sortConfig.key === key ? ' selected' : ''}`}
                                            onClick={() => handleSort(key)}
                                        >
                                            {label} {sortConfig.key === key ? (sortConfig.direction === 'asc' ? '▲' : '▼') : ''}
                                        </th>
                                    ))}
                                </tr>
                                </thead>
                                <tbody>
                                {error ? (
                                    <tr>
                                        <td colSpan="12" style={{ textAlign: 'center', color: '#111', fontWeight: 600, fontSize: '1.5rem' }}>
                                            No results found
                                        </td>
                                    </tr>
                                ) : studentsToShow.length === 0 ? (
                                    <tr>
                                        <td colSpan="12" style={{ textAlign: 'center', color: '#111', fontWeight: 600, fontSize: '1.5rem' }}>
                                            No results found
                                        </td>
                                    </tr>
                                ) : (
                                    studentsToShow.map(student => (
                                        <tr key={student.studentID}>
                                            <td>{student.studentID}</td>
                                            <td>{student.name}</td>
                                            <td>{`${student.nifCountry} ${student.nif}`}</td>
                                            <td>{student.street}</td>
                                            <td>{student.postalCode}</td>
                                            <td>{student.location}</td>
                                            <td>{student.addressCountry}</td>
                                            <td>{`${student.countryCode} ${student.phoneNumber}`}</td>
                                            <td>{student.email}</td>
                                            <td>{student.academicEmail}</td>
                                        </tr>
                                    ))
                                )}
                                </tbody>
                            </table>
                        </div>
                        <div className="student-pagination-bar">
                            <div className="student-pagination-controls student-pagination-left">
                                <PaginationButton onClick={() => setCurrentPage(p => Math.max(p - 1, 1))} disabled={currentPage === 1}>
                                    Previous
                                </PaginationButton>
                                <span className="pagination-page-info" style={{ minWidth: '120px', textAlign: 'center', display: 'inline-block' }}>Page {currentPage} of {totalPages}</span>
                                <PaginationButton onClick={() => setCurrentPage(p => Math.min(p + 1, totalPages))} disabled={currentPage === totalPages}>
                                    Next
                                </PaginationButton>
                            </div>
                            <div className="student-per-page-controls student-pagination-right">
                                <span className="student-per-page-label">Show:</span>
                                {studentsPerPageOptions.map(opt => (
                                    <PerPageButton
                                        key={opt}
                                        value={opt}
                                        selected={studentsPerPage === opt}
                                        onClick={() => setStudentsPerPage(opt)}
                                    />
                                ))}
                                <span className="students-per-page-label-end">per page</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}