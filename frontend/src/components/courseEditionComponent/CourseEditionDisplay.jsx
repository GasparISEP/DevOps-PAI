import React, {useEffect, useState} from "react";
import { Link } from "react-router-dom";
import '../../styles/DisplayPage.css';
import '../../styles/Buttons.css';

export default function CourseEditionDisplay() {
    const [courseEditions, setCourseEditions] = useState([]);

    const [currentPage, setCurrentPage] = useState(1);
    const [courseEditionsPerPage, setCourseEditionsPerPage] = useState(10);
    const courseEditionsPerPageOptions = [5, 10, 20, 50];
    const totalPages = Math.ceil(courseEditions.length / courseEditionsPerPage);
    const startIndex = (currentPage - 1) * courseEditionsPerPage;
    const endIndex = startIndex + courseEditionsPerPage;
    const currentItems = courseEditions.slice(startIndex, endIndex);
    const [filterField, setFilterField] = useState('programme name');
    const [filterValue, setFilterValue] = useState('');

    useEffect(() => {
        async function fetchCourseEditions() {
            try {
                const courseEditionRes = await fetch(`${process.env.REACT_APP_API_URL}/course-editions`)

                const courseEditionsData = await courseEditionRes.json();

                //setCourseEditions(courseEditionsData._embedded?.courseDTOList || []);
                setCourseEditions(courseEditionsData);
            } catch (err) {
                console.error("Failed to load Course Editions:", err);
            }
        }
        fetchCourseEditions();
    }, []);

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
                disabled={selected}>
                {value}
            </button>
        );
    }

    return (
        <div className="display-main-grid-center">
            <div className="form">
                <div className="display-table-container">
                    <div className="display-table-header-bar">
                        <div>
                            <Link to="/" className="display-pagination-btn">
                                Back to Main Page
                            </Link>
                        </div>
                        <h1 style={{margin: 0}}>Course Editions</h1>

                        <div className="display-table-filter-bar">
                            <select
                                value={filterField}
                                onChange={e => setFilterField(e.target.value)}
                                className="display-table-filter-select">
                                <option value="programme name">Programme Name</option>
                                <option value="course name">Course Name</option>
                                <option value="course acronym">Course Acronym</option>
                            </select>
                            <input
                                type="text"
                                value={filterValue}
                                onChange={e => setFilterValue(e.target.value)}
                                placeholder={`Search by ${
                                    filterField === 'programme name' ? 'Programme Name' :
                                        filterField === 'course name' ? 'Course Name' :
                                            filterField === 'course acronym' ? 'Course Acronym' :
                                                ''
                                }`}
                                className="display-table-filter-input"/>
                        </div>
                    </div>

                    <div className="display-table-center-wrapper">
                        <table className="display-form-table">
                            <thead>
                            <tr>
                                <th>Programme Acronym</th>
                                <th>Course Name</th>
                                <th>Course Acronym</th>
                                <th>School Year</th>
                            </tr>
                            </thead>
                            <tbody>
                            {currentItems.map((edition, index) => (
                                <tr key={index}>
                                    <td>{edition.programmeAcronym}</td>
                                    <td>{edition.courseName}</td>
                                    <td>{edition.courseAcronym}</td>
                                    <td>{edition.schoolYearID}</td>
                                </tr>
                            ))}
                            </tbody>
                        </table>
                    </div>

                    <div className="display-pagination-bar">
                        <div className="display-pagination-left display-pagination-controls">
                            <PaginationButton onClick={() => setCurrentPage(p => Math.max(p - 1, 1))}
                                              disabled={currentPage === 1}>
                                Previous
                            </PaginationButton>
                            <span className="pagination-page-info">Page {currentPage} of {totalPages}</span>


                            <PaginationButton onClick={() => setCurrentPage(p => Math.min(p + 1, totalPages))}
                                              disabled={currentPage === totalPages}>
                                Next
                            </PaginationButton>
                        </div>

                        <div className="display-per-page-controls display-pagination-right">
                            <span className="display-per-page-label">Show:</span>
                            {courseEditionsPerPageOptions.map(opt => (
                                <PerPageButton
                                    key={opt}
                                    value={opt}
                                    selected={courseEditionsPerPage === opt}
                                    onClick={() => setCourseEditionsPerPage(opt)}
                                />
                            ))}
                            <span className="display-per-page-label-end">per page</span>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    );
}