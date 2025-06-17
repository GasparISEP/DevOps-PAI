import React from 'react';

export default function StudentEnrolledCourses({ courses }) {
    if (!courses.length) {
        return <p>No courses enrolled.</p>;
    }

    return (
        <div style={{ textAlign: 'left' }}>
            {courses.map((c, idx) => (
                <div key={idx}>
                    <table className="tcp-table">
                        <tbody>
                        <tr className="tcp-row">
                            <th className="tcp-label">Course Acronym</th>
                            <td className="tcp-value">{c.courseAcronym}</td>
                        </tr>
                        <tr className="tcp-row">
                            <th className="tcp-label">Course Name</th>
                            <td className="tcp-value">{c.courseName}</td>
                        </tr>
                        <tr className="tcp-row">
                            <th className="tcp-label">Study Plan Year</th>
                            <td className="tcp-value">{c.studyPlanStartYear}</td>
                        </tr>
                        </tbody>
                    </table>
                    {/* Linha preta entre blocos, excepto no último */}
                    {idx < courses.length - 1 && (
                        <hr style={{ border: '1px solid black', margin: '1rem 0' }} />
                    )}
                </div>
            ))}
        </div>
    );
}