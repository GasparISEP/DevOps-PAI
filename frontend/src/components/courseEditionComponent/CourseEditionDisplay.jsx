import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import '../../styles/DisplayPage.css';
import '../../styles/Buttons.css';
import ActionMenu from '../../components/courseEditionComponent/ActionMenu';
import EnrolmentCountModal from '../../components/courseEditionComponent/EnrolmentCountModal';
import { getAllSchoolYears } from '../../services/DefineRucInCourseEditionService';
import useFetchCourseEditions from '../../components/courseEditionComponent/useFetchCourseEditions';
import useCourseEditionEnrolmentCountModal from '../../components/courseEditionComponent/useCourseEditionEnrolmentCountModal';

export default function CourseEditionDisplay() {
    const courseEditions = useFetchCourseEditions();

    const [currentPage, setCurrentPage] = useState(1);
    const [courseEditionsPerPage, setCourseEditionsPerPage] = useState(10);
    const courseEditionsPerPageOptions = [5, 10, 20, 50];

    const [filterField, setFilterField] = useState('programme acronym');
    const [filterValue, setFilterValue] = useState('');

    const {
        isModalOpen,
        enrolmentCount,
        selectedCourse,
        handleCountEnrolments,
        closeModal
    } = useCourseEditionEnrolmentCountModal();

    const [schoolYears, setSchoolYears] = useState([]);

    useEffect(() => {
        async function loadSchoolYears() {
            try {
                const schoolYearsData = await getAllSchoolYears();
                setSchoolYears(schoolYearsData);
            } catch (error) {
                console.error("Failed to fetch school years:", error);
            }
        }
        loadSchoolYears();
    }, []);

    const filteredCourseEditions = courseEditions.filter(edition => {
        if (!filterValue.trim()) return true;

        const value = {
            'programme acronym': edition.programmeAcronym,
            'course name': edition.courseName,
            'course acronym': edition.courseAcronym
        }[filterField];

        return value?.toLowerCase().includes(filterValue.toLowerCase());
    });

    const totalPages = Math.ceil(filteredCourseEditions.length / courseEditionsPerPage);
    const startIndex = (currentPage - 1) * courseEditionsPerPage;
    const endIndex = startIndex + courseEditionsPerPage;
    const currentItems = filteredCourseEditions.slice(startIndex, endIndex);

    useEffect(() => {
        setCurrentPage(1);
    }, [courseEditionsPerPage, filterField, filterValue]);

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
                            <Link to="/" className="display-pagination-btn">Back to Main Page</Link>
                        </div>
                        <h1 style={{ margin: 0 }}>Course Editions</h1>

                        <div className="display-table-filter-bar">
                            <select
                                value={filterField}
                                onChange={e => setFilterField(e.target.value)}
                                className="display-table-filter-select">
                                <option value="programme acronym">Programme Acronym</option>
                                <option value="course name">Course Name</option>
                                <option value="course acronym">Course Acronym</option>
                            </select>
                            <input
                                type="text"
                                value={filterValue}
                                onChange={e => setFilterValue(e.target.value)}
                                placeholder={`Search by ${
                                    filterField === 'programme acronym' ? 'Programme Acronym' :
                                        filterField === 'course name' ? 'Course Name' :
                                            'Course Acronym'
                                }`}
                                className="display-table-filter-input"
                            />
                        </div>
                    </div>

                    <div className="display-table-center-wrapper">
                        <table className="display-form-table" id="course-edition-table">
                            <thead>
                            <tr>
                                <th>Programme Name</th>
                                <th>Course Name</th>
                                <th>Course Acronym</th>
                                <th>School Year</th>
                                <th>RUC</th>
                                <th className="actions"></th>
                            </tr>
                            </thead>
                            <tbody>
                            {currentItems.length === 0 ? (
                                <tr>
                                    <td colSpan="6" style={{ textAlign: 'center', fontWeight: 'bold' }}>
                                        No results found.
                                    </td>
                                </tr>
                            ) : (
                                currentItems.map((edition, index) => (
                                    <tr key={index}>
                                        <td className="nameRow">{edition.programmeAcronym}</td>
                                        <td className="nameRow">{edition.courseName}</td>
                                        <td>{edition.courseAcronym}</td>
                                        <td>
                                            {schoolYears.find(sy => sy.id === edition.schoolYearID)?.description || edition.schoolYearID}
                                        </td>
                                        <td>{edition.teacherID ? edition.teacherID : "No RUC assigned"}</td>
                                        <td className="actions">
                                            <div className="action-buttons-container">
                                                <ActionMenu
                                                    edition={edition}
                                                    onCountEnrolments={handleCountEnrolments}
                                                />
                                            </div>
                                        </td>
                                    </tr>
                                ))
                            )}
                            </tbody>
                        </table>
                    </div>

                    <div className="display-pagination-bar">
                        <div className="display-pagination-left display-pagination-controls">
                            <PaginationButton onClick={() => setCurrentPage(p => Math.max(p - 1, 1))} disabled={currentPage === 1}>
                                Previous
                            </PaginationButton>
                            <span className="pagination-page-info">Page {currentPage} of {totalPages}</span>
                            <PaginationButton onClick={() => setCurrentPage(p => Math.min(p + 1, totalPages))} disabled={currentPage === totalPages}>
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
            <EnrolmentCountModal
                isOpen={isModalOpen}
                onClose={closeModal}
                count={enrolmentCount}
                courseName={selectedCourse?.courseName}
            />
        </div>
    );
}