const API_URL = process.env.REACT_APP_API_URL || 'http://localhost:8081';

export async function registerStudent(studentDTO) {
    const response = await fetch(`${API_URL}/students`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(studentDTO)
    });

    if (!response.ok) {
        const errBody = await response.json();
        // procura em message primeiro, depois em error, senão status
        const errMsg = errBody.message || errBody.error || `HTTP ${response.status}`;
        throw new Error(errMsg);
    }

    return response.json();
}
