import React from "react";

export default function ProgrammeSuccessModal({ success, degreeTypes, departments, teachers, onClose }) {
    return (
        <div className="modal-overlay">
            <div className="modal-content">
                <h2>Success!</h2>
                <p>The programme was registered successfully.</p>
                {success && (
                    <div className="success" style={{marginTop: '1rem', color: '#080'}}>
                        <p><strong>Name:</strong> {success.name}</p>
                        <p><strong>Acronym:</strong> {success.acronym}</p>
                        <p><strong>Semesters:</strong> {success.quantSemesters}</p>
                        <p><strong>Degree Type:</strong> {degreeTypes.find(dt => dt.id === success.degreeTypeID)?.name || 'Unknown'}</p>
                        <p><strong>ECTS Credits:</strong> {degreeTypes.find(dt => dt.id === success.degreeTypeID)?.maxEcts || 'Unknown'}</p>
                        <p><strong>Department:</strong> {departments.find(d => d.id === success.departmentID)?.name || 'Unknown'}</p>
                        <p><strong>Programme's Director:</strong> {teachers.find(t => t.id === success.teacherID)?.name || 'Unknown'}</p>
                    </div>
                )}
                <button className="modal-btn" onClick={onClose}>Close</button>
            </div>
        </div>
    )
}
