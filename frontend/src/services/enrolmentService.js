export async function getEnrolledProgrammes(studentId) {
    const baseUrl = process.env.REACT_APP_API_URL;

    const response = await fetch(`${baseUrl}/programmes/${studentId}/programmes-enrolled-in`);

    if (!response.ok) {
        throw new Error('Erro ao obter programas em que o estudante está inscrito.');
    }

    const data = await response.json();
    return data.programmeInfo;
}

export async function getProgrammeEditions(programmeEnrolmentGID) {
    const baseUrl = process.env.REACT_APP_API_URL;

    const response = await fetch(`${baseUrl}/programme-enrolments/${programmeEnrolmentGID}/available-programme-editions`);

    if (!response.ok) {
        throw new Error("Erro ao carregar edições disponíveis para este programa.");
    }

    return response.json();
}

export async function getAvailableCourses(programmeEditionIdDto) {
    const baseUrl = process.env.REACT_APP_API_URL;

    const response = await fetch(`${baseUrl}/programme-editions/available-courses`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(programmeEditionIdDto)
    });

    if (!response.ok) {
        throw new Error("Erro ao carregar cursos disponíveis para esta edição.");
    }

    return response.json();
}

export async function enrolStudent(studentId, payload) {
    const baseUrl = process.env.REACT_APP_API_URL;
    const response = await fetch(`${baseUrl}/students/${studentId}/enrolments`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(payload)
    });

    if (!response.ok) {
        throw new Error("Erro ao inscrever o estudante no programa e unidades curriculares.");
    }

    return response.json();
}