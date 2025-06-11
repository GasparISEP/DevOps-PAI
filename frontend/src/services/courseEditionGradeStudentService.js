const API_URL = process.env.REACT_APP_API_URL || 'http://localhost:8081';

export async function gradeAStudent(gradeAStudentRequestDTO) {
    const response = await fetch(`${API_URL}/courseeditions/studentgrades/register`, { // Corrigido o endpoint
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(gradeAStudentRequestDTO) // Corrigido o nome da variável
    });

    let responseData = null;

    if (!response.ok) {
        const contentType = response.headers.get("content-type");
        if (contentType && contentType.includes("application/json")) {
            responseData = await response.json();
        } else {
            responseData = await response.text();
        }
        throw new Error(responseData.message || responseData);
    }

    try {
        responseData = await response.json();
    } catch (error) {
        console.warn("Resposta sem corpo JSON");
    }

    return responseData; // Retorna um GradeAStudentResponseDTO
}

export async function findAllCourseEditions() {
    const response = await fetch(`${API_URL}/courseeditions`);

    if (!response.ok) {
        throw new Error('Failed to fetch Course Editions');
    }

    return response.json();
}
