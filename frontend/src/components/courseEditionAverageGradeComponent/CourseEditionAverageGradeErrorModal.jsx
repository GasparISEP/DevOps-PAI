import React from 'react';
import '../../styles/Modal.css';

export default function CourseEditionAverageGradeErrorModal({ statusCode, onClose }) {
    const safeStatusCode = statusCode ?? 500;

    const isClientError = safeStatusCode >= 400 && safeStatusCode < 500;
    const isServerError = safeStatusCode >= 500;

    const getTitle = () => {
        if (isClientError) return 'No Grades Available';
        if (isServerError) return 'Server Error';
        return 'Unexpected Error';
    };

    const getMessage = () => {
        if (isClientError) {
            return 'There are no grades assigned yet for this course edition.';
        }
        if (isServerError) {
            return 'An unexpected error occurred while retrieving the average grade. Please try again later.';
        }
        return 'An unknown error occurred.';
    };

    return (
        <div className="modal-overlay">
            <div className="modal-content error">
                <h2 style={{ color: isClientError ? '#cc8800' : 'red' }}>{getTitle()}</h2>
                <p>{getMessage()}</p>
                <button className="btn btn-primary" onClick={onClose}>
                    Close
                </button>
            </div>
        </div>
    );
}