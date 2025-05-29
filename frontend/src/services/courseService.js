const API_URL = process.env.REACT_APP_API_URL || 'http://localhost:8081';

export async function registerCourseInStudyPlan(payload) {
    const response = await fetch(`${API_URL}/course-in-study-plan`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(payload)
    });

    let responseData = null;

    try {
        responseData = await response.json();
    } catch (e) {
        console.warn("Response without JSON body.");
    }

    if (!response.ok) {
        const errMsg =
            responseData?.message ||
            responseData?.error ||
            `Failed to register course. HTTP ${response.status}`;
        throw new Error(errMsg);
    }

    return responseData;
}
