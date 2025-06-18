import React from "react";
import '../../styles/Modal.css';

export default function StudentRemovalModal({ isSuccess, studentID, courseEdition, onClose }) {
    const title = isSuccess ? "Success!" : "Unsuccessful!";
    const textColor = isSuccess ? "green" : "red";

    return (
        <div className="modal-overlay">
            <div className="modal-content">
                <h2 style={{ color: textColor }}>{title}</h2>

                <p>
                    {isSuccess ? "The student " : "Error trying to remove student "}
                    <strong>{studentID}</strong> {isSuccess ? "was removed from" : "from"}
                    <br />
                    <strong>
                        {courseEdition?.courseName} ({courseEdition?.courseAcronym}) - {courseEdition?.studyPlanStartYear}
                    </strong>
                    {isSuccess ? " successfully." : "."}
                </p>

                <div style={{ marginTop: '1.5rem', display: 'flex', justifyContent: 'center' }}>
                    <button className="modal-btn modal-btn-primary" onClick={onClose}>
                        Close
                    </button>
                </div>
            </div>
        </div>
    );
}
