import React from 'react';
import VisibilityIcon from '@mui/icons-material/Visibility';
import FolderOpenIcon from '@mui/icons-material/FolderOpen';

export default function SuccessModal({ data, form, onClose }) {
    if (!data) return null;

    const openLink = (href) => {
        if (href) {
            const isApi = href.includes('8081') || href.startsWith('http://localhost:8081');
            const url = isApi
                ? href
                : `http://localhost:3000${href}`;

            window.open(url, '_blank');
        }
    };

    const detailsHref = data._links?.details?.href || data._links?.self?.href;
    const collectionHref = data._links?.collection?.href || data._links?.all?.href;

    return (
        <div className="modal-overlay">
            <div className="modal-content">
                <h2 style={{ color: 'green' }}>Success!</h2>
                <p>The teacher category was updated successfully.</p>

                <div className="success" style={{ marginTop: '1rem', color: '#080' }}>
                    <p><strong>Teacher:</strong> {data.teacherID || form.teacher}</p>
                    <p><strong>Teacher Category:</strong> {data.teacherCategoryID || form.teacherCategory}</p>
                    <p><strong>Date:</strong> {data.date || form.date}</p>
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
