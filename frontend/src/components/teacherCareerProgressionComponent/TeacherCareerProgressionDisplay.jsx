import React, {useEffect, useState} from 'react';
import '../../styles/DisplayTeacherCareerProgressionPage.css';
import '../../styles/Buttons.css';
import {Link} from 'react-router-dom';

export default function TeacherCareerProgressionDisplay() {
    const [progressionOptions, setProgressionOptions] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState('');
    const [currentPage, setCurrentPage] = useState(1);
    const [careerProgressionsPerPage, setCareerProgressionsPerPage] = useState(20);
    const careerProgressionPerPageOptions = [5, 10, 20, 50];
    const [sortConfig, setSortConfig] = useState({key: null, direction: 'asc'});
    const [filterField, setFilterField] = useState('date');
    const [filterValue, setFilterValue] = useState('');

    function handleSort(key) {
        setSortConfig(prev => {
            if (prev.key === key) {
                return {key, direction: prev.direction === 'asc' ? 'desc' : 'asc'};
            } else {
                return {key, direction: 'asc'};
            }
        });
    }

    function getSortedFields() {
        if (!sortConfig.key) return progressionOptions;
        const sorted = [...progressionOptions].sort((a, b) => {
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

    const sortedFields = getSortedFields();

    const filteredValues = sortedFields.filter(field => {
        if (!filterValue) return true;
        const value = field[filterField];
        if (typeof value === 'string') {
            return value.toLowerCase().includes(filterValue.toLowerCase());
        }
        if (typeof value === "number") {
            const stringNumber = value.toString();
            return stringNumber.toLowerCase().includes(filterValue.toLowerCase());
        }
        return false;
    });

    // Calculate totalPages based on filtered results
    const totalPages = Math.ceil(filteredValues.length / careerProgressionsPerPage);

    const startIndex = (currentPage - 1) * careerProgressionsPerPage;
    const endIndex = startIndex + careerProgressionsPerPage;

    const careerProgressionsToShow = filteredValues.slice(startIndex, endIndex);

    // Reset currentPage when careerProgressionsPerPage, filterField or filterValue changes
    useEffect(() => {
        setCurrentPage(1);
    }, [careerProgressionsPerPage, filterValue, filterField]);

    useEffect(() => {
        async function fetchOptions() {
            try {
                const [careerProgressions, teacherCategories] =  await Promise.all([
                    fetch(`${process.env.REACT_APP_API_URL}/teacher-career-progressions`),
                    fetch(`${process.env.REACT_APP_API_URL}/teacher-categories`),
            ]);

                if (careerProgressions.ok && teacherCategories.ok) {
                    const progressionOptionsJson = await careerProgressions.json();
                    const teacherCategoriesJson = await teacherCategories.json();

                    // Create array of id's for name consultation
                    const categoryNameById = {};
                    teacherCategoriesJson.forEach(category => {
                        categoryNameById[category.id] = category.name;
                    });

                    // add teacherCategoryName to final json
                    const joinedJson = progressionOptionsJson.map(item => ({
                        ...item,
                        teacherCategoryName: categoryNameById[item.teacherCategoryID] || '',
                    }));

                    setProgressionOptions(joinedJson);
                    setLoading(false);
                } else {
                    setError("Failed to fetch Data");
                    setLoading(false);
                }
            } catch (err) {
                console.error("Failed to load options:", err);
                setError("Failed to load Teacher Career Progression options");
                setLoading(false);
            }
        }

        fetchOptions();
    }, []);

    if (loading) return (
        <div style={{
            display: 'flex',
            justifyContent: 'center',
            alignItems: 'center',
            height: '60vh',
            width: '100%'
        }}>
            <div className="loader" data-testid="loading-spinner"></div>
        </div>
    );

    function PaginationButton({onClick, disabled, children}) {
        return (
            <button className="pagination-btn pagination-btn-primary" onClick={onClick} disabled={disabled}>
                {children}
            </button>
        );
    }

    function PerPageButton({value, selected, onClick}) {
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
        <div className="career-progression-main-component-div">
            <div className="career-progression-main-grid career-progression-main-grid-center">
                <div className="form career-progression-display-table-wrapper">
                    <div className="career-progression-table-container">
                        <div className="career-progression-table-header-bar" style={{
                            display: 'flex',
                            justifyContent: 'space-between',
                            alignItems: 'center',
                            marginBottom: '1.5rem'
                        }}>
                            <div style={{
                                display: 'flex',
                                justifyContent: 'flex-start',
                                alignItems: 'center',
                                height: '100%'
                            }}>
                                <Link to="/" className="pagination-btn2 pagination-btn-secondary" style={{
                                    textDecoration: 'none',
                                    fontSize: '1.5rem',
                                    padding: '0rem 0rem',
                                    minHeight: '3.2rem',
                                    display: 'flex',
                                    alignItems: 'center'
                                }}>
                                    Back to Main Page
                                </Link>
                            </div>
                            <h1 style={{margin: 0}}>Career Progression</h1>
                            <div className="career-progression-table-filter-bar" style={{
                                display: 'flex',
                                alignItems: 'center',
                                gap: '1rem',
                                justifyContent: 'flex-end'
                            }}>
                                <select
                                    value={filterField}
                                    onChange={e => setFilterField(e.target.value)}
                                    className="career-progression-table-filter-select"
                                    style={{
                                        fontSize: '1.4rem',
                                        padding: '0.5rem 1rem',
                                        borderRadius: '4px',
                                        border: '1px solid #ccc'
                                    }}
                                >
                                    <option value="date">Date</option>
                                    <option value="workingPercentage">Working Percentage</option>
                                    <option value="teacherCareerProgressionId">ID</option>
                                    <option value="teacherCategoryName">Teacher Category</option>
                                    <option value="teacherID">Teacher</option>
                                </select>
                                <input
                                    type="text"
                                    value={filterValue}
                                    onChange={e => setFilterValue(e.target.value)}
                                    placeholder={`Search by ${
                                        filterField === 'date' ? 'Date' :
                                            filterField === 'workingPercentage' ? 'Working Percentage' :
                                                filterField === 'teacherID' ? 'Teacher' :
                                                    filterField === 'teacherCategoryName' ? 'Teacher Category' :
                                                        filterField === 'teacherCareerProgressionId' ? 'ID' :
                                                            filterField.charAt(0).toUpperCase() + filterField.slice(1)}`}
                                    className="career-progression-table-filter-input"
                                />
                            </div>
                        </div>
                        <div className="career-progression-table-center-wrapper">
                            <table data-testid="career-progression-table" className="career-progression-form-table">
                                <thead>
                                <tr>
                                    <th data-testid="date-header"
                                        className={`sortable${sortConfig.key === 'date' ? ' selected' : ''}`}
                                        onClick={() => handleSort('date')}>
                                        Date {sortConfig.key === 'date' ? (sortConfig.direction === 'asc' ? '▲' : '▼') : ''}
                                    </th>
                                    <th data-testid="working-percentage-header"
                                        className={`sortable${sortConfig.key === 'workingPercentage' ? ' selected' : ''}`}
                                        onClick={() => handleSort('workingPercentage')}>
                                        Working
                                        percentage {sortConfig.key === 'workingPercentage' ? (sortConfig.direction === 'asc' ? '▲' : '▼') : ''}
                                    </th>
                                    <th data-testid="id-header"
                                        className={`sortable${sortConfig.key === 'teacherCareerProgressionId' ? ' selected' : ''}`}
                                        onClick={() => handleSort('teacherCareerProgressionId')}>
                                        Career Progression ID {sortConfig.key === 'teacherCareerProgressionId' ? (sortConfig.direction === 'asc' ? '▲' : '▼') : ''}
                                    </th>
                                    <th data-testid="teacher-category-header"
                                        className={`sortable${sortConfig.key === 'teacherCategoryName' ? ' selected' : ''}`}
                                        onClick={() => handleSort('teacherCategoryName')}>
                                        Teacher
                                        Category {sortConfig.key === 'teacherCategoryName' ? (sortConfig.direction === 'asc' ? '▲' : '▼') : ''}
                                    </th>
                                    <th data-testid="teacher-header"
                                        className={`sortable${sortConfig.key === 'teacherID' ? ' selected' : ''}`}
                                        onClick={() => handleSort('teacherID')}>
                                        Teacher {sortConfig.key === 'teacherID' ? (sortConfig.direction === 'asc' ? '▲' : '▼') : ''}
                                    </th>
                                </tr>
                                </thead>
                                <tbody>
                                {error ? (
                                    <tr>
                                        <td colSpan="10" style={{
                                            textAlign: 'center',
                                            color: '#111',
                                            fontWeight: 600,
                                            fontSize: '1.5rem'
                                        }}>
                                            Failed to load Teacher Career Progression options
                                        </td>
                                    </tr>
                                ) : careerProgressionsToShow.length === 0 ? (
                                    <tr>
                                        <td colSpan="10" style={{
                                            textAlign: 'center',
                                            color: '#111',
                                            fontWeight: 600,
                                            fontSize: '1.5rem'
                                        }}>
                                            No results found
                                        </td>
                                    </tr>
                                ) : (
                                    careerProgressionsToShow.map(progressionOption => (
                                        <tr key={progressionOption.teacherCareerProgressionId}>
                                            <td>{progressionOption.date}</td>
                                            <td>{progressionOption.workingPercentage}</td>
                                            <td>{progressionOption.teacherCareerProgressionId}</td>
                                            <td>{progressionOption.teacherCategoryName}</td>
                                            <td>{progressionOption.teacherID}</td>
                                        </tr>
                                    ))
                                )}
                                </tbody>
                            </table>
                        </div>
                        <div className="career-progression-pagination-bar">
                            <div className="career-progression-pagination-controls career-progression-pagination-left">
                                <PaginationButton onClick={() => setCurrentPage(p => Math.max(p - 1, 1))}
                                                  disabled={currentPage === 1}>
                                    Previous
                                </PaginationButton>
                                <span className="pagination-page-info" style={{
                                    minWidth: '120px',
                                    textAlign: 'center',
                                    display: 'inline-block'
                                }}>Page {currentPage} of {totalPages}</span>
                                <PaginationButton onClick={() => setCurrentPage(p => Math.min(p + 1, totalPages))}
                                                  disabled={currentPage === totalPages}>
                                    Next
                                </PaginationButton>
                            </div>
                            <div className="career-progression-per-page-controls career-progression-pagination-right">
                                <span className="career-progression-per-page-label">Show:</span>
                                {careerProgressionPerPageOptions.map(opt => (
                                    <PerPageButton
                                        key={opt}
                                        value={opt}
                                        selected={careerProgressionsPerPage === opt}
                                        onClick={() => setCareerProgressionsPerPage(opt)}
                                    />
                                ))}
                                <span className="career-progression-per-page-label-end">per page</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}
