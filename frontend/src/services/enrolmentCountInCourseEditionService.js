export async function fetchEnrolmentCount(courseEditionId) {
    try {
        const response = await fetch(`${process.env.REACT_APP_API_URL}/course-editions/${courseEditionId}/enrolments/count`);
        if (!response.ok) {
            throw new Error(`Failed to fetch enrolment count: ${response.status} ${response.statusText}`);
        }
        const data = await response.json();
        return data;
    } catch (error) {
        throw error;
    }
}