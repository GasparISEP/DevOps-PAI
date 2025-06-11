import React from 'react';

export default function GradeStudentErrorModal({ error, onClose }) {
    if (!error) return null;
    return (
        <div className="modal-overlay">
            <div className="modal-content" style={{ borderColor: 'red' }}>
                <h2 style={{ color: 'red' }}>Grade Registration Error</h2> {/* 🔹 Atualizado */}
                <p>{error}</p>
                <button className="modal-btn" onClick={onClose}>Close</button>
            </div>
        </div>
    );
}
