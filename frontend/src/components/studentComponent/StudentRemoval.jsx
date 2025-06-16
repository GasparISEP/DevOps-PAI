import React, { useEffect, useState } from 'react';
import { getAllStudents } from '../../services/studentService';
import { getCourseEditionsByStudent } from '../../services/removeStudentFromCourseEditionService';
import { removeStudentFromCourseEditionService } from '../../services/removeStudentFromCourseEditionService';
import ISEPLogoBranco from "../../assets/images/ISEP_logo-branco.png";
import {Link} from "react-router-dom";
import '../../styles/Form.css'


export default function StudentCourseEditionForm() {
    const [students, setStudents] = useState([]);
    const [selectedStudentID, setSelectedStudentID] = useState('');
    const [courseEditions, setCourseEditions] = useState([]);
    const [selectedCourseEditionID, setSelectedCourseEditionID] = useState('');
    const [message, setMessage] = useState('');
    const [error, setError] = useState('');

    const [loading, setLoading] = useState(false);

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

                setCourseEditions(data);
                setSelectedCourseEditionID('');
            } catch (err) {
                console.error("Error loading course editions:", err);
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

            // Atualizar a lista de edições do curso após remoção
            const updatedEditions = await getCourseEditionsByStudent(selectedStudentID);
            setCourseEditions(updatedEditions);
        } catch (err) {
            console.error(err);
            setError("Error trying to remove student from course edition.");
            setMessage('');
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
                <div className="form-img-main-div">
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
                        <h1 style={{margin: 0}}>Remove Student</h1>
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
                            </div>

                            <div className="form-group">
                                <label className="form-label" htmlFor="courseEdition">Course Edition:</label>
                                <select
                                    id="courseEdition"
                                    className="form-input"
                                    value={selectedCourseEditionID}
                                    onChange={(e) => setSelectedCourseEditionID(e.target.value)}
                                    disabled={!selectedStudentID || courseEditions.length === 0}
                                >
                                    <option value="" disabled hidden>Choose Course Editions </option>
                                    {courseEditions
                                        .filter(edition => edition.studentID === selectedStudentID)
                                        .map(edition => (
                                            <option key={edition.courseEditionGeneratedUUID}
                                                    value={edition.courseEditionGeneratedUUID}>
                                                {edition.courseName} ({edition.courseAcronym})
                                            </option>
                                        ))}
                                </select>
                            </div>


                            <div className="form-actions">
                                <button type="button" className="btn btn-secondary" onClick={handleClear}
                                        disabled={loading}>
                                    CLEAR
                                </button>

                                <button type="submit" className="btn btn-primary" onClick={handleRemove}
                                        disabled={loading}>
                                    {loading ? 'REMOVING…' : 'REMOVE'}
                                </button>
                            </div>
                        </div>

                    </div>
                    {message && <p style={{color: 'green'}}>{message}</p>}
                    {error && <p style={{color: 'red'}}>{error}</p>}
                </form>
            </div>
        </div>
    );
}
