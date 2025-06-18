import React, { useState } from "react";
import '../../styles/Modal.css';
import infoImage from "../../assets/images/information.png";
import listImage from "../../assets/images/list.png";

export default function StudentRemovalModal({ isSuccess, studentID, courseEdition, onClose }) {
    const [showDetails, setShowDetails] = useState(false);

    const title = isSuccess ? "Success!" : "Unsuccessful!";
    const textColor = isSuccess ? "green" : "red";

    const detailsHref = courseEdition?.courseAcronym
        ? `/course-editions/by-acronym/${courseEdition.courseAcronym}`
        : null;

    const collectionHref = "/courseeditions/display";

    const openListLink = (href) => {
        if (href) {
            const url = `http://localhost:3000${href}`;
            window.open(url, '_blank');
        }
    };

    const toggleDetails = () => {
        setShowDetails(prev => !prev);
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
                            src={infoImage}
                            alt="View Details"
                            title="View Details"
                            style={{ cursor: 'pointer', width: '20px', height: '20px' }}
                            onClick={toggleDetails}
                        />
                        <img
                            src={listImage}
                            alt="View All"
                            title="View All"
                            style={{ cursor: 'pointer', width: '20px', height: '20px' }}
                            onClick={() => openListLink(collectionHref)}
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
