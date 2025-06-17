export default function GradeStudentSuccessModal({ success, onClose }) {
    if (!success) return null;

    return (
        <div className="modal-overlay">
            <div className="modal-content">
                <h2 style={{ color: 'green' }}>✅ Grade Registered Successfully</h2>
                <p><strong>Student ID:</strong> {success.studentUniqueNumber}</p>
                <p><strong>Course Edition:</strong> {success.courseName} — {success.schoolYearId}</p>
                <p><strong>Grade Assigned:</strong> {success.grade}</p>

                <button className="modal-btn" onClick={onClose}>Close</button>
            </div>
        </div>
    );
}
