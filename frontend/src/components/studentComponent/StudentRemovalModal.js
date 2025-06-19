import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import '../../styles/Modal.css';
import listImage from "../../assets/images/list.png";

export default function StudentRemovalModal({ isSuccess, studentID, courseEdition, onClose }) {
    const [showDetails, setShowDetails] = useState(false);
    const navigate = useNavigate();

    const title = isSuccess ? "Success!" : "Unsuccessful!";
    const textColor = isSuccess ? "green" : "red";

    const collectionHref = "/courseeditions/display";

    const goToList = () => {
        navigate(collectionHref);
    };

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

                {isSuccess && (
                    <div style={{ display: 'flex', justifyContent: 'center', gap: '2rem', marginTop: '2rem' }}>
                        <img
                            src={listImage}
                            alt="View All"
                            title="View All"
                            style={{ cursor: 'pointer', width: '20px', height: '20px' }}
                            onClick={goToList}
                            data-testid="view-all-icon"
                        />
                    </div>
                )}

                {showDetails && courseEdition && (
                    <div style={{ marginTop: '1rem', textAlign: 'center', color: '#333' }}>
                        <p><strong>Course Edition Acronym:</strong> {courseEdition.courseAcronym}</p>
                        <p><strong>Course Edition Name:</strong> {courseEdition.courseName}</p>
                        <p><strong>RUC:</strong> {courseEdition.ruc || 'N/A'}</p>
                    </div>
                )}

                <div style={{ marginTop: '1.5rem', display: 'flex', justifyContent: 'center' }}>
                    <button className="modal-btn modal-btn-primary" onClick={onClose}>
                        Close
                    </button>
                </div>
            </div>
        </div>
    );
}
