import React from "react";
import { useNavigate } from "react-router-dom";
import '../../styles/Modal.css';
import infoImage from "../../assets/images/information.png";
import listImage from "../../assets/images/list.png";

export default function StudentRemovalModal({ isSuccess, studentID, courseEdition, onClose }) {
    const title = isSuccess ? "Success!" : "Unsuccessful!";
    const textColor = isSuccess ? "green" : "red";

    const detailsHref = courseEdition?.courseEditionGeneratedUUID
        ? `/course-editions/by-id/${courseEdition.courseEditionGeneratedUUID}`
        : null;


    const collectionHref = "/courseeditions/display";

    const openLink = (href) => {
        if (href) {

            const url = `http://localhost:3000${href}`;
            window.open(url, '_blank');
        }
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
                            onClick={() => openLink(detailsHref)}
                        />
                        <img
                            src={listImage}
                            alt="View All"
                            title="View All"
                            style={{ cursor: 'pointer', width: '20px', height: '20px' }}
                            onClick={() => openLink(collectionHref)}
                        />
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
