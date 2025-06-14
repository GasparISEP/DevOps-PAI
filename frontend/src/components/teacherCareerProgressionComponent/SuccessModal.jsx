import React from 'react';
import VisibilityIcon from '@mui/icons-material/Visibility';
import FolderOpenIcon from '@mui/icons-material/FolderOpen';

const apiToRouteMap = {
    'teacher-career-progressions': '/teachers/displayTeacherCareerProgressions',
};

export default function SuccessModal({ data, form, onClose }) {
    if (!data) return null;

    return (
        <div className="modal-overlay">
            <div className="modal-content">
                <h2 style={{ color: 'green' }}>Success!</h2>
                <p>The teacher category was updated successfully.</p>

                <div className="success" style={{ marginTop: '1rem', color: '#080' }}>
                    <p><strong>Teacher:</strong> {data.teacher || form.teacher}</p>
                    <p><strong>Teacher Category:</strong> {data.teacherCategory || form.teacherCategory}</p>
                    <p><strong>Date:</strong> {data.date || form.date}</p>
                </div>

                <div style={{ display: 'flex', justifyContent: 'center', gap: '2rem', marginTop: '2rem' }}>
                    {data._links?.self?.href && (
                        <VisibilityIcon
                            fontSize="medium"
                            titleAccess="View Entry"
                            style={{ cursor: 'pointer', color: '#555' }}
                            onClick={() => {
                                const id = data._links.self.href.split('/').pop();
                                if (id) {
                                    window.open(`${window.location.origin}/teacher-career-progressions/${id}`, '_blank');
                                }
                            }}
                        />
                    )}
                    {data._links?.all?.href && (
                        <FolderOpenIcon
                            fontSize="medium"
                            titleAccess="View All"
                            style={{ cursor: 'pointer', color: '#555' }}
                            onClick={() => {
                                const path = new URL(data._links.all.href).pathname.replace(/^\/+/, '');
                                const route = apiToRouteMap[path];
                                if (route) {
                                    window.open(`${window.location.origin}${route}`, '_blank');
                                }
                            }}
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
