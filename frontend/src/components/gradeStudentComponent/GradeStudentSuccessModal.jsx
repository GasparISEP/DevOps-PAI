import React from 'react';

export default function GradeStudentSuccessModal({ success, onClose }) {
    if (!success) return null;
    return (
        <div className="modal-overlay">
            <div className="modal-content">
                <h2>Success!</h2>
                <p>The student's grade was registered successfully.</p>
                <div className="success" style={{ marginTop: '1rem', color: '#080' }}>
                    <p><strong>Student ID:</strong> {success.studentUniqueNumber}</p>
                    <p><strong>Grade:</strong> {success.grade}</p>
                    <p><strong>Date:</strong> {success.date}</p>
                    <p><strong>Course Edition ID:</strong> {success.courseEditionID}</p>
                </div>
                <button className="modal-btn" onClick={onClose}>Close</button>
            </div>
        </div>
    );
}
