import React from "react";
import InfoImage from "../../assets/images/information.png";
import '../../styles/CourseEditionSuccess.css';

export default function ProgrammeSuccessModal({
                                                  success,
                                                  degreeTypes,
                                                  departments,
                                                  teachers,
                                                  onDisplay,
                                                  onClose,
                                                  loadingDetails,
                                                  detailsDisplayed,
                                              }) {
    const selectedDegree = degreeTypes.find(dt => dt.id === success?.degreeTypeID);
    const selectedDepartment = departments.find(d => d.id === success?.departmentID);
    const selectedTeacher = teachers.find(t => t.id === success?.teacherID);

    return (
        <div className="modal-overlay">
            <div className="modal-content">
                {!detailsDisplayed ? (
                    <>
                        <h2>Success!</h2>
                        <p>The programme was registered successfully.</p>
                    </>
                ) : (
                    <h2 style={{ color: '#1a1a1a' }}>Programme Details</h2>
                )}

                {success?.name && (
                    <div style={{ marginTop: '1.5rem' }}>
                        <table className="courseedition-table">
                            <tbody>
                            <tr className="courseedition-row">
                                <td className="courseedition-label">Name</td>
                                <td className="courseedition-value">{success.name}</td>
                            </tr>
                            <tr className="courseedition-row">
                                <td className="courseedition-label">Acronym</td>
                                <td className="courseedition-value">{success.acronym}</td>
                            </tr>
                            <tr className="courseedition-row">
                                <td className="courseedition-label">Semesters</td>
                                <td className="courseedition-value">{success.quantSemesters}</td>
                            </tr>
                            <tr className="courseedition-row">
                                <td className="courseedition-label">Degree Type</td>
                                <td className="courseedition-value">{selectedDegree?.name || 'Unknown'}</td>
                            </tr>
                            <tr className="courseedition-row">
                                <td className="courseedition-label">Max ECTS</td>
                                <td className="courseedition-value">{success.maxECTS}</td>
                            </tr>
                            <tr className="courseedition-row">
                                <td className="courseedition-label">Department</td>
                                <td className="courseedition-value">{selectedDepartment?.name || 'Unknown'}</td>
                            </tr>
                            <tr className="courseedition-row">
                                <td className="courseedition-label">Director</td>
                                <td className="courseedition-value">{selectedTeacher?.name || 'Unknown'}</td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                )}

                <div style={{ marginTop: '1.5rem', display: 'flex', justifyContent: 'space-between' }}>
                    <button className="modal-btn" onClick={onClose}>
                        Close
                    </button>

                    {!detailsDisplayed && (
                        loadingDetails ? (
                            <span style={{ alignSelf: 'center', fontStyle: 'italic' }}>Loading...</span>
                        ) : (
                            <img
                                src={InfoImage}
                                alt="Display Details"
                                title="Display Details"
                                style={{ cursor: 'pointer', width: '24px', height: '24px' }}
                                onClick={onDisplay}
                            />
                        )
                    )}
                </div>
            </div>
        </div>
    );
}