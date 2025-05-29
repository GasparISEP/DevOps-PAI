import React from "react";

export default function ProgrammeErrorModal({ error, onClose }) {
    return (
        <div className="modal-overlay">
            <div className="modal-content" style={{borderColor: 'red'}}>
                <h2 style={{color: 'red'}}>Registration Error</h2>
                <p>{error}</p>
                <button className="modal-btn" onClick={onClose}>Close</button>
            </div>
        </div>
    )
}
