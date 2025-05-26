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
        let errMsg = `HTTP ${response.status}`;
        try {
            const errBody = await response.json();
            errMsg = errBody.error || errMsg;
        } catch {}
        throw new Error(errMsg);
    }

    return response.json();
}
