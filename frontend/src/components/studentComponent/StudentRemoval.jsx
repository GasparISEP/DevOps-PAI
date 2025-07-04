import React, { useEffect, useState } from 'react';
import { getAllStudents } from '../../services/studentService';
import { getCourseEditionsByStudent } from '../../services/removeStudentFromCourseEditionService';
import { removeStudentFromCourseEditionService } from '../../services/removeStudentFromCourseEditionService';
import ISEPLogoBranco from "../../assets/images/ISEP_logo-branco.png";
import {Link} from "react-router-dom";
import '../../styles/Form.css'
import StudentRemovalModal from "./StudentRemovalModal";


export default function StudentCourseEditionForm() {
    const [students, setStudents] = useState([]);
    const [selectedStudentID, setSelectedStudentID] = useState('');
    const [courseEditions, setCourseEditions] = useState([]);
    const [selectedCourseEditionID, setSelectedCourseEditionID] = useState('');
    const [message, setMessage] = useState('');
    const [error, setError] = useState('');

    const [loading, setLoading] = useState(false);

    const [showModal, setShowModal] = useState(false);
    const [removedEdition, setRemovedEdition] = useState(null);

    const [isSuccess, setIsSuccess] = useState(true);

    useEffect(() => {
        async function fetchStudents() {
            try {
                const data = await getAllStudents();
                setStudents(data);
            } catch (err) {
                console.error("Error loading students:", err);
            }
        }

        fetchStudents();
    }, []);

    useEffect(() => {
        console.log("selectedStudentID:", selectedStudentID);
        async function fetchCourseEditions() {
            if (!selectedStudentID) {
                setCourseEditions([]);
                return;
            }

            try {
                const data = await getCourseEditionsByStudent(selectedStudentID);

                if (!Array.isArray(data)) {
                    throw new Error("Response is not an array!");
                }

                console.log("[DEBUG] typeof courseEditions:", typeof data);
                console.log("[DEBUG] courseEditions:", data);

                const filtered = data.filter(
                    edition => String(edition.studyPlanStartYear) === "2025"
                );

                setCourseEditions(filtered);
            } catch (err) {
                console.error("Error loadingit sg course editions:", err);
            }
        }

        fetchCourseEditions();
    }, [selectedStudentID]);


    async function handleRemove(e) {
        e.preventDefault();

        if (!selectedCourseEditionID) {
            setError("Please select a Course Edition");
            setMessage('');
            return;
        }

        try {
            setLoading(true);
            await removeStudentFromCourseEditionService(selectedStudentID, selectedCourseEditionID);
            setMessage("Student removed successfully from course edition.");
            setError('');
            setSelectedCourseEditionID('');

            const removed = courseEditions.find(e => e.courseEditionGeneratedUUID === selectedCourseEditionID);
            setRemovedEdition(removed);
            setShowModal(true);
            setIsSuccess(true);

            const updatedEditions = await getCourseEditionsByStudent(selectedStudentID);
            setCourseEditions(updatedEditions);
        } catch (err) {
            console.error(err);
            setError("Error trying to remove student from course edition.");
            setMessage('');

            const removed = courseEditions.find(e => e.courseEditionGeneratedUUID === selectedCourseEditionID);
            setRemovedEdition(removed);
            setShowModal(true);
            setIsSuccess(false);
        } finally {
            setLoading(false);
        }
    }


    function handleClear() {
        setSelectedStudentID('');
        setSelectedCourseEditionID('');
        setCourseEditions([]);
        setMessage('');
        setError('');
    }


    return (
        <div className="form-main-component-div">
            <div className="form-main-grid">
                <div className="form-img-main-div remove-student-form-img-div">
                    <div className="form-logo-img-div">
                        <img src={ISEPLogoBranco} alt="Logo do ISEP"/>
                    </div>
                </div>

                <form className="form">

                    <div style={{
                        display: 'flex',
                        alignItems: 'center',
                        justifyContent: 'space-between',
                        marginBottom: '2rem'
                    }}>
                        <h1 style={{margin: 0}}>Remove Enrolment</h1>
                        <Link to="/" className="pagination-btn2 pagination-btn-secondary"
                              style={{textDecoration: 'none'}}>
                            Back to Home Page
                        </Link>
                    </div>

                    <div className="form-and-buttons-main-div">
                        <div className="form-div">
                            <div className="form-group">
                                <label className="form-label" htmlFor="studentID">Enter Student Number:</label>
                                <input
                                    type="text"
                                    className="form-input"
                                    placeholder="Enter 7-digit Student Unique Number"
                                    id="studentID"
                                    value={selectedStudentID}
                                    onChange={(e) => {
                                        const input = e.target.value;

                                        if (!/^\d*$/.test(input)) {
                                            return;
                                        }

                                        setSelectedStudentID(input);
                                        setSelectedCourseEditionID('');
                                        setMessage('');
                                        setError('');
                                    }}
                                    onBlur={() => {
                                        const uniqueNumber = Number(selectedStudentID);
                                        if (uniqueNumber <= 1000000 || uniqueNumber >= 2000000) {
                                            setError('Student number must be between 1000001 and 1999999.');
                                        } else {
                                            setError('');
                                        }
                                    }}
                                />

                                <p style={{
                                    color: selectedStudentID && courseEditions.length === 0 ? 'red' : 'transparent',
                                    height: '1.25rem',
                                    marginTop: '0.5rem'
                                }}>
                                    This student is not enrolled in any course edition.
                                </p>

                            </div>

                            <div className="form-group">
                                <label className="form-label" htmlFor="courseEdition">Course Edition:</label>
                                <select
                                    id="courseEdition"
                                    className="form-input"
                                    data-testid="courseEdition"
                                    value={selectedCourseEditionID}
                                    onChange={(e) => setSelectedCourseEditionID(e.target.value)}
                                    disabled={!selectedStudentID || courseEditions.length === 0}
                                >
                                    <option value="" disabled hidden>Choose Course Editions </option>
                                    {courseEditions.map(edition => {
                                        return (
                                            <option key={edition.courseEditionGeneratedUUID} value={edition.courseEditionGeneratedUUID}>
                                                {edition.courseName} ({edition.courseAcronym}) - {edition.studyPlanStartYear}
                                            </option>
                                        );
                                    })}
                                </select>
                            </div>


                            <div className="form-actions">
                                <button type="button" className="btn btn-secondary" onClick={handleClear}
                                        disabled={loading}>
                                    CLEAR
                                </button>

                                <button
                                    type="submit"
                                    className="btn btn-primary"
                                    onClick={handleRemove}
                                    disabled={loading}
                                    data-testid="remove-button"
                                >
                                    {loading ? 'REMOVING…' : 'REMOVE'}
                                </button>
                            </div>
                        </div>
                    </div>
                    {error && (
                        <p style={{color: 'red', marginTop: '1rem'}}>
                        {error}
                        </p>
                    )}
                    {message && (
                        <p style={{ color: 'green', marginTop: '1rem' }}>
                            {message}
                        </p>
                    )}
                </form>
            </div>
            {showModal && (
                <StudentRemovalModal
                    isSuccess={isSuccess}
                    studentID={selectedStudentID}
                    courseEdition={removedEdition}
                    onClose={() => setShowModal(false)}
                />
            )}
        </div>
    );
}
