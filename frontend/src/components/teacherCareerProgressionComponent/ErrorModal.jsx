import React from 'react';

export default function ErrorModal({ message, onClose }) {
    if (!message) return null;

    return (
        <div className="modal-overlay">
            <div className="modal-content" style={{ borderColor: 'red' }}>
                <h2 style={{ color: 'red' }}>Error</h2>
                <p>{message}</p>
                <button className="modal-btn" onClick={onClose}>Close</button>
            </div>
        </div>
    );
}
