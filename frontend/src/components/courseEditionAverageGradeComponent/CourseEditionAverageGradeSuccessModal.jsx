import React from 'react';
import '../../styles/Modal.css';

export default function CourseEditionAverageGradeSuccessModal({ courseEditionId, averageGrade, onClose }) {
    return (
        <div className="modal-overlay">
            <div className="modal-content success">
                <h2>Average Grade</h2>

                {averageGrade === null ? (
                    <p>No grades available yet for this course edition.</p>
                ) : (
                    <p>
                        The average grade is: <strong>{averageGrade.toFixed(2)}</strong>
                    </p>
                )}

                <button className="btn btn-primary" onClick={onClose}>
                    Close
                </button>
            </div>
        </div>
    );
}