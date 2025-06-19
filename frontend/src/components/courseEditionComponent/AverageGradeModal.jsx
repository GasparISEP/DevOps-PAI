import React from "react";
import '../../styles/Modal.css';
import '../../styles/Buttons.css';

function AverageGradeModal(props = {}) {
    const {
        isOpen,
        onClose,
        average,
        courseName,
        statusCode
    } = props;

    if (!isOpen) return null;

    const isClientError = statusCode >= 400 && statusCode < 500;
    const isServerError = statusCode >= 500;
    const isSuccess = !isClientError && !isServerError;

    const getTitle = () => {
        if (isClientError) return 'No Grades Available';
        if (isServerError) return 'Server Error';
        return 'Average Grade';
    };

    const getMessage = () => {
        if (isClientError) {
            return 'There are no grades assigned yet for this course edition.';
        }
        if (isServerError) {
            return 'An unexpected error occurred while retrieving the average grade. Please try again later.';
        }
        return `The average grade for this course is: ${average}`;
    };

    const getColor = () => {
        if (isClientError) return '#b36b00';
        if (isServerError) return '#a00';
        return '#080';
    };

    const getStyleClass = () => {
        if (isClientError) return 'warning';
        if (isServerError) return 'error';
        return 'success';
    };

    return (
        <div className="modal-overlay" onClick={onClose}>
            <div className="modal-content" onClick={e => e.stopPropagation()}>
                <h2 style={{
                    color: getColor(),
                    textAlign: 'center',
                    fontSize: '1.8rem',
                    marginBottom: '1.5rem',
                    marginTop: '0.5rem'
                }}>
                    {getTitle()}
                </h2>
                <div className={getStyleClass()} style={{
                    marginTop: '1rem',
                    color: getColor(),
                    fontSize: '1.2rem',
                    lineHeight: '1.6'
                }}>
                    <p style={{ marginBottom: '0.8rem' }}>
                        <strong>Course:</strong> {courseName}
                    </p>
                    <p style={{ marginBottom: '0.8rem' }}>
                        {getMessage()}
                    </p>
                </div>
                <div style={{ marginTop: '2rem', textAlign: 'center' }}>
                    <button
                        className="pagination-btn-secondary pagination-btn2"
                        onClick={onClose}
                        style={{
                            fontSize: '1.2rem',
                            padding: '0.7rem 1.5rem',
                            backgroundColor: '#9a1a24',
                            color: 'white',
                            border: '2px solid #9a1a24',
                            borderRadius: '6px',
                            cursor: 'pointer',
                            fontWeight: '600',
                            transition: 'all 0.3s ease',
                            minWidth: '100px'
                        }}
                        onMouseEnter={(e) => {
                            e.target.style.backgroundColor = '#7a1520';
                            e.target.style.borderColor = '#7a1520';
                        }}
                        onMouseLeave={(e) => {
                            e.target.style.backgroundColor = '#9a1a24';
                            e.target.style.borderColor = '#9a1a24';
                        }}
                    >
                        Close
                    </button>
                </div>
            </div>
        </div>
    );
}

export default AverageGradeModal;
