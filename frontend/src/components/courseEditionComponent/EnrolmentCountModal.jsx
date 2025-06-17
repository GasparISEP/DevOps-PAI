import React from "react";
import '../../styles/Modal.css';

function EnrolmentCountModal({ isOpen, onClose, count, courseName }) {
    if (!isOpen) return null;

    return (
        <div className="modal-overlay" onClick={onClose}>
            <div className="modal-content" onClick={e => e.stopPropagation()}>
                <div className="modal-header">
                    <h2>Enrolment Count</h2>
                    <button className="modal-close-button" onClick={onClose}>×</button>
                </div>
                <div className="modal-body">
                    <div className="modal-info-item">
                        <span className="modal-label">Course:</span>
                        <span className="modal-value">{courseName}</span>
                    </div>
                    <div className="modal-info-item">
                        <span className="modal-label">Enrolled Students:</span>
                        <span className="modal-value modal-count">
                                {count ?? 0}
                            </span>
                    </div>
                </div>
                <div className="modal-footer">
                    <button className="modal-close-btn" onClick={onClose}>Close</button>
                </div>
            </div>
        </div>

    );
}

export default EnrolmentCountModal;


