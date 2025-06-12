import React from "react";

export default function CourseEditionAverageGradeErrorModal({ error, onClose }) {
    return (
        <div className="modal-overlay">
            <div className="modal-content" style={{ borderColor: 'red' }}>
                <h2 style={{ color: 'red' }}>Error Retrieving Average Grade</h2>
                <p>{error}</p>
                <button className="modal-btn" onClick={onClose}>Close</button>
            </div>
        </div>
    );
}