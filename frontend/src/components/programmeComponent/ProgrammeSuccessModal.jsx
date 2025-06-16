import React from "react";

export default function ProgrammeSuccessModal({success, degreeTypes, departments, teachers, onDisplay, onClose, loadingDetails, detailsDisplayed,
                                              }) {
    const selectedDegree = degreeTypes.find(dt => dt.id === success?.degreeTypeID);
    const selectedDepartment = departments.find(d => d.id === success?.departmentID);
    const selectedTeacher = teachers.find(t => t.id === success?.teacherID);

    return (
        <div className="modal-overlay">
            <div className="modal-content">
                <h2>Success!</h2>
                <p>The programme was registered successfully.</p>

                {success?.name && (
                    <div style={{ marginTop: '1rem' }}>
                        <p><strong>Name:</strong> {success.name}</p>
                        <p><strong>Acronym:</strong> {success.acronym}</p>
                        <p><strong>Semesters:</strong> {success.quantSemesters}</p>
                        <p><strong>Degree Type:</strong> {selectedDegree?.name || 'Unknown'}</p>
                        <p><strong>ECTS Credits:</strong> {success.maxECTS}</p>
                        <p><strong>Department:</strong> {selectedDepartment?.name || 'Unknown'}</p>
                        <p><strong>Programme's Director:</strong> {selectedTeacher?.name || 'Unknown'}</p>
                    </div>
                )}

                <div style={{ marginTop: '1.5rem', display: 'flex', justifyContent: 'space-between' }}>
                    <button className="modal-btn" onClick={onClose}>
                        Close
                    </button>

                    {!detailsDisplayed && (
                        <button
                            className="modal-btn modal-btn-primary"
                            onClick={onDisplay}
                            disabled={loadingDetails}
                        >
                            {loadingDetails ? 'Loading...' : 'Display Details'}
                        </button>
                    )}
                </div>
            </div>
        </div>
    );
}

