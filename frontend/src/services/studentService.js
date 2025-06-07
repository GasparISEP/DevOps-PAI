const API_URL = process.env.REACT_APP_API_URL || 'http://localhost:8081';

export async function registerStudent(studentDTO) {
    const response = await fetch(`${API_URL}/students`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(studentDTO)
    });

    const contentType = response.headers.get('Content-Type') || '';

    if (!response.ok) {
        let errBody;
        if (contentType.includes('application/json') || contentType.includes('application/hal+json')) {
            errBody = await response.json();
        } else {
            const text = await response.text();
            errBody = { message: text };
        }
        const errMsg = errBody.message || errBody.error || `HTTP ${response.status}`;
        throw new Error(errMsg);
    }


    const isJson = contentType.includes('application/json') || contentType.includes('application/hal+json');
    const result = isJson ? await response.json() : await response.text();

    console.log("[DEBUG] Resposta do backend:", result);
    return result;
}
