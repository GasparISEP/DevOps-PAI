const API_URL = process.env.REACT_APP_API_URL || 'http://localhost:8081';

export async function registerProgramme(payload) {
    const response = await fetch(`${API_URL}/programmes`, {
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
        const errMsg = responseData?.message || `HTTP ${response.status}`;
        throw new Error(errMsg);
    }

    return responseData;
}
