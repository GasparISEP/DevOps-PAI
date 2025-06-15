const API_URL = process.env.REACT_APP_API_URL || 'http://localhost:8081';

export async function defineRucInCourseEdition({ id, teacherId }) {
    const response = await fetch(`${API_URL}/course-editions/${id}/ruc`, {
        method: 'PATCH',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ teacherID: teacherId }),
    });

    console.log('Status:', response.status);

    const contentType = response.headers.get('content-type');

    let data;
    if (contentType && contentType.includes('application/json')) {
        data = await response.json();
    } else {
        data = await response.text();
    }

    if (!response.ok) {
        const errorMessage = typeof data === 'string' ? data : JSON.stringify(data);
        throw new Error(`Failed to define RUC. Status: ${response.status}. Response: ${errorMessage}`);
    }

    return data;
}

export async function getAllSchoolYears() {
    const response = await fetch(`${API_URL}/school-years/descriptions`, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }

    });

    let responseData = null;
    try {
        responseData = await response.json();
    } catch (e) {
        console.warn("Response without JSON body.", e);
    }

    if (!response.ok) {
        const errMsg =
            responseData?.message ||
            responseData?.error ||
            `Failed to fetch school years. HTTP ${response.status}`;
        throw new Error(errMsg);
    }

    return responseData;
}

export async function findAllCourseEditions() {
    const response = await fetch(`${API_URL}/course-editions`);

    if (!response.ok) {
        throw new Error('Failed to fetch Course Editions');
    }

    return response.json();
}