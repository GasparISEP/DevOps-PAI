import React from 'react';
import '../../styles/Modal.css';

export default function ErrorModal({ message, onClose, show }) {
    if (!show || !message) return null;

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
