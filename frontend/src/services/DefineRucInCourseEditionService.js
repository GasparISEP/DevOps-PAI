const API_URL = process.env.REACT_APP_API_URL || 'http://localhost:8081';

export async function defineRucInCourseEdition({ courseEditionId, teacherId }) {
    const response = await fetch(`${API_URL}/course-editions/${courseEditionId}/ruc`, {
        method: 'PATCH',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ teacherId })
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
            `Fail to define RUC. HTTP ${response.status}`;
        throw new Error(errMsg);
    }

    return responseData;
}

export async function getAllSchoolYears() {
    const response = await fetch(`${API_URL}/school-years/description/`, {
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