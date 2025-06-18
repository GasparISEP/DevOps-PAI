import React from "react";
import '../../styles/Modal.css';
import '../../styles/Buttons.css';

function EnrolmentCountModal({ isOpen, onClose, count, courseName }) {
    if (!isOpen) return null;

    return (
        <div className="modal-overlay" onClick={onClose}>
            <div className="modal-content" onClick={e => e.stopPropagation()}>
                <h2 style={{ color: '#1a1a1a', textAlign: 'center' }}>Enrolment Count</h2>
                <div className="success" style={{ marginTop: '1rem', color: '#080' }}>
                    <p><strong>Course:</strong> {courseName}</p>
                    <p><strong>Enrolled Students:</strong> {count ?? 0}</p>
                </div>
                <div>
                    <button className="pagination-btn-secondary pagination-btn2" onClick={onClose}>
                        Close
                    </button>
                </div>
            </div>
        </div>
    );
}

export default EnrolmentCountModal;


