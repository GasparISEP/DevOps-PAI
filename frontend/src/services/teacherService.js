const API_URL = process.env.REACT_APP_API_URL || 'http://localhost:8081';

export async function registerTeacher(teacherDTO) {
    const response = await fetch(`${API_URL}/teachers`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(teacherDTO)
    });

    let responseData = null;

    try {
        responseData = await response.json();
    } catch (e) {
        console.warn("Response without JSON body.");
    }

    if (!response.ok) {
        const errMsg = responseData?.message || `HTTP ${response.status}`;
        throw new Error(errMsg);
    }
    return responseData;
}

export async function getAllTeachers() {
    const response = await fetch(`${API_URL}/teachers`);
    if (!response.ok) {
        throw new Error('Failed to fetch teachers');
    }
    return response.json();
}

