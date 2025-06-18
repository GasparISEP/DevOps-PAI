import React from 'react';
import { useNavigate } from 'react-router-dom';
import '../../styles/Modal.css';
import VisibilityIcon from "@mui/icons-material/Visibility";
import FolderOpenIcon from "@mui/icons-material/FolderOpen";
import infoImage from "../../assets/images/information.png";
import listImage from "../../assets/images/list.png";

export default function SuccessModal({ data, form, onClose, show }) {
    const navigate = useNavigate();

    if (!show || !data) return null;

    const collectionHref = data._links?.all?.href || data._links?.collection?.href;
    const detailsHref = data._links?.details?.href || data._links?.self?.href || null;

    const openLink = (href) => {
        if (href) {
            const isApi = href.includes('8081') || href.startsWith('http://localhost:8081');
            const url = isApi
                ? href
                : `http://localhost:3000${href}`;

            window.open(url, '_blank');
        }
    };

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

                <div style={{ display: 'flex', justifyContent: 'center', gap: '2rem', marginTop: '2rem' }}>
                    {detailsHref && (
                        <img
                            src={infoImage}
                            alt="View Entry"
                            title="View Entry"
                            style={{cursor: 'pointer', width: '20px', height: '20px'}}
                            onClick={() => openLink(detailsHref)}
                        />
                    )}
                    {collectionHref && (
                        <img
                            src={listImage}
                            alt="View All"
                            title="View All"
                            style={{cursor: 'pointer', width: '20px', height: '20px'}}
                            onClick={() => openLink(collectionHref)}
                        />
                    )}
                </div>

                <button className="modal-btn" onClick={onClose} style={{ marginTop: '1rem' }}>
                    Close
                </button>
            </div>
        </div>
    );
}
