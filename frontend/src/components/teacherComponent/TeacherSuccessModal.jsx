import React from 'react';

export default function TeacherSuccessModal({ success, departments, onClose }) {
    if (!success) return null;
    return (
        <div className="modal-overlay">
            <div className="modal-content">
                <h2>Success!</h2>
                <p>The teacher was registered successfully.</p>
                <div className="success" style={{ marginTop: '1rem', color: '#080' }}>
                    <p><strong>Name:</strong> {success.name}</p>
                    <p><strong>Acronym:</strong> {success.id}</p>
                    <p><strong>Email:</strong> {success.email}</p>
                    <p><strong>Nif:</strong> {success.nif}</p>
                    <p><strong>Academic Background:</strong> {success.academicBackground}</p>
                    <p><strong>Street:</strong> {success.street}</p>
                    <p><strong>Postal Code:</strong> {success.postalCode}</p>
                    <p><strong>Location:</strong> {success.location}</p>
                    <p><strong>Country:</strong> {success.country}</p>
                    <p><strong>Country Code:</strong> {success.countryCode}</p>
                    <p><strong>Phone Number:</strong> {success.phoneNumber}</p>
                    <p><strong>Department:</strong> {departments.find(d => d.id === success.departmentID)?.name || 'Unknown'}</p>
                </div>
                <button className="modal-btn" onClick={onClose}>Close</button>
            </div>
        </div>
    );
}

