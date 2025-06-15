const API_URL = process.env.REACT_APP_API_URL || 'http://localhost:8081';

/**
 * Register a student's grade using HATEOAS.
 */
export async function gradeAStudentWithLink(gradeAStudentRequestDTO) {
    const response = await fetch(`${API_URL}/studentgrades/register/hateoas`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(gradeAStudentRequestDTO)
    });

    if (!response.ok) {
        const contentType = response.headers.get("content-type");
        let errorData;

        if (contentType && contentType.includes("application/json")) {
            errorData = await response.json();
            throw new Error(errorData.message || 'Error registering the grade');
        } else {
            errorData = await response.text();
            throw new Error(errorData);
        }
    }

    const responseData = await response.json();

    // Extract link to student details (HATEOAS)
    const studentLink = responseData.links?.find(link => link.rel === "student-details")?.href;

    return { data: responseData, studentLink };
}

/**
 * Obtain a student's registration for course editions.
 */
export async function getEnrolmentsForStudent(studentID) {
    const response = await fetch(`${API_URL}/students/${studentID}/courseeditionenrolments`);

    if (!response.ok) {
        throw new Error(`Error when searching for student registrations ${studentID}`);
    }

    return response.json();
}