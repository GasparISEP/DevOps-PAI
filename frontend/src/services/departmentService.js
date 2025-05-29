const API_URL = process.env.REACT_APP_API_URL || 'http://localhost:8081';

export async function fetchDepartments() {
    const response = await fetch(`${API_URL}/departments`);
    if (!response.ok) {
        throw new Error('Failed to fetch departments');
    }
    return response.json();
}

