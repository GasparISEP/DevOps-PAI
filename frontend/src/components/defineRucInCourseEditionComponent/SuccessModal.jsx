import React from 'react';
import '../../styles/Modal.css';

export default function SuccessModal({ data, form, onClose, show }) {
    if (!show || !data) return null;

    return (
        <div className="modal-overlay">
            <div className="modal-content">
                <h2 style={{ color: 'green' }}>Success!</h2>
                <p>RUC successfully defined in the course edition.</p>

                <div className="success" style={{ marginTop: '1rem', color: '#080' }}>
                    <p><strong>Teacher:</strong> {data.teacherName}</p>
                    <p><strong>Course Edition:</strong> {data.courseEditionName}</p>
                    <p><strong>Programme:</strong> {data.programmeName}</p>

                </div>

                <button className="modal-btn" onClick={onClose} style={{ marginTop: '1rem' }}>
                    Close
                </button>
            </div>
        </div>
    );
}
