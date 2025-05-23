const API_URL = process.env.REACT_APP_API_URL || 'http://localhost:8081';

export async function registerProgramme(programmeDTO) {
    const response = await fetch(`${API_URL}/programmes`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(programmeDTO)
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
