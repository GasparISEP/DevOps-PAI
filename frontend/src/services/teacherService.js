const API_URL = process.env.REACT_APP_API_URL || 'http://localhost:8081';

export async function registerTeacher(teacherDTO) {
    const response = await fetch(`${API_URL}/teachers`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(teacherDTO)
    });

    if (!response.ok) {
        const errBody = await response.json();
        // procura em message primeiro, depois em error, senão status
        const errMsg = errBody.message || errBody.error || `HTTP ${response.status}`;
        throw new Error(errMsg);
    }

    return response.json();
}

export async function getAllTeachers() {
    const response = await fetch(`${API_URL}/teachers`);
    if (!response.ok) {
        throw new Error('Failed to fetch teachers');
    }
    return response.json();
}

