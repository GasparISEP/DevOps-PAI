import React from "react";
import '../../styles/Modal.css';
import '../../styles/Buttons.css';

function EnrolmentCountModal({ isOpen, onClose, count, courseName }) {
    if (!isOpen) return null;

    return (
        <div className="modal-overlay" onClick={onClose}>
            <div className="modal-content" onClick={e => e.stopPropagation()}>
                <h2 style={{
                    color: '#1a1a1a',
                    textAlign: 'center',
                    fontSize: '1.8rem',
                    marginBottom: '1.5rem'
                }}>
                    Enrolment Count
                </h2>
                <div className="success" style={{
                    marginTop: '1rem',
                    color: '#080',
                    fontSize: '1.2rem',
                    lineHeight: '1.6'
                }}>
                    <p style={{ marginBottom: '0.8rem' }}>
                        <strong>Course:</strong> {courseName}
                    </p>
                    <p style={{ marginBottom: '0.8rem' }}>
                        <strong>Enrolled Students:</strong> {count ?? 0}
                    </p>
                </div>
                <div style={{ marginTop: '2rem', textAlign: 'center' }}>
                    <button
                        className="pagination-btn-secondary pagination-btn2"
                        onClick={onClose}
                        style={{
                            fontSize: '1.3rem',
                            padding: '1rem 2rem',
                            backgroundColor: '#9a1a24',
                            color: 'white',
                            border: '2px solid #9a1a24',
                            borderRadius: '6px',
                            cursor: 'pointer',
                            fontWeight: '600',
                            transition: 'all 0.3s ease',
                            minWidth: '120px'
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

export default EnrolmentCountModal;