export default function StudentEnrolledCourses({ courses }) {
    if (!courses.length) {
        return <p>No courses enrolled.</p>;
    }

    return (
        <div
            style={{
                display: 'flex',
                flexDirection: 'column',
                alignItems: 'flex-start'
            }}
        >
            {courses.map((c, idx) => (
                <table
                    key={idx}
                    className="tcp-table"
                    style={{
                        marginBottom: '1rem',
                        alignSelf: 'flex-start',
                        width: '350px',
                        borderBottom: idx !== courses.length - 1 ? '2px solid black' : 'none'
                    }}
                >
                    <tbody>
                    <tr className="tcp-row">
                        <th className="tcp-label">Course Acronym</th>
                        <td className="tcp-value" style={{ fontSize: '0.95rem', fontWeight: 'bold' }}>{c.courseAcronym}</td>
                    </tr>
                    <tr className="tcp-row">
                        <th className="tcp-label">Course Name</th>
                        <td className="tcp-value" style={{ fontSize: '0.95rem', fontWeight: 'bold' }}>{c.courseName}</td>
                    </tr>
                    <tr className="tcp-row">
                        <th className="tcp-label">Study Plan Year</th>
                        <td className="tcp-value" style={{ fontSize: '0.95rem', fontWeight: 'bold' }}>{c.studyPlanStartYear}</td>
                    </tr>
                    </tbody>
                </table>
            ))}
        </div>
    );
}