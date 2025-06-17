import React from 'react';
import '../../styles/Modal.css';
import VisibilityIcon from "@mui/icons-material/Visibility";
import FolderOpenIcon from "@mui/icons-material/FolderOpen";
export default function SuccessModal({ data, form, onClose, show }) {
    if (!show || !data) return null;
    const collectionHref = data._links?.collection?.href || data._links?.all?.href;
    const detailsHref = data._links?.self?.href || null;
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
                        <VisibilityIcon
                            fontSize="medium"
                            titleAccess="View Entry"
                            style={{ cursor: 'pointer', color: '#555' }}
                            onClick={() => openLink(detailsHref)}
                        />
                    )}
                    {collectionHref && (
                        <FolderOpenIcon
                            fontSize="medium"
                            titleAccess="View All"
                            style={{ cursor: 'pointer', color: '#555' }}
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