import React from "react";
import '../../styles/Modal.css';
import '../../styles/Buttons.css';

function EnrolmentCountModal({ isOpen, onClose, count, courseName }) {
    if (!isOpen) return null;

    return (
        <div className="modal-overlay" onClick={onClose}>
            <div className="modal-content" onClick={e => e.stopPropagation()}>
                <h2 style={{ color: '#1a1a1a', textAlign: 'center' }}>Enrolment Count</h2>

                <div style={{ marginTop: '1.5rem' }}>
                    <div className="modal-info-item">
                        <span className="modal-label">Course:</span>
                        <span className="modal-value">{courseName}</span>
                    </div>
                    <div className="modal-info-item" style={{ marginTop: '1rem' }}>
                        <span className="modal-label">Enrolled Students:</span>
                        <span className="modal-value modal-count">{count ?? 0}</span>
                    </div>
                </div>

                <div style={{ marginTop: '1.5rem', display: 'flex', justifyContent: 'flex-end' }}>
                    <button className="pagination-btn-secondary pagination-btn2" onClick={onClose}>
                        Close
                    </button>
                </div>
            </div>
        </div>
    );
}

export default EnrolmentCountModal;


