const API_URL = process.env.REACT_APP_API_URL || 'http://localhost:8081';

export async function removeStudentFromCourseEditionService(studentId, courseEditionGeneratedUUID) {
    const response = await fetch(`${API_URL}/students/${studentId}/course-editions/${courseEditionGeneratedUUID}`, {
        method: 'PATCH',
    });

    if (!response.ok) {
        throw new Error('Error trying to remove student from course edition.');
    }
}


export async function getCourseEditionsByStudent(studentID) {
    const response = await fetch(`${API_URL}/students/${studentID}/enrolled-course-editions`);

    const text = await response.text();

    try {
        const data = JSON.parse(text);
        return data;
    } catch (err) {
        throw err;
    }
}