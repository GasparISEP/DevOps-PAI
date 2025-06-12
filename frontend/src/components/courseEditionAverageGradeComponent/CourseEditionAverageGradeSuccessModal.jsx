import React from "react";

export default function CourseEditionAverageGradeSuccessModal({ result, onClose }) {
    return (
        <div className="modal-overlay">
            <div className="modal-content">
                <h2>Success!</h2>
                <p>The average grade was retrieved successfully.</p>
                {result && (
                    <div className="success" style={{ marginTop: '1rem', color: '#080' }}>
                        <p><strong>Course Edition ID:</strong> {result.courseEditionId}</p>
                        <p><strong>Average Grade:</strong> {result.averageGrade}</p>
                    </div>
                )}
                <button className="modal-btn" onClick={onClose}>Close</button>
            </div>
        </div>
    );
}